package daoimpl;

import domaine.Joueur;
import domaine.JoueurPartie;

public class JoueurPartieDaoImpl extends DaoImpl<Integer, JoueurPartie> {

	public JoueurPartieDaoImpl() {
		super(JoueurPartie.class);
	}

	public JoueurPartie recherche(int id_partie, String pseudo) {
		String queryString = "SELECT jp.* from joueursparties jp, joueurs j "
				+ "WHERE jp.id_partie =? AND j.pseudo =? AND jp.joueur_id_joueur = j.id_joueur";
		return super.recherche(queryString, id_partie, pseudo);
	}
}