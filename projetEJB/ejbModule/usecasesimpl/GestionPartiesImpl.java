package usecasesimpl;

import java.util.ArrayList;
import java.util.List;

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

	@PostConstruct
	public void postconstruct() {
		ObjectFactory o = new ObjectFactory();
		JDOM dom = new JDOM();
		Wazabi jeu = dom.getJeu();
		for (Carte c : jeu.getCarte()){
			for (int i=0; i<c.getNb(); i++){
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
		for (int i=0; i<de.getNbTotalDes(); i++){
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
		boolean aRenvoyer = false;
		List<Partie> parties = partieDao.lister();
		for (Partie p : parties) {
			if (p.getEtat() == Etat.INITIAL) {
				this.partie = p;
				aRenvoyer = true;
			}
		}
		if (!aRenvoyer)
			return false;

		partie = partieDao.rechercher(partie.getId());
		JoueurPartie joueurPart = joueurPartieDao.recherche(partie.getId(), pseudo);
		if (joueurPart == null) {
			joueurPart = objFact.createJoueurPartie();
			joueurPart.setId_partie(partie);
			joueurPart.setJoueur(joueurDao.recherche(pseudo));
			joueurPart = joueurPartieDao.enregistrer(joueurPart);
			List<Carte> main = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				int index = (int) (Math.random() * partie.pioche.size());
				main.add(partie.pioche.remove(index));
			}
			joueurPart.setMainCarte(main);
		}
		
		//partie.ajouterJoueurPartie(joueurPart);
		joueurPartieDao.mettreAJour(joueurPart);
		partieDao.mettreAJour(partie);
		return true;
	}

	@Override
	public boolean partieEnCours() {
		List<Partie> parties = partieDao.lister();
		for (Partie p : parties) {
			if (p.getEtat() != Etat.FINIE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Partie creer(Partie partie) {
		this.partie = partie;
		partie.setPioche(carteDao.lister());
		return partieDao.enregistrer(partie);
		
	}

	@Override
	public Partie creer(String nom) {
		Partie nouvelle = objFact.createPartie();
		nouvelle.setNom(nom);
		return this.creer(nouvelle);
	}

	@Override
	public boolean commencerPartie() {
		if (partie == null || partie.getEtat() != Etat.INITIAL)
			return false;
		partie = partieDao.rechercher(partie.getId());
		return partie.commencerPartie();
	}

	@Override
	public String vainqueur() {
		if (partie == null)
			return null;
		partie = partieDao.rechercher(partie.getId());
		Joueur v = partie.estVainqueur();
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
		return partie.getJoueurCourant().getJoueur().getPseudo();
	}

	@Override
	public boolean auSuivant() {
		if (partie == null)
			return false;
		partie = partieDao.rechercher(partie.getId());
		return partie.commencerTourSuivant();
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
		if (partie != null)
			partie.annuler();
	}

	@Override
	public Etat getEtatPartie() {
		return partie.getEtat();
	}

	@Override
	public List<Carte> getCartesJoueur(String pseudo) {
		partie = partieDao.getDernier();
		System.out.println("****************************** get cartes" +"id parie"+ this.partie.getId() +"******************************");
		JoueurPartie jp = 
				joueurPartieDao.recherche(this.partie.getId(), pseudo);
		System.out.println("****************************** " + jp.getId_joueurPartie() + " ******************************");
		
		return carteDao.lister(jp);
	}

	@Override
	public boolean donnerDe(String joueurDonneur, String joueurReceveur) {
		JoueurPartie joueurDon = joueurPartieDao.recherche(partie.getId(), joueurDonneur);
		JoueurPartie joueurRecev = joueurPartieDao.recherche(partie.getId(), joueurReceveur);
		De aDonner = joueurDon.supprimerDe();
		joueurRecev.ajouterDe(aDonner);
		joueurPartieDao.mettreAJour(joueurDon);
		joueurPartieDao.mettreAJour(joueurRecev);
		return false;
	}

	@Override
	public boolean jouerCarte(int codeEffet, Object... params) {
		switch (codeEffet) {
		case 1:
			// Supprimez 1 de vos dés
			// On ne peut pas jouer cette carte si on obtient dans le même tour
			// 2 figures
			int nbFigure = 0;
			for (De de : partie.getJoueurCourant().getMainDe()) {
				if (de.getValeur().equals(Face.DE))
					nbFigure++;
			}
			if (nbFigure >= 2) {
				return false;
			}
			JoueurPartie tmp = partie.getJoueurCourant();
			tmp.supprimerDe();
			joueurPartieDao.mettreAJour(tmp);
			return true;
		case 2:
			// tous les joueurs donnent leurs des à leur voisin de droite ou de
			// gauche

			if ((String) params[0] == "g") {
				List<De> ancienne;
				List<De> nouvelle = partie.getJoueursParties().get(partie.getJoueursParties().size() - 1).getMainDe();
				for (int i = 0; i < partie.getJoueursParties().size(); i++) {
					ancienne = partie.getJoueursParties().get(i).getMainDe();
					partie.getJoueursParties().get(i).setMainDe(nouvelle);
				}
			} else {
				List<De> ancienne;
				List<De> nouvelle = partie.getJoueursParties().get(0).getMainDe();
				for (int i = partie.getJoueursParties().size(); i > 0; i--) {
					ancienne = partie.getJoueursParties().get(i).getMainDe();
					partie.getJoueursParties().get(i).setMainDe(nouvelle);
				}
			}
			return true;
		case 3:
			JoueurPartie tmp1 = partie.getJoueurCourant();
			tmp1.supprimerDe();
			tmp1.supprimerDe();
			joueurPartieDao.mettreAJour(tmp1);
			break;
		case 4:
			// Donnez 1 de vos dés au joueur de votre choix
			break;
		case 5:
			// Prenez 1 carte au joueur de votre choix
			break;
		case 6:
			// Le joueur de votre choix n’a plus qu’1 carte
			break;
		case 7:
			// Piochez 3 cartes dans la pioche
			break;
		case 8:
			// Tous les joueurs sauf vous n’ont plus que 2 cartes
			break;
		case 9:
			// Le joueur de votre choix passe son tour
			break;
		case 10:
			// Rejouez et changement de sens
			
		//	this.partie.
			break;

		}

		Carte carte = this.carteDao.recherche(this.partie.getJoueurCourant(), codeEffet);
		partie.jouerCarte(carte);
		joueurPartieDao.mettreAJour(partie.getJoueurCourant());
		partieDao.mettreAJour(partie);
		return false;
	}

	@Override
	public List<Joueur> getJoueurs() {
		if (partie.getEtat() == Etat.FINIE)
			return null;
		//return joueurDao.listerJoueurs(partie.getId());
		partie = partieDao.recharger(partie.getId());
		partieDao.chargerJoueurs(partie);
		List<Joueur> liste = new ArrayList<>();
		for(JoueurPartie jp : partie.getJoueursParties()){
			joueurPartieDao.chargerJoueur(jp);
			liste.add(jp.getJoueur());
			joueurDao.chargerJoueursParties(jp.getJoueur());
		}
		return liste;
	}
	
	
	
	public int getNombreDe(String pseudo) {
		JoueurPartie jp = this.joueurPartieDao.recherche(this.partie.getId(), pseudo);
		return jp.getMainDe().size();
	}

	@Override
	public Partie getDernierePartie() {
		return partieDao.getDernier();
	}
	
	@Override
	public JoueurPartie getJoueurPartie(String pseudo){
		JoueurPartie jp = joueurPartieDao.recherche(getDernierePartie().getId(), pseudo);
		return joueurPartieDao.chargerMain(jp);
	}

	@Override
	public Info lancerDes() {
		Info info = new Info();
		JoueurPartie jp = partie.getJoueurCourant();
		jp.lancerDes();
		List<De> des = jp.getMainDe();
		joueurPartieDao.mettreAJour(jp);
		for (De de : jp.getMainDe()) {
			if (de.getValeur().equals("p")) 
				piocherCartes();
		}
		info.setCartes(jp.getMainCarte());
		info.setDes(jp.getMainDe());
		info.setEtat(partie.getEtat());
		info.setJoueurCourant(jp.getJoueur().getPseudo());
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
	public void piocherCartes() {
		JoueurPartie jp = partie.getJoueurCourant();
		jp.getMainCarte().add(partie.piocherCarte());
		joueurPartieDao.mettreAJour(jp);
		partieDao.mettreAJour(partie);
	}

}
