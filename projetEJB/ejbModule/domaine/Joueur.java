package domaine;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "JOUEURS", schema = "koala")
public class Joueur implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_joueur;

	@NotNull
	@Column(unique = true)
	private String pseudo;

	@NotNull
	private String mdp;

	public Joueur(String pseudo, String mdp) {
		this.mdp = mdp;
		this.pseudo = pseudo;
	}

	protected Joueur() {
	}

	public int getId() {
		return id_joueur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public int getId_joueur() {
		return id_joueur;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_joueur;
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
		Joueur other = (Joueur) obj;
		if (id_joueur != other.id_joueur)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.pseudo;
	}

	public static class JoueurComparator implements Comparator<Joueur> {

		@Override
		public int compare(Joueur j1, Joueur j2) {
			return j1.pseudo.compareTo(j2.pseudo);
		}

	}
}
