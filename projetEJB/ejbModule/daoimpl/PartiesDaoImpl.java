package daoimpl;

import javax.ejb.Stateless;

import domaine.JoueurPartie;
import domaine.Partie;

@SuppressWarnings("serial")
@Stateless
public class PartiesDaoImpl extends DaoImpl<Integer, Partie> {

	public PartiesDaoImpl() {
		super(Partie.class);
	}
	
	public Partie getDernier(){
		return recherche("select x from Partie x where x.id_partie = (select max(id_partie) from Partie)");
	}
	
	public Partie chargerJoueurs(Partie partie) {
		partie = rechercher(partie.getId());
		partie.getJoueursParties().size();
		return partie;
	}


}
