package usecasesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import daoimpl.DesDaoImpl;
import domaine.De;
import domaine.JoueurPartie;
import usecases.GestionDes;

public class GestionDesImpl implements GestionDes {

	@EJB DesDaoImpl deDao;
	
	@Override
	public De enregistrer(De de) {
		return deDao.enregistrer(de);
	}

	@Override
	public List<De> listerDes() {
		return deDao.lister();
	}

	@Override
	public List<De> listerDes(JoueurPartie joueur) {
		List<De> liste = new ArrayList<De>();
		for (De de : joueur.getMainDe()) {
			liste.add(deDao.rechercher(de.getId()));
		}
		return liste;
	}

	@Override
	public void supprimerDe(De de) {
		deDao.supprimer(de.getId());
	}
}
