package domaine;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import daoimpl.CartesDaoImpl;
import util.JDOM;

@SuppressWarnings("serial")
@Entity
@Table(name = "PARTIES", schema = "koala")
public class Partie implements Serializable {
	public static final int NB_DES = 3;
	public static final int VINGT_ET_UN = 21;

	public enum Etat {
		INITIAL {
			boolean ajouterJoueurPartie(JoueurPartie j, Partie partie) {
				String pseudo = j.getJoueur().getPseudo();
				if (partie.getJoueur(pseudo) != null)
					return false;
				// on attribu des carte aleatoirement au joueur donc on les
				// retire aussi de la pioche de la partie
				/*
				 * List<Carte> main = new ArrayList<>(); for (int i = 0; i < 3;
				 * i++) { int index = (int) (Math.random() *
				 * partie.pioche.size()); //
				 * main.add(partie.pioche.remove(index)); }
				 * j.setMainCarte(main);
				 */
				// on ajoute le joueur a la liste des joueurs
				partie.joueursParties.add(j);
				// si + de 2 joueurs on lance la partie
				if (partie.getJoueursParties().size() == 2) {
					commencerPartie(partie);
				}
				return true;
			}

			boolean commencerPartie(Partie partie) {
				// On commence la partie ==> changement d'etat
				partie.etat = Etat.EN_COURS;
				// on melange les joueurs aleatoirement
				partie.melangerJoueurs();
				// celui a l'indice 0 sera le premier a jouer
				partie.joueurCourant = partie.getJoueursParties().get(0);
				return true;
			}
		},
		EN_COURS {
			boolean commencerTourSuivant(Partie partie) {
				partie.joueurCourant = partie.prochain();
				return true;
			}

			void lancerLesDes(Partie partie) {
				partie.getJoueurCourant().lancerDes();
			}

			Carte piocherCarte(Partie partie) {
				int index = (int) (Math.random() * partie.pioche.size());
				return partie.pioche.remove(index);
			}

			public void jouerCarte(Partie partie, Carte carte) {
				partie.pioche.add(carte);
			}

			boolean donnerSonDe(De aDonner, int ordre, Partie partie) {
				partie.joueurCourant.getMainDe().remove(aDonner);
				for (JoueurPartie jp : partie.joueursParties) {
					if (jp.getOrdreJoueur() == ordre) {
						jp.ajouterDe(aDonner);
						return true;
					}
				}
				return false;
			}
		},
		FINIE {
			Joueur estVainqueur(Partie partie) {
				Joueur aRenvoyer = null;
				for (int i = 0; i < partie.getJoueursParties().size(); i++) {
					if (partie.getJoueursParties().get(i).getMainDe().size() == 0) {
						aRenvoyer = partie.getJoueursParties().get(i).getJoueur();
					}
				}
				return aRenvoyer;
			}
		};

		boolean ajouterJoueurPartie(JoueurPartie joueurPart, Partie partie) {
			return false;
		}

		boolean commencerPartie(Partie partie) {
			return false;
		}

		boolean commencerTourSuivant(Partie partie) {
			return false;
		}

		void lancerLesDes(Partie partie) {

		}

		Carte piocherCarte(Partie partie) {
			return null;
		}

		Joueur estVainqueur(Partie partie) {
			return null;
		}

		public void jouerCarte(Partie partie, Carte carte) {
		}

