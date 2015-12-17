package daoimpl;

import java.util.List;

import javax.ejb.Stateless;

import domaine.Carte;
import domaine.Joueur;
import domaine.JoueurPartie;
import util.JDOM;

@SuppressWarnings("serial")
@Stateless
public class CartesDaoImpl extends DaoImpl<Integer, Carte>{
	
	public CartesDaoImpl() {
		super(Carte.class);
		
	}

	public Carte piocher() {
		
		return null;
	}
	
	

	public List<Carte> lister(JoueurPartie jp) {
		String queryString = "SELECT c "
				+ "FROM Carte c left outer join JoueurPartie jp "
				+ "WHERE c.id_carte = :id_carte AND jp.id_joueurpartie = ?1";
		System.out.println("**************************** " + jp.getId_joueurPartie() + " ********************************");
		return super.liste(queryString, jp.getId_joueurPartie());
	}
	
	public Carte recherche(JoueurPartie jp, int codeEffet) {
		String queryString = "SELECT c "
				+ "FROM cartes c, joueursparties_cartes jpc "
				+ "WHERE jpc.id_carte = c.id_carte "
				+ "AND c.codeeffet = ?1 "
				+ "AND jpc.id_joueurpartie = ?2";
		return (Carte) super.recherche(queryString, codeEffet, jp.getId_joueurPartie());
	}

}
