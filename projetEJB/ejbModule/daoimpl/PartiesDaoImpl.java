package daoimpl;

import javax.ejb.Stateless;

import domaine.Partie;

@SuppressWarnings("serial")
@Stateless
public class PartiesDaoImpl extends DaoImpl<Integer, Partie> {

	public PartiesDaoImpl() {
		super(Partie.class);
	}

}
