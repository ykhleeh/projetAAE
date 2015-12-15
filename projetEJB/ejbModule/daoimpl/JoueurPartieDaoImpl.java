package daoimpl;

import domaine.Joueur;
import domaine.JoueurPartie;

public class JoueurPartieDaoImpl extends DaoImpl<Integer, JoueurPartie> {

	public JoueurPartieDaoImpl() {
		super(JoueurPartie.class);
	}

	public JoueurPartie recherche(String pseudo) {
		String queryString = "SELECT jp.* from joueurs j, joueursparties jp where j.pseudo = ? AND jp.joueur_id_joueur =j.id_joueur";
		return super.recherche(queryString, pseudo);
	}
}
