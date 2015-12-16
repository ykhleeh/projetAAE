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
		String queryString = "SELECT jp from JoueurPartie jp, Joueur j "
				+ "WHERE jp.id_joueurPartie =?1 AND j.pseudo =?2 AND jp.id_joueurPartie = j.id_joueur";
		return super.recherche(queryString, id_partie, pseudo);
	}

	public boolean possede(int id_de, int id_joueur_partie) {
		String queryString = "SELECT jpd from joueursparties_des jpd "
				+ "WHERE jpd.id_de=?1 AND jpd.id_joueur_partie =?2";
		return (super.recherche(queryString, id_de, id_joueur_partie) != null);
	}
	
}
