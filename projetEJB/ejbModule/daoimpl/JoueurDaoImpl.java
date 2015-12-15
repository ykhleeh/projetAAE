package daoimpl;

import domaine.Joueur;

public class JoueurDaoImpl extends DaoImpl<Integer, Joueur> {

	public JoueurDaoImpl() {
		super(Joueur.class);
	}

	public Joueur recherche(String pseudo) {
		Joueur aRenvoyer;
		String queryString = "SELECT * from Joueur j where j.pseudo = ?";
		return (Joueur) super.recherche(queryString, pseudo);
	}

}
