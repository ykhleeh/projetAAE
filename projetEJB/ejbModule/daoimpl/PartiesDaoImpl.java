package daoimpl;

import javax.ejb.Stateless;

import domaine.Partie;

@SuppressWarnings("serial")
@Stateless
public class PartiesDaoImpl extends DaoImpl<Integer, Partie> {

	public PartiesDaoImpl() {
		super(Partie.class);
	}
	
	public Partie getDernier(){
		return recherche("select x from koala.parties x where x.id_partie = (select max(id_partie) from koala.parties)");
	}


}
