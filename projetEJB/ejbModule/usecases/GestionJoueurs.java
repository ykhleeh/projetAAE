package usecases;

import domaine.Joueur;

public interface GestionJoueurs {
	Joueur enregistrer (Joueur joueur);
	Joueur rechercherJoueur (String login);
	boolean authentifier(Joueur j);
}
