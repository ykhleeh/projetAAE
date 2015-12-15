package usecases;

import javax.ejb.Remote;

import domaine.Joueur;

@Remote
public interface GestionJoueurs {
	Joueur enregistrer (Joueur joueur);
	Joueur rechercherJoueur (String login);
	boolean authentifier(String pseudo, String mdp);
}
