package usecasesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import daoimpl.CartesDaoImpl;
import daoimpl.DesDaoImpl;
import daoimpl.JoueurDaoImpl;
import daoimpl.JoueurPartieDaoImpl;
import daoimpl.PartiesDaoImpl;
import domaine.Carte;
import domaine.De;
import domaine.Face;
import domaine.Info;
import domaine.Joueur;
import domaine.JoueurPartie;
import domaine.ObjectFactory;
import domaine.Partie;
import domaine.Partie.Etat;
import domaine.Wazabi;
import usecases.GestionParties;
import util.JDOM;

@Singleton
@Startup
public class GestionPartiesImpl implements GestionParties {
	private Partie partie;
	private ObjectFactory objFact = new ObjectFactory();
	private static AtomicInteger num = new AtomicInteger(0);

	@EJB
	JoueurDaoImpl joueurDao;
	@EJB
	PartiesDaoImpl partieDao;
	@EJB
	JoueurPartieDaoImpl joueurPartieDao;
	@EJB
	CartesDaoImpl carteDao;
	@EJB
	DesDaoImpl deDao;

	int nbDes = 0;

	@PostConstruct
	public void postconstruct() {
		ObjectFactory o = new ObjectFactory();
		JDOM dom = new JDOM();
		Wazabi jeu = dom.getJeu();
		for (Carte c : jeu.getCarte()) {
			for (int i = 0; i < c.getNb(); i++) {
				Carte clone = o.createCarte();
				clone.setCodeEffet(c.getCodeEffet());
				clone.setCout(c.getCout());
				clone.setEffet(c.getEffet());
				clone.setNb(c.getNb());
				clone.setSrc(c.getSrc());
				carteDao.enregistrer(clone);
			}
		}
		De de = jeu.getDe();
		for (int i = 0; i < de.getNbTotalDes(); i++) {
			De dee = o.createDe();
			dee.setFace(de.getFace());
			dee.setNbParJoueur(de.getNbParJoueur());
			dee.setNbTotalDes(de.getNbTotalDes());
			dee.setValeur("w");
			deDao.enregistrer(dee);
		}

		System.out.println("********************************* ENREG *****************************");
	}

	@PreDestroy
	public void predestroy() {
		System.out.println("GestionPartieImpl destroyed");
	}

	@Override
	public boolean rejoindreLaPartie(String pseudo) {
		/*
		 * boolean aRenvoyer = false; List<Partie> parties = partieDao.lister();
		 * for (Partie p : parties) { if (p.getEtat() == Etat.INITIAL) {
		 * this.partie = p; aRenvoyer = true; } } if (!aRenvoyer) return false;
		 * 
		 * partie = partieDao.rechercher(partie.getId());
		 */
		System.out.println("********** creation joueur partie " + pseudo + "*****************");
		partie = getDernierePartie();
		partie = partieDao.chargerPartie(partie);
		for (JoueurPartie jp : partie.getJoueursParties()) {
			if (jp.getJoueur().getPseudo().equals(pseudo))
				// System.out.println("********************************************Joueur
				// " + jp.getJoueur().getPseudo() + " d�j� dans le jeu");
				return true;

		}
		JoueurPartie joueurPart;
		// = joueurPartieDao.recherche(partie.getId(), pseudo);
		// joueurPart=joueurPartieDao.chargerJoueur(joueurPart);
		// if (joueurPart == null) {
		joueurPart = objFact.createJoueurPartie();
		joueurPart.setId_partie(partie);
		joueurPart.setJoueur(joueurDao.recherche(pseudo));
		joueurPart = joueurPartieDao.enregistrer(joueurPart);
		System.out.println("********** creation joueur partie " + pseudo + "*****************");
		List<Carte> main = new ArrayList<>();
		/*********** Tester les diff�rentes cartes ***************/
		int limite = 3;
		if (joueurPart.getJoueur().getPseudo().equals("a")) {
			main.add(carteDao.rechercher(21));
			limite = 2;
		}
		for (int i = 0; i < limite; i++) {
			int index = (int) (Math.random() * partie.pioche.size());
			main.add(partie.pioche.remove(index));
		}
		
		
		/**************************/
		joueurPart.setMainCarte(main);
		for (int i = 0; i < 4; i++) {
			De de = deDao.rechercher(nbDes + 1);
			joueurPart.ajouterDe(de);
			nbDes++;
		}
		joueurPartieDao.mettreAJour(joueurPart);
		// }
		System.out.println("**************** JOUEUR PART = " + joueurPart);
		partie.ajouterJoueurPartie(joueurPart);
		joueurPartieDao.enregistrer(joueurPart);
		partieDao.mettreAJour(partie);
		return true;
	}

