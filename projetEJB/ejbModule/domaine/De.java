//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2015.12.14 � 01:55:55 PM CET 
//

package domaine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour anonymous complex type.
 * 
 * <p>
 * Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette
 * classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}face" maxOccurs="6"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nbParJoueur" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nbTotalDes" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "face" })
@XmlRootElement(name = "de")
@SuppressWarnings("serial")
@Entity
@Table(name = "DES", schema = "koala")
public class De implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_de;

	@Min(1)
	@Max(Partie.NB_DES)
	@NotNull
	private String valeur;

	@XmlElement(required = true)
	@Transient
	protected List<Face> face;

	@XmlAttribute(name = "nbParJoueur", required = true)
	@Transient
	protected int nbParJoueur;

	@XmlAttribute(name = "nbTotalDes", required = true)
	@Transient
	protected int nbTotalDes;

	public De(String valeur) {
		this.valeur = valeur;
	}


	public De() {
		super();
	}

	/**
	 * Gets the value of the face property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the face property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFace().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Face }
	 * 
	 * 
	 */
	
	
	
	public List<Face> getFace() {
		if (face == null) {
			face = new ArrayList<Face>();
		}
		return this.face;
	}

	public void setFace(List<Face> face) {
		this.face = face;
	}

	/**
	 * Obtient la valeur de la propri�t� nbParJoueur.
	 * 
	 */
	public int getNbParJoueur() {
		return nbParJoueur;
	}

	/**
	 * D�finit la valeur de la propri�t� nbParJoueur.
	 * 
	 */
	public void setNbParJoueur(int value) {
		this.nbParJoueur = value;
	}

	/**
	 * Obtient la valeur de la propri�t� nbTotalDes.
	 * 
	 */
	public int getNbTotalDes() {
		return nbTotalDes;
	}

	/**
	 * D�finit la valeur de la propri�t� nbTotalDes.
	 * 
	 */
	public void setNbTotalDes(int value) {
		this.nbTotalDes = value;
	}
	
	public int getId() {
		return this.id_de;
	}
	
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

}
