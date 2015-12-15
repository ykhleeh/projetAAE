package domaine;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import daoimpl.CartesDaoImpl;

@SuppressWarnings("serial")
@Entity
@Table(name = "PARTIES", schema = "koala")
public class Partie implements Serializable {
	public static final int NB_DES = 3;
	public static final int VINGT_ET_UN = 21;
	CartesDaoImpl daoCarte = new CartesDaoImpl();

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
					main.add(partie.pioche.remove(index));
				}
				j.setMainCarte(main);
				//on ajoute le joueur a la liste des joueurs
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
				//partie.preparerPourUnNouveauLancer();
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
			//A COMPLETER
			boolean donnerSonDe(int numeroDe, int numJoueur, Partie partie) {					
				De de = partie.getJoueurCourant().supprimerDe(numeroDe);
				if(de == null)return false;
				
				return true;
			}
		},
		FINIE {
			/*
			 * Joueur estVainqueur(Partie partie) { int indiceMax = -1; int max
			 * = 0; for (int i = 0; i < partie.getJoueurs().size(); i++) {
			 * Joueur joueur = partie.getJoueurs().get(i); int points =
			 * partie.getPoints(joueur); if (points > VINGT_ET_UN) continue; if
			 * (points > max) { max = points; indiceMax = i; } } if (indiceMax
			 * == -1) return null; return partie.getJoueurs().get(indiceMax); }
			 */
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

		boolean ecarterDe(int numero, Partie partie) {
			return false;
		}

		Joueur estVainqueur(Partie partie) {
			return null;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_partie;

	@NotNull
	@Column(unique = true)
	private String nom;

	// @NotNull
	// @Enumerated
	@Transient
	private Etat etat = Etat.INITIAL;

	// @OneToMany(cascade = CascadeType.ALL)
	// @JoinColumn(name = "PARTIE_ID_JOUABLE")
	// @MapKey(name = "numero")

	@ManyToMany
	@JoinTable(schema = "koala", joinColumns = { @JoinColumn(name = "id_partie") }, inverseJoinColumns = {
			@JoinColumn(name = "id_carte") })
	private List<Carte> pioche = new ArrayList<Carte>();

	// @ElementCollection
	// @CollectionTable(schema = "JACK", name = "JOUEURS_PARTIES")
	// @Column(name = "POINTS")
	// @MapKeyJoinColumn(name = "JOUEUR_ID")
	@Transient
	private List<JoueurPartie> joueurs = new ArrayList<JoueurPartie>();

	private JoueurPartie joueurCourant;

	private LocalDateTime dateHeure;

	private boolean ordreCroissant = true;

	private Joueur vainqueur;

	public Partie(String nom) {
		this.nom = nom;
		dateHeure = LocalDateTime.now();
		pioche = daoCarte.lister();
	}

	protected void melangerJoueurs() {
		Collections.shuffle(joueurs);
	}

	protected Partie() {
	}

	public int getId() {
		return id_partie;
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

	private int prochain() {
		int indice = getJoueurs().indexOf(joueurCourant) + 1;
		if (indice >= getJoueurs().size())
			indice = 0;
		return indice;
	}

	public boolean commencerTourSuivant() {
		return etat.commencerTourSuivant(this);
	}

	public void lancerLesDes() {
		etat.lancerLesDes(this);
	}

	public boolean ecarterDe(int numero) {
		return etat.ecarterDe(numero, this);
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