	@Override
	public boolean partieEnCours() {
		List<Partie> parties = partieDao.lister();
		for (Partie p : parties) {
			p = partieDao.chargerPartie(partie);
			if (p.getEtat() != Etat.FINIE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Partie creer(Partie part) {
		this.partie = part;
		partieDao.enregistrer(partie);
		partie = partieDao.chargerPartie(partie);
		partie.setPioche(carteDao.lister());
		partie.setJoueurCourant(partie.getJoueursParties().get(0));
		return partieDao.mettreAJour(partie);

	}

	@Override
	public Partie creer(String nom, String pseudo) {
		Partie nouvelle = objFact.createPartie();
		Joueur joueur = joueurDao.recherche(pseudo);
		JoueurPartie jp = objFact.createJoueurPartie();
		jp.setId_partie(nouvelle);
		jp.setJoueur(joueur);

		nouvelle.setNom(nom);
		this.partie = nouvelle;
		partieDao.enregistrer(partie);
		partie = partieDao.chargerPartie(partie);
		partie.setPioche(carteDao.lister());
		List<Carte> main = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			int index = (int) (Math.random() * partie.pioche.size());
			main.add(partie.pioche.remove(index));
		}
		jp.setMainCarte(main);
		for (int i = 0; i < 4; i++) {
			De de = deDao.rechercher(nbDes + 1);
			jp.ajouterDe(de);
			nbDes++;
		}
		joueurPartieDao.enregistrer(jp);
		nouvelle.ajouterJoueurPartie(jp);
		partie.setJoueurCourant(partie.getJoueursParties().get(0));
		return partieDao.mettreAJour(partie);
	}

	@Override
	public boolean commencerPartie() {

		partie = partieDao.chargerPartie(partie);
		partie.setJoueurCourant(partie.getJoueursParties().get(0));
		partieDao.mettreAJour(partie);
		// partie = partieDao.rechercher(partie.getId());
		if (partie == null || partie.getEtat() != Etat.INITIAL)
			return false;
		if (partie.commencerPartie()) {
			partieDao.mettreAJour(partie);
			return true;
		}
		return false;
	}

	@Override
	public void initialiser() {
		if (partie == null || partie.getEtat() == Etat.FINIE) {
			partie = new Partie("partie" + num.getAndIncrement());
			partie = partieDao.enregistrer(partie);
		}
	}

	@Override
	public String vainqueur() {
		if (partie == null)
			return null;
		partie = partieDao.rechercher(partie.getId());
		Joueur v = partie.estVainqueur();
		v = joueurDao.chargerJoueursParties(v);
		if (v == null)
			return null;
		return v.getPseudo();
	}

	@Override
	public List<Partie> listerParties() {
		return partieDao.lister();
	}

	@Override
	public String joueurCourant() {		
		if (partie == null)
			return null;
		if (partie.getJoueurCourant() == null)
			return null;
		partie = partieDao.rechercher(partie.getId());
		return partie.getJoueurCourant().getJoueur().getPseudo();
	}

	@Override
	public boolean auSuivant() {
		// if (partie == null)
		// return false;
		partie = partieDao.rechercher(partie.getId());
		if (partie.commencerTourSuivant()){
			partieDao.mettreAJour(partie);
			return true;
		}
		return false;
	}

	@Override
	public boolean estFinie() {
		if (partie == null)
			return true;
		partie = partieDao.rechercher(partie.getId());
		return partie.getEtat() == Etat.FINIE;
	}

	@Override
	public void annulerPartie() {
		if (partie != null) {
			partie = partieDao.rechercher(partie.getId());
			partie.annuler();
		}
	}

	@Override
	public Etat getEtatPartie() {
		return partie.getEtat();
	}

	@Override
	public List<Carte> getCartesJoueur(String pseudo) {
		partie = partieDao.getDernier();
		Joueur tmp = joueurDao.recherche(pseudo);
		JoueurPartie jp = joueurPartieDao.recherche(this.partie, tmp);
		return carteDao.lister(jp);
	}

	@Override
	public boolean donnerDe(String joueurDonneur, String joueurReceveur) {
		Joueur jDonneur = joueurDao.recherche(joueurDonneur);
		JoueurPartie joueurDon = joueurPartieDao.recherche(partie, jDonneur);

		Joueur jReceveur = joueurDao.recherche(joueurReceveur);
		JoueurPartie joueurRecev = joueurPartieDao.recherche(partie, jReceveur);

		joueurDon = joueurPartieDao.chargerJoueur(joueurDon);
		joueurRecev = joueurPartieDao.chargerJoueur(joueurRecev);
		De aDonner = joueurDon.supprimerDe();
		joueurRecev.ajouterDe(aDonner);
		joueurPartieDao.mettreAJour(joueurDon);
		joueurPartieDao.mettreAJour(joueurRecev);
		return false;
	}

	@Override
	public Info jouerCarte(String codeEffet, String cible) {
		partie = partieDao.getDernier();
		System.out.println("*******************************  pseudo joueur courant" +partie.getJoueurCourant().getJoueur().getPseudo());
		switch (codeEffet) {
		case "1":
			// Supprimez 1 de vos des
			// On ne peut pas jouer cette carte si on obtient dans le meme tour
			// 2 figures
			int nbFigure = 0;
			for (De de : partie.getJoueurCourant().getMainDe()) {
				if (de.getValeur().equals(Face.DE))
					nbFigure++;
			}
			// if (nbFigure >= 2) {
			// return false;
			// }
			partie.getJoueurCourant().supprimerDe();
			break;
		case "2":
			// tous les joueurs donnent leurs des a leur voisin de droite ou de
			// gauche
			// voisin de gauche
			if (cible.equals("g")) {
				List<De> ancienne;
				List<De> nouvelle = partie.getJoueursParties().get(partie.getJoueursParties().size() - 1).getMainDe();
				System.out.println("*****************    Je suis a gauche ******************");
				for (int i = 0; i < partie.getJoueursParties().size(); i++) {
					ancienne = partie.getJoueursParties().get(i).getMainDe();
					partie.getJoueursParties().get(i).setMainDe(nouvelle);
					joueurPartieDao.mettreAJour(partie.getJoueursParties().get(i));
					nouvelle = ancienne;
				}
			} else {
				// voisin de droite
				List<De> ancienne;
				List<De> nouvelle = partie.getJoueursParties().get(0).getMainDe();
				System.out.println("*****************    Je suis a droite ******************");
				for (int i = partie.getJoueursParties().size() - 1; i >= 0; i--) {
					ancienne = partie.getJoueursParties().get(i).getMainDe();
					partie.getJoueursParties().get(i).setMainDe(nouvelle);
					joueurPartieDao.mettreAJour(partie.getJoueursParties().get(i));
					nouvelle = ancienne;
				}
			}
			break;
		case "3":
			// Supprimez 2 de vos des
			partie.getJoueurCourant().supprimerDe();
			partie.getJoueurCourant().supprimerDe();
			break;
		case "4":
			// Donnez 1 de vos des au joueur de votre choix
			donnerDe(partie.getJoueurCourant().getJoueur().getPseudo(), cible);
			break;
		case "5":
			// Prenez 1 carte au joueur de votre choix
			String pseudoCible = cible;
			Joueur tmp = joueurDao.recherche(pseudoCible);
			JoueurPartie jpCible = joueurPartieDao.recherche(this.partie, tmp);
			Random rdm = new Random();
			int index = rdm.nextInt(jpCible.getMainCarte().size());
			Carte cartePrise = jpCible.getMainCarte().remove(index);
			partie.getJoueurCourant().getMainCarte().add(cartePrise);
			joueurPartieDao.mettreAJour(jpCible);
			break;
		case "6":
			// Le joueur de votre choix n a plus que une carte
			String pseudoCible2 = cible;
			Joueur tmp2 = joueurDao.recherche(pseudoCible2);
			JoueurPartie jpCible2 = joueurPartieDao.recherche(this.partie, tmp2);

			List<Carte> main = jpCible2.getMainCarte();
			Random rdm2 = new Random();
			int index2 = rdm2.nextInt(main.size());
			Carte carteRestante = main.remove(index2);
			List<Carte> nouvelleMain = new ArrayList<Carte>();
			nouvelleMain.add(carteRestante);
			jpCible2.setMainCarte(nouvelleMain);

			joueurPartieDao.mettreAJour(jpCible2);
			// on remet la "main" dans la pioche de la partie
			partie.remettreCartesDansPioche(main);
			partieDao.mettreAJour(partie);

			break;
		case "7":
			// Piochez 3 cartes dans la pioche
			List<Carte> nouvellesCartes = new ArrayList<Carte>();
			nouvellesCartes.add(partie.piocherCarte());
			nouvellesCartes.add(partie.piocherCarte());
			nouvellesCartes.add(partie.piocherCarte());
			JoueurPartie tmp3 = partie.getJoueurCourant();
			tmp3.getMainCarte().addAll(nouvellesCartes);
			joueurPartieDao.mettreAJour(tmp3);
			break;
		case "8":
			// Tous les joueurs sauf vous n ont plus que 2 cartes
			List<Carte> mainRestante;
			for (JoueurPartie jp : partie.getJoueursParties()) {
				if (jp.equals(partie.getJoueurCourant()) || jp.getMainCarte().size() <= 2)
					continue;
				mainRestante = jp.getMainCarte();
				int nbARetirer = mainRestante.size() - 2;
				List<Carte> carteARemettre = new ArrayList<>();
				Random rdm3 = new Random();
				for (; nbARetirer > 0; nbARetirer--) {
					carteARemettre.add(jp.getMainCarte().remove(rdm3.nextInt(nbARetirer)));
				}
				partie.remettreCartesDansPioche(carteARemettre);
				joueurPartieDao.mettreAJour(jp);
			}
			break;
		case "9":
			// Le joueur de votre choix passe son tour
			String pseudoCible3 = cible;
			Joueur tmp4 = joueurDao.recherche(pseudoCible3);
			JoueurPartie jpCible3 = joueurPartieDao.recherche(this.partie, tmp4);
			partie.passeSonTour(jpCible3);
			break;
		case "10":
			// Rejouez et changement de sens
			partie.joueurCourantRejoue();
			partie.changerSens();
			break;

		}
		Carte carteJouee = partie.getJoueurCourant().supprimerCarte(Integer.parseInt(codeEffet));
		partie.remettreCarte(carteJouee);
		joueurPartieDao.mettreAJour(partie.getJoueurCourant());
		partieDao.mettreAJour(partie);
		Info info = new Info();
		info.setCartes(partie.getJoueurCourant().getMainCarte());
		info.setDes(partie.getJoueurCourant().getMainDe());
		info.setEtat(partie.getEtat());
		info.setJoueurCourant(partie.getJoueurCourant().getJoueur().getPseudo());
		List<String> listeJoueurs = new ArrayList<String>();
		for (JoueurPartie j : partie.getJoueursParties()) {
			listeJoueurs.add(j.getJoueur().getPseudo());
		}
		info.setJoueurs(listeJoueurs);
		info.setUser(partie.getJoueurCourant().getJoueur().getPseudo());
		info.setVainqueur("");
		return info;
	}

	@Override
	public List<Joueur> getJoueurs() {
		if (partie == null || partie.getEtat() == Etat.FINIE)
			return null;
		// return joueurDao.listerJoueurs(partie.getId());
		partie = partieDao.recharger(partie.getId());
		partieDao.chargerPartie(partie);
		List<Joueur> liste = new ArrayList<>();
		for (JoueurPartie jp : partie.getJoueursParties()) {
			joueurPartieDao.chargerJoueur(jp);
			liste.add(jp.getJoueur());
			joueurDao.chargerJoueursParties(jp.getJoueur());
		}
		return liste;
	}

	public int getNombreDe(String pseudo) {
		Joueur tmp = joueurDao.recherche(pseudo);
		JoueurPartie jp = joueurPartieDao.recherche(this.partie, tmp);
		return jp.getMainDe().size();
	}

	@Override
	public Partie getDernierePartie() {
		return partieDao.getDernier();
	}

	@Override
	public JoueurPartie getJoueurPartie(String pseudo) {
		Joueur tmp = joueurDao.recherche(pseudo);
		JoueurPartie jp = joueurPartieDao.recherche(this.partie, tmp);
		return joueurPartieDao.chargerMain(jp);
	}
	@Override	
	public List<De> lancerDes2(JoueurPartie jp) {
		Random rdm = new Random();
		for (De de : jp.getMainDe()) {
			de = deDao.recharger(de.getId());
			int res = rdm.nextInt(6);
			switch (res) {
			case 0:
			case 1:
			case 2:
				de.setValeur(Face.WASABI);
				break;

			case 3:
			case 4:
				de.setValeur(Face.CARTE);
				break;

			default:
				de.setValeur(Face.DE);
			}
			deDao.mettreAJour(de);
		}
		return jp.getMainDe();
	}

	@Override
	public Info lancerDes() {
		Info info = new Info();
		partie = partieDao.rechercher(getDernierePartie().getId());
		partie = partieDao.chargerPartie(partie);
		JoueurPartie jp = partie.getJoueurCourant();
		jp = joueurPartieDao.rechercher(jp.getId_joueurPartie());
		jp = joueurPartieDao.chargerJoueur(jp);
		System.out.println("************************** JOUEUR COURANT = " + jp.getId_joueurPartie());
		
		jp.setMainDe(lancerDes2(jp));
		
		jp.getMainDe().size();
		joueurPartieDao.mettreAJour(jp);
		
		for (De de : jp.getMainDe()) {
			if (de.getValeur().equals("c"))
				piocherCartes();
		}
		info.setCartes(jp.getMainCarte());
		info.setDes(jp.getMainDe());
		info.setEtat(partie.getEtat());
		info.setJoueurCourant(joueurCourant());
		List<String> listeJoueurs = new ArrayList<String>();
		for (JoueurPartie j : partie.getJoueursParties()) {
			listeJoueurs.add(j.getJoueur().getPseudo());
		}
		info.setJoueurs(listeJoueurs);
		info.setUser(jp.getJoueur().getPseudo());
		info.setVainqueur("");
		return info;
	}
	@Override
	public void definirVainqueur(String pseudo){
		partie = partieDao.rechercher(getDernierePartie().getId());
		partie = partieDao.chargerPartie(partie);
		Joueur j = joueurDao.recherche(pseudo);
		partie.setVainqueur(j);
		partie.setEtat(Etat.FINIE);
		partieDao.mettreAJour(partie);
	}
	
	@Override
	public void piocherCartes() {
		partie = partieDao.rechercher(getDernierePartie().getId());
		partie = partieDao.chargerPartie(partie);		
		JoueurPartie jp = partie.getJoueurCourant();
		jp = joueurPartieDao.rechercher(jp.getId_joueurPartie());
		jp = joueurPartieDao.chargerJoueur(jp);		
		jp.getMainCarte().add(partie.piocherCarte());
		joueurPartieDao.mettreAJour(jp);
		partieDao.mettreAJour(partie);
	}

}
