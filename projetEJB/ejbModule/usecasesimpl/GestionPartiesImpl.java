package usecasesimpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import daoimpl.CartesDaoImpl;
import daoimpl.JoueurDaoImpl;
import daoimpl.JoueurPartieDaoImpl;
import daoimpl.PartiesDaoImpl;
import domaine.Carte;
import domaine.De;
import domaine.Face;
import domaine.Joueur;
import domaine.JoueurPartie;
import domaine.ObjectFactory;
import domaine.Partie;
import domaine.Partie.Etat;
import usecases.GestionParties;

@Stateless
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
	CartesDaoImpl deDao;

	@PostConstruct
	public void postconstruct() {
		System.out.println("GestionPartieImpl created");
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
			joueurPart.setJoueur(joueurDao.recherche(pseudo));
			joueurPart = joueurPartieDao.enregistrer(joueurPart);
		}
		return partie.ajouterJoueurPartie(joueurPart);

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
		JoueurPartie jp = this.joueurPartieDao.recherche(this.partie.getId(), pseudo);
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
				List<De> nouvelle = partie.getJoueurs().get(partie.getJoueurs().size() - 1).getMainDe();
				for (int i = 0; i < partie.getJoueurs().size(); i++) {
					ancienne = partie.getJoueurs().get(i).getMainDe();
					partie.getJoueurs().get(i).setMainDe(nouvelle);
				}
			} else {
				List<De> ancienne;
				List<De> nouvelle = partie.getJoueurs().get(0).getMainDe();
				for (int i = partie.getJoueurs().size(); i > 0; i--) {
					ancienne = partie.getJoueurs().get(i).getMainDe();
					partie.getJoueurs().get(i).setMainDe(nouvelle);
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
		return joueurDao.listerJoueurs(partie.getId());
	}
	
	public int getNombreDe(String pseudo) {
		JoueurPartie jp = this.joueurPartieDao.recherche(this.partie.getId(), pseudo);
		return jp.getMainDe().size();
	}

	@Override
	public Partie getDernierePartie() {
		return partieDao.getDernier();
	}

}
