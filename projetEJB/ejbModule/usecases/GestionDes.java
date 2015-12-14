package usecases;

import java.util.List;

import domaine.De;
import domaine.Joueur;

public interface GestionDes {
	De enregistrer (De de);
	List<De> listerDes();
	List<De> listerDes(Joueur joueur);
	void supprimerDe(De de);
}
