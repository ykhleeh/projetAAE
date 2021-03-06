package usecasesimpl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import daoimpl.CartesDaoImpl;
import domaine.Carte;
import usecases.GestionCartes;

@Stateless
public class GestionCartesImpl implements GestionCartes {
	
	@EJB CartesDaoImpl carteDao;
	
	@Override
	public Carte enregistrer(Carte carte) {
		return carteDao.enregistrer(carte);
	}

	@Override
	public Carte rechercherCarte(int id) {
		return carteDao.rechercher(id);
	}

	@Override
	public List<Carte> listerCartes() {
		return carteDao.lister();
	}

}
