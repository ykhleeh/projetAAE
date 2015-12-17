package usecases;

import java.util.List;

import javax.ejb.Remote;

import domaine.Joueur;
import domaine.JoueurPartie;
import domaine.Partie;
import domaine.Partie.Etat;
import domaine.Carte;

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

	boolean partieEnCours();

	Partie creer(String nom);

	List<Carte> getCartesJoueur(String pseudo);

	boolean donnerDe(String JoueurDonneur, String joueurReceveur);

	List<Joueur> getJoueurs();
	
	Partie getDernierePartie();
	
	boolean jouerCarte(int codeEffet, Object... params);

	JoueurPartie getJoueurPartie(String pseudo);
	
	
}
