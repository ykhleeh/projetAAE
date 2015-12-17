package domaine;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import domaine.De;
import domaine.Partie.Etat;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="info", propOrder = {
		"user",
		"joueurCourant",
		"vainqueur",
		"joueurs",
		"etat",
		"nbDes",
		"nbCartes",
		"des",
		"cartes",
})
@XmlRootElement(name="info")
public class Info implements Serializable {
	@XmlElement
	private String user;
	@XmlElement
	private String joueurCourant;
	@XmlElement
	private String vainqueur;
	@XmlElement
	private List<String> joueurs = new ArrayList<String>();
	@XmlElement
	private Etat etat;
	@XmlElement
	private String nbDes;
	@XmlElement
	private List<De> des;
	@XmlElement
	private String nbCartes;
	@XmlElement
	private List<Carte> cartes;

	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getJoueurCourant() {
		return joueurCourant;
	}
	public void setJoueurCourant(String joueurCourant) {
		this.joueurCourant = joueurCourant;
	}
	public String getVainqueur() {
		return vainqueur;
	}
	public void setVainqueur(String vainqueur) {
		this.vainqueur = vainqueur;
	}
	public Etat getEtat() {
		return etat;
	}
	public String getNbDes() {
		return nbDes;
	}
	public void setNbDes(String nbDes) {
		this.nbDes = nbDes;
	}
	public List<String> getJoueurs() {
		return joueurs;
	}
	public List<De> getDes() {
		return des;
	}
	public void setDes(List<De> des) {
		this.des = des;
	}
	public String getNbCartes() {
		return nbCartes;
	}
	public void setNbCartes(String nbCartes) {
		this.nbCartes = nbCartes;
	}
	public List<Carte> getCartes() {
		return cartes;
	}
	public void setCartes(List<Carte> cartes) {
		this.cartes = cartes;
	}
	public void setJoueurs(List<String> joueurs) {
		this.joueurs = joueurs;
	}
	public void setEtat(Etat etat2) {
		this.etat = etat2;
	}
	
}
