package daoimpl;

import javax.ejb.Stateless;

import domaine.Joueur;
import domaine.JoueurPartie;
import domaine.Partie;

@SuppressWarnings("serial")
@Stateless
public class JoueurPartieDaoImpl extends DaoImpl<Integer, JoueurPartie> {

	public JoueurPartieDaoImpl() {
		super(JoueurPartie.class);
	}

	public JoueurPartie recherche(Partie partie, Joueur joueur) {
		String queryString = "SELECT jp from JoueurPartie jp  "
				+ "WHERE jp.partie =?1 AND jp.joueur = ?2";
		return super.recherche(queryString, partie, joueur);
	}

	public boolean possede(int id_de, int id_joueur_partie) {
		String queryString = "SELECT jpd from joueursparties_des jpd "
				+ "WHERE jpd.id_de=?1 AND jpd.id_joueur_partie =?2";
		return (super.recherche(queryString, id_de, id_joueur_partie) != null);
	}
	
	public JoueurPartie chargerMain(JoueurPartie joueurPartie) {
		System.out.println("**************** " + joueurPartie);

		joueurPartie = rechercher(joueurPartie.getId_joueurPartie());
		joueurPartie.getMainCarte().size();
		joueurPartie.getMainDe().size();
		return joueurPartie;
	}

	public JoueurPartie chargerJoueur(JoueurPartie joueurPartie) {
		System.out.println("**************** " + joueurPartie);
		joueurPartie = rechercher(joueurPartie.getId_joueurPartie());
		joueurPartie.getJoueur();
		return joueurPartie;
	}
	
}
