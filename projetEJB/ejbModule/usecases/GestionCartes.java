package usecases;

import java.util.List;

import domaine.Carte;

public interface GestionCartes {
	Carte enregistrer (Carte carte);
	Carte rechercherCarte (int id);
	List<Carte> listerCartes();
}
