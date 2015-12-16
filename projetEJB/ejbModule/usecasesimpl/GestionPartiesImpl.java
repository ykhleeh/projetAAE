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
	public List<Carte> getCarteJoueur(String pseudo) {
		JoueurPartie jp = this.joueurPartieDao.recherche(this.partie.getId(), pseudo);
		return carteDao.lister(jp);
	}

}
