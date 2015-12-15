package usecases;

import java.util.List;

import domaine.De;
import domaine.Joueur;
import domaine.JoueurPartie;

public interface GestionDes {
	De enregistrer (De de);
	List<De> listerDes();
	List<De> listerDes(JoueurPartie joueur);
	void supprimerDe(De de);
}
