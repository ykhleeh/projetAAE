package daoimpl;

import javax.ejb.Stateless;

import domaine.JoueurPartie;

@SuppressWarnings("serial")
@Stateless
public class JoueurPartieDaoImpl extends DaoImpl<Integer, JoueurPartie> {

	public JoueurPartieDaoImpl() {
		super(JoueurPartie.class);
	}

	public JoueurPartie recherche(int id_partie, String pseudo) {
		String queryString = "SELECT jp.* from joueursparties jp, joueurs j "
				+ "WHERE jp.id_partie =? AND j.pseudo =? AND jp.joueur_id_joueur = j.id_joueur";
		return super.recherche(queryString, id_partie, pseudo);
	}

	public boolean possede(int id_de, int id_joueur_partie) {
		String queryString = "SELECT jpd.* from joueursparties_des jpd "
				+ "WHERE jpd.id_de=? AND jpd.id_joueur_partie =?";
		return (super.recherche(queryString, id_de, id_joueur_partie) != null);
	}
	
}
