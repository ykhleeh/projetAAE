package usecases;

import java.util.List;

import javax.ejb.Remote;

import domaine.Joueur;
import domaine.Partie;
import domaine.Partie.Etat;

@Remote
public interface GestionParties {
	boolean rejoindreLaPartie(String pseudo);

	Partie creer(Partie partie);

	List<Partie> listerParties();

	String joueurCourant();

	boolean auSuivant();

	boolean estFinie();

	void annulerPartie();

	Etat getEtatPartie();

	boolean commencerPartie();

	String vainqueur();
}
