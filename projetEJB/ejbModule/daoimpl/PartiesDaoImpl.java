package daoimpl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import domaine.Partie;

@Stateless
public class PartiesDaoImpl extends DaoImpl<Integer, Partie> {

	public PartiesDaoImpl() {
		super(Partie.class);
	}

}
