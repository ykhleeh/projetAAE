package usecasesimpl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import daoimpl.JoueurDaoImpl;
import domaine.Joueur;
import usecases.GestionJoueurs;

@Stateless
public class GestionJoueursImpl implements GestionJoueurs {
	@EJB
	JoueurDaoImpl joueurDao;
	
	@Override
	public Joueur enregistrer(Joueur joueur) {
		String encryptedPwd = null; 
		try {
			encryptedPwd = util.PasswordSHA1.getEncryptedPassword(joueur.getMdp());
			
			System.out.println("Enreg " + joueur.getMdp() + "  :  " +encryptedPwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		joueur.setMdp(encryptedPwd);
		return joueurDao.enregistrer(joueur);
	}

	@Override
	public Joueur rechercherJoueur(String login) {
		Joueur joueur = joueurDao.recherche(login);
		return joueur;
	}
	@Override
	public boolean authentifier(String pseudo, String mdp){
		Joueur db = rechercherJoueur(pseudo);
		try {
			String encryptedPwd = util.PasswordSHA1.getEncryptedPassword(mdp);
			return db.getMdp().equals(encryptedPwd);
			// return util.PasswordSHA1.authenticate(mdp, db.getMdp());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Joueur> listerPseudos() {
		return joueurDao.lister();
	}

}
