package daoimpl;

import java.util.List;

import javax.ejb.Stateless;

import domaine.Carte;
import domaine.Joueur;
import domaine.JoueurPartie;

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
	
	public List<Joueur> listerJoueurs(int id_partie) {
		String queryString = "SELECT j.* "
				+ "FROM joueursparties jp, joueurs j, parties p "
				+ "WHERE jp.id_partie = p.id_partie "
				+ "AND jp.id_joueur= j.id_joueur "
				+ "AND j.pseudo= ? ";
		return super.liste(queryString, id_partie);
	}
	
}
