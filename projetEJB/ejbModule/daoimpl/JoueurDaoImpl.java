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
		String queryString = "SELECT j.* from joueurs j where j.pseudo = ?";
		return (Joueur) super.recherche(queryString, pseudo);
	}

	public void ajouter(Joueur j){
		enregistrer(j);
	}
	
}
