package usecases;

import java.util.List;

import javax.ejb.Remote;

import domaine.Joueur;
import domaine.JoueurPartie;
import domaine.Partie;
import domaine.Partie.Etat;
import domaine.Carte;
import domaine.De;
import domaine.Info;

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

	Partie creer(String nom, String pseudo);

	List<Carte> getCartesJoueur(String pseudo);

	boolean donnerDe(String JoueurDonneur, String joueurReceveur);

	List<Joueur> getJoueurs();
	
	Partie getDernierePartie();
	
	Info jouerCarte(String code, String cible);

	JoueurPartie getJoueurPartie(String pseudo);
	
	Info lancerDes();
	
	void piocherCartes();

	void initialiser();
	
}
