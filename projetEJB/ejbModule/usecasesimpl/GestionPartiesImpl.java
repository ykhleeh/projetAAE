package usecasesimpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import daoimpl.JoueurDaoImpl;
import daoimpl.JoueurPartieDaoImpl;
import daoimpl.PartiesDaoImpl;
import domaine.Joueur;
import domaine.JoueurPartie;
import domaine.ObjectFactory;
import domaine.Partie;
import domaine.Partie.Etat;
import usecases.GestionParties;

@Stateless
public class GestionPartiesImpl implements GestionParties {
	private Partie partie;
	private static int num = 0;
	private ObjectFactory objFact = new ObjectFactory();

	@EJB
	JoueurDaoImpl joueurDao;
	@EJB
	PartiesDaoImpl partieDao;
	@EJB
	JoueurPartieDaoImpl joueurPartieDao;

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

			partie = objFact.createPartie();
			partie.setNom("partie" + num);
			num++;
			partie = partieDao.enregistrer(partie);
		}
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
	public Partie creer(Partie partie) {
		this.partie = partie;
		return partieDao.enregistrer(partie);
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

}
