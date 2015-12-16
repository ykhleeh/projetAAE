package usecasesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import daoimpl.DesDaoImpl;
import daoimpl.JoueurPartieDaoImpl;
import domaine.De;
import domaine.JoueurPartie;
import usecases.GestionDes;

@Stateless
public class GestionDesImpl implements GestionDes {

	@EJB
	DesDaoImpl deDao;

	@EJB
	JoueurPartieDaoImpl jpDao;

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

	@Override
	public De modifierValeurDe(De de) {
		De tmp = deDao.rechercher(de.getId());
		deDao.supprimer(de.getId());
		tmp.setValeur(de.getValeur());
		deDao.enregistrer(tmp);
		return tmp;
	}

	@Override
	public boolean donnerDe(De de, JoueurPartie joueurPart) {
		
		// TODO faire la meme chose que ce qui suit mais sans passer par un proprietaire dans le de
		
		De tmp = deDao.rechercher(de.getId());
//		JoueurPartie ancienProp = tmp.getProprietaire();
//		JoueurPartie jp = jpDao.rechercher(joueurPart.getId_joueurPartie());
//		tmp.setProprietaire(jp);
//		jp.ajouterDe(de);
//		if (ancienProp != null)
//			ancienProp.supprimerDe(tmp);
		return false;
	}
}
