package usecasesimpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;

import daoimpl.JoueurDaoImpl;
import daoimpl.PartiesDaoImpl;
import domaine.Joueur;
import domaine.ObjectFactory;
import domaine.Partie;
import domaine.Partie.Etat;
import usecases.GestionParties;

public class GestionPartiesImpl implements GestionParties {
	private Partie partie;
	private static int num = 0;

	@EJB
	JoueurDaoImpl joueurDao;
	@EJB
	PartiesDaoImpl partieDao;

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
		if (partie != null && partie.getEtat() == Etat.EN_COURS)
			return false;
		if (partie == null || partie.getEtat() == Etat.FINIE) {
			partie = new Partie("partie" + num);
			num++;
			partie = partieDao.enregistrer(partie);
		}

		partie = partieDao.rechercher(partie.getId());

		Joueur joueur = joueurDao.recherche(pseudo);
		if (joueur == null) {
			// joueur = new Joueur(pseudo);
			joueur = joueurDao.enregistrer(joueur);
		}
		return partie.ajouterJoueur(joueur);

	}

	@Override
	public Partie creer(Partie partie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Partie enregistrerVainqueur(Partie partie, Joueur vainqueur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Partie> listerParties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String joueurCourant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean auSuivant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estFinie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void annulerPartie() {
		// TODO Auto-generated method stub

	}

	@Override
	public Etat getEtatPartie() {
		// TODO Auto-generated method stub
		return null;
	}

}
