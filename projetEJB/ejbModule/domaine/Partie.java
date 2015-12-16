package domaine;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

import util.JDOM;

@SuppressWarnings("serial")
@Entity
@Table(name = "PARTIES", schema = "koala")
public class Partie implements Serializable {
	public static final int NB_DES = 3;
	public static final int VINGT_ET_UN = 21;
	// CartesDaoImpl daoCarte = new CartesDaoImpl();

	public enum Etat {
		INITIAL {
			boolean ajouterJoueurPartie(JoueurPartie j, Partie partie) {
				String pseudo = j.getJoueur().getPseudo();
				if (partie.getJoueur(pseudo) != null)
					return false;
				// on attribu des carte aleatoirement au joueur donc on les
				// retire aussi de la pioche de la partie
				List<Carte> main = new ArrayList<>();
				for (int i = 0; i < 3; i++) {
					int index = (int) (Math.random() * partie.pioche.size());
				//	main.add(partie.pioche.remove(index));
				}
				j.setMainCarte(main);
				// on ajoute le joueur a la liste des joueurs
				partie.joueurs.add(j);
				// si + de 2 joueurs on lance la partie
				if (partie.getJoueurs().size() == 2) {
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
				partie.joueurCourant = partie.getJoueurs().get(0);
				return true;
			}
		},
		EN_COURS {
			boolean commencerTourSuivant(Partie partie) {
				// partie.preparerPourUnNouveauLancer();
				int indice = partie.prochain();
				if (indice == 0) {
					partie.etat = Etat.FINIE;
					partie.joueurCourant = null;
					return false;
				}
				partie.joueurCourant = partie.getJoueurs().get(indice);
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
				partie.joueurCourant.getMainCarte().remove(carte);
			}

			boolean donnerSonDe(De aDonner, int ordre, Partie partie) {
				partie.joueurCourant.getMainDe().remove(aDonner);
				for (JoueurPartie jp : partie.joueurs) {
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
				for (int i = 0; i < partie.getJoueurs().size(); i++) {
					if (partie.getJoueurs().get(i).getMainDe().size() == 0) {
						aRenvoyer = partie.getJoueurs().get(i).getJoueur();
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
	private List<Carte> pioche = new ArrayList<Carte>();

	@OneToMany(mappedBy = "partie")
	private List<JoueurPartie> joueurs = new ArrayList<JoueurPartie>();

	@OneToOne(cascade = { CascadeType.ALL })
	@PrimaryKeyJoinColumn
	private JoueurPartie joueurCourant;

	// @Temporal(TemporalType.TIMESTAMP)
	private Timestamp dateHeure;

	@Transient
	private boolean ordreCroissant = true;

	@ManyToOne
	@JoinColumn(name = "vainqueur")
	private Joueur vainqueur;

	public Partie(String nom) {
		this.nom = nom;
		dateHeure = Timestamp.valueOf(LocalDateTime.now());
	}

	protected void melangerJoueurs() {
		Collections.shuffle(joueurs);
	}

	protected Partie() {
		dateHeure = Timestamp.valueOf(LocalDateTime.now());
		JDOM dom = new JDOM();
		Wazabi jeu = dom.getJeu();
		pioche = jeu.getCarte();
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
		this.joueurs = joueurs;
	}

	public void setJoueurCourant(JoueurPartie joueurCourant) {
		this.joueurCourant = joueurCourant;
	}

	public void setDateHeure(Timestamp dateHeure) {
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

	public List<JoueurPartie> getJoueurs() {
		return Collections.unmodifiableList(joueurs);
	}

	public JoueurPartie getJoueurCourant() {
		return joueurCourant;
	}

	public Joueur getJoueur(String pseudo) {
		for (JoueurPartie joueur : joueurs)
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

	public int prochain() {
		if (this.ordreCroissant) {
			int indice = getJoueurs().indexOf(joueurCourant) + 1;
			if (indice >= getJoueurs().size())
				indice = 0;
			return indice;
		} else {
			int indice = getJoueurs().indexOf(joueurCourant) - 1;
			if (indice >= getJoueurs().size())
				indice = 0;
			return indice;
		}
	}

	public void changerSens() {
		if (this.ordreCroissant) {
			this.ordreCroissant = false;
		} else {
			ordreCroissant = true;
		}
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

}
