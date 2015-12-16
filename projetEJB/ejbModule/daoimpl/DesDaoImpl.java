package daoimpl;

import javax.ejb.Stateless;

import domaine.De;


@SuppressWarnings("serial")
@Stateless
public class DesDaoImpl extends DaoImpl<Integer, De> {
	
	public DesDaoImpl() {
		super(De.class);
	}
	
}
