package domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "joueursparties", schema = "koala")
public class JoueurPartie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_joueurPartie;

	@ManyToOne
	private Joueur joueur;

	@ManyToMany
	@JoinTable(schema = "koala", joinColumns = { @JoinColumn(name = "id_joueurPartie") }, inverseJoinColumns = {
			@JoinColumn(name = "id_de") })
	private List<De> mainDe;

	int ordreJoueur;

	@ManyToMany
	@JoinTable(schema = "koala", joinColumns = { @JoinColumn(name = "id_joueurPartie") }, inverseJoinColumns = {
			@JoinColumn(name = "id_carte") })
	private List<Carte> mainCarte;

	public JoueurPartie(Joueur joueur) {
		super();
		this.joueur = joueur;
		mainDe = new ArrayList<De>();
		for (int i = 0; i < 4; i++) {
			mainDe.add(new De(i + 1));
		}

	}

	protected JoueurPartie() {
		super();
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public List<De> getMainDe() {
		return mainDe;
	}

	public List<Carte> getMainCarte() {
		return mainCarte;
	}

	public void setMainCarte(List<Carte> mainCarte) {
		this.mainCarte = mainCarte;
	}

	public int getId_joueurPartie() {
		return id_joueurPartie;
	}

	public int getOrdreJoueur() {
		return ordreJoueur;
	}

	public void setOrdreJoueur(int ordreJoueur) {
		this.ordreJoueur = ordreJoueur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_joueurPartie;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JoueurPartie other = (JoueurPartie) obj;
		if (id_joueurPartie != other.id_joueurPartie)
			return false;
		return true;
	}

}