		boolean donnerSonDe(De aDonner, int ordre, Partie partie) {
			return false;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_partie;

	@NotNull
	@Column(unique = true)
	private String nom;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Etat etat = Etat.INITIAL;

	// @OneToMany(cascade = CascadeType.ALL)
	// @JoinColumn(name = "PARTIE_ID_JOUABLE")
	// @MapKey(name = "numero")

	@ManyToMany
	@JoinTable(schema = "koala", joinColumns = { @JoinColumn(name = "id_partie") }, inverseJoinColumns = {
			@JoinColumn(name = "id_carte") })
	public List<Carte> pioche = new ArrayList<Carte>();

	@OneToMany(mappedBy = "partie")
	private List<JoueurPartie> joueursParties = new ArrayList<JoueurPartie>();

	@OneToOne(cascade = { CascadeType.ALL })
	private JoueurPartie joueurCourant;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dateHeure;

	@Column
	private boolean ordreCroissant = true;

	@ManyToOne
	@JoinColumn(name = "vainqueur")
	private Joueur vainqueur;

	@Transient
	private Set<JoueurPartie> jpPassTour = new HashSet<JoueurPartie>();

	@Transient
	private boolean rejoue = false;

	public void joueurCourantRejoue() {
		rejoue = true;
	}

	public static int getNbDes() {
		return NB_DES;
	}

	public static int getVingtEtUn() {
		return VINGT_ET_UN;
	}

	public int getId_partie() {
		return id_partie;
	}

	public List<Carte> getPioche() {
		return pioche;
	}

	public Calendar getDateHeure() {
		return dateHeure;
	}

	public boolean isOrdreCroissant() {
		return ordreCroissant;
	}

	public void setJoueursParties(List<JoueurPartie> joueursParties) {
		this.joueursParties = joueursParties;
	}

	public Partie(String nom) {
		this.nom = nom;
		dateHeure = new GregorianCalendar();
	}

	protected void melangerJoueurs() {
		Collections.shuffle(joueursParties);
	}

	protected Partie() {
		dateHeure = new GregorianCalendar();
		// pioche = cartes.lister();
	}

	public int getId() {
		return id_partie;
	}

	public void setId_partie(int id_partie) {
		this.id_partie = id_partie;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public void setPioche(List<Carte> pioche) {
		this.pioche = pioche;
	}

	public void setJoueurs(List<JoueurPartie> joueurs) {
		this.joueursParties = joueurs;
	}

	public void setJoueurCourant(JoueurPartie joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public void setDateHeure(Calendar dateHeure) {
		this.dateHeure = dateHeure;
	}

	public void setOrdreCroissant(boolean ordreCroissant) {
		this.ordreCroissant = ordreCroissant;
	}

	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}

	public String getNom() {
		return nom;
	}

	public Etat getEtat() {
		return etat;
	}

	public List<JoueurPartie> getJoueursParties() {
		return Collections.unmodifiableList(joueursParties);
	}

	public JoueurPartie getJoueurCourant() {
		return joueurCourant;
	}

	public Joueur getJoueur(String pseudo) {
		for (JoueurPartie joueur : joueursParties)
			if (joueur.getJoueur().getPseudo().equals(pseudo))
				return joueur.getJoueur();
		return null;
	}

	public void annuler() {
		this.etat = Etat.FINIE;
	}

	public boolean ajouterJoueurPartie(JoueurPartie joueurPart) {
		return etat.ajouterJoueurPartie(joueurPart, this);
	}

	public boolean commencerPartie() {
		return etat.commencerPartie(this);
	}

	public JoueurPartie prochain() {
		if (this.rejoue) {
			rejoue = false;
			return this.joueurCourant;
		}
		if (joueurCourant == null) {
			return this.getJoueursParties().get(0);
		}
		int indice;
		if (this.ordreCroissant) {
			indice = getJoueursParties().indexOf(joueurCourant) + 1;
			if (indice >= getJoueursParties().size())
				indice = 0;
		} else {
			indice = getJoueursParties().indexOf(joueurCourant) - 1;
			if (indice < 0)
				indice = getJoueursParties().size();
		}
		JoueurPartie tmp = getJoueursParties().get(indice);
		if (jpPassTour.contains(tmp)) {
			jpPassTour.remove(tmp);
			return prochain();
		}
		return tmp;
	}

	public void changerSens() {
		if (this.ordreCroissant) {
			this.ordreCroissant = false;
		} else {
			ordreCroissant = true;
		}
	}

	public void passeSonTour(JoueurPartie jp) {
		this.jpPassTour.add(jp);
	}

	public boolean commencerTourSuivant() {
		return etat.commencerTourSuivant(this);
	}

	public void lancerLesDes() {
		etat.lancerLesDes(this);
	}

	public Carte piocherCarte() {
		return etat.piocherCarte(this);
	}

	public void jouerCarte(Carte carte) {
	}

	public Joueur estVainqueur() { // pas de gestion des ex-aequos pour
									// simplicit�
		return etat.estVainqueur(this);
	}

	public Joueur getVainqueur() {
		return vainqueur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	boolean donnerSonDe(De aDonner, int ordre) {
		return this.etat.donnerSonDe(aDonner, ordre, this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_partie;
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
		Partie other = (Partie) obj;
		if (id_partie != other.id_partie)
			return false;
		return true;
	}

	public void remettreCartesDansPioche(List<Carte> main) {
		this.pioche.addAll(main);
	}

}
