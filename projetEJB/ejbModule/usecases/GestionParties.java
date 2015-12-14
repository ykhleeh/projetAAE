package usecases;

import java.util.List;

import domaine.Joueur;
import domaine.Partie;

public interface GestionParties {
	Partie enregistrer (Partie partie);
	Partie EnregistrerVainqueur (Partie partie, Joueur vainqueur);
	List<Partie> listerParties();
}
