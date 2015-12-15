package usecasesimpl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import daoimpl.JoueurDaoImpl;
import domaine.Joueur;
import usecases.GestionJoueurs;

public class GestionJoueursImpl implements GestionJoueurs {

	JoueurDaoImpl dao = new JoueurDaoImpl();
	
	@Override
	public Joueur enregistrer(Joueur joueur) {
		String encryptedPwd = null; 
		try {
			encryptedPwd = util.PasswordSHA1.getEncryptedPassword(joueur.getMdp());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		joueur.setMdp(encryptedPwd);
		return dao.enregistrer(joueur);
	}

	@Override
	public Joueur rechercherJoueur(String login) {
		Joueur joueur = dao.recherche(login);
		return joueur;
	}
	@Override
	public boolean authentifier(Joueur j){
		Joueur db = rechercherJoueur(j.getPseudo());
		try {
			return util.PasswordSHA1.authenticate(j.getMdp(), db.getMdp());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return false;
	}

}
