package usecases;

import java.util.List;

import domaine.De;
import domaine.Joueur;
import domaine.JoueurPartie;

public interface GestionDes {
	De enregistrer (De de);
	De modifierValeurDe (De de);
	List<De> listerDes();
	List<De> listerDes(JoueurPartie joueur);
	void supprimerDe(De de);
	boolean donnerDe(De de, JoueurPartie joueurPart);
}
