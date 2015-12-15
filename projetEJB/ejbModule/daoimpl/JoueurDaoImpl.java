package daoimpl;

import javax.ejb.Stateless;

import domaine.Joueur;

@SuppressWarnings("serial")
@Stateless
public class JoueurDaoImpl extends DaoImpl<Integer, Joueur> {

	public JoueurDaoImpl() {
		super(Joueur.class);
	}

	public Joueur recherche(String pseudo) {
		String queryString = "select j from Joueur j where j.pseudo = ?1";
		return (Joueur) super.recherche(queryString, pseudo);
	}

	public void ajouter(Joueur j){
		enregistrer(j);
	}
	
}
