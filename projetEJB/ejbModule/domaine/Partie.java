package domaine;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

@SuppressWarnings("serial")
@Entity
@Table(name = "PARTIES", schema = "koala")
public class Partie implements Serializable {
	public static final int NB_DES = 3;
	public static final int VINGT_ET_UN = 21;

	public enum Etat {
		INITIAL {
			boolean ajouterJoueur(Joueur joueur, Partie partie) {
				String pseudo = joueur.getPseudo();
				if (partie.getJoueur(pseudo) != null)
					return false;
				partie.joueurs.add(new JoueurPartie(joueur));
				return true;
			}

			boolean commencerPartie(Partie partie) {
				partie.etat = Etat.EN_COURS;
				partie.joueurCourant = partie.getJoueurs().get(0);
				return true;
			}
		},
		EN_COURS {
			boolean commencerTourSuivant(Partie partie) {
				partie.preparerPourUnNouveauLancer();

				int indice = partie.prochain();
				if (indice == 0) {
					partie.etat = Etat.FINIE;
					partie.joueurCourant = null;
					return false;
				}
				partie.joueurCourant = partie.getJoueurs().get(indice);
				return true;
			}
//TODO terminer domaine.partie les differents etats
			/*
			 * int lancerLesDes(Partie partie) { if
			 * (partie.getPoints(partie.joueurCourant) >= 21) return -1; int
			 * total = 0; for (Integer i : partie.desJouables.keySet()) { total
			 * += partie.desJouables.get(i).lancerDe(); }
			 * partie.ajouterPoints(total, partie.joueurCourant); return total;
			 * }
			 * 
			 * boolean ecarterDe(int numero, Partie partie) { if
			 * (partie.getPoints(partie.joueurCourant) >= 21) return false; De
			 * de = partie.desJouables.get(numero); if (de == null) return
			 * false; partie.desJouables.remove(numero);
			 * partie.desEcartes.put(numero, de); return true; }
			 */

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
		boolean ajouterJoueur(Joueur joueur, Partie partie) {
			return false;
		}

		boolean commencerPartie(Partie partie) {
			return false;
		}

		boolean commencerTourSuivant(Partie partie) {
			return false;
		}

		int lancerLesDes(Partie partie) {
			return -1;
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

	public boolean ajouterJoueur(Joueur joueur) {
		return etat.ajouterJoueur(joueur, this);
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

	public int lancerLesDes() {
		return etat.lancerLesDes(this);
	}

	public boolean ecarterDe(int numero) {
		return etat.ecarterDe(numero, this);
	}

	private void preparerPourUnNouveauLancer() {
		// desEcartes.clear();
	}

	public Joueur estVainqueur() { // pas de gestion des ex-aequos pour
									// simplicit�
		return etat.estVainqueur(this);
	}
	
	

	public Joueur getVainqueur() {
		return vainqueur;
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
	/*
	 * @PostConstruct public void go() throws JAXBException, IOException { //
	 * cr�ation de l�InputStream � adapter selon votre jar. InputStream is = new
	 * FileInputStream("../standalone/deployments/wasabiEJB.jar/wazabi.xml");
	 * 
	 * Wazabi wazabi = fromStream(Wazabi.class, is);
	 * 
	 * // enregistrement des d�s for (int i = 0; i <
	 * wazabi.getDe().getNbTotalDes(); i++) { De de = new De();
	 * deDao.enregistrer(de); }
	 * 
	 * // enregistrement des cartes for (Carte carte :
	 * wazabi.getCartes().getCarte()) { int nbCartesDeCeType = carte.getNb();
	 * Carte[] cartes = new Carte[nbCartesDeCeType]; for (int i = 0; i <
	 * nbCartesDeCeType; i++) { cartes[i] = (Carte) carte.clone();
	 * carteDao.enregistrer(cartes[i]); } } }
	 * 
	 * @SuppressWarnings("unchecked") private static <T> T fromStream(Class<T>
	 * clazz, InputStream input) throws JAXBException { JAXBContext ctx =
	 * JAXBContext.newInstance(clazz); Object result =
	 * ctx.createUnmarshaller().unmarshal(new StreamSource(input), clazz); if
	 * (result instanceof JAXBElement<?>) { result =
	 * JAXBIntrospector.getValue(result); } return (T) result; }
	 */

}
