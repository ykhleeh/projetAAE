package daoimpl;

import domaine.Joueur;

@SuppressWarnings("serial")
public class JoueurDaoImpl extends DaoImpl<Integer, Joueur> {

	public JoueurDaoImpl() {
		super(Joueur.class);
	}

	public Joueur recherche(String pseudo) {
		String queryString = "SELECT * from Joueur j where j.pseudo = ?";
		return (Joueur) super.recherche(queryString, pseudo);
	}

	public void ajouter(Joueur j){
		enregistrer(j);
	}
	
}
