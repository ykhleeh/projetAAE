//
// Ce fichier a ete genere par l'implementation de reference JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportee e ce fichier sera perdue lors de la recompilation du schema source. 
// Genere le : 2015.12.14 e 01:55:55 PM CET 
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
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Classe Java pour anonymous complex type.
 * 
 * <p>
 * Le fragment de schema suivant indique le contenu attendu figurant dans cette
 * classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="figure" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="cout" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nb" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="effet" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="codeEffet" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "content" })
@XmlRootElement(name = "carte")
@SuppressWarnings("serial")
@Entity
@Table(name = "CARTES", schema = "koala")
public class Carte implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_carte;

	@XmlElementRef(name = "figure", type = JAXBElement.class, required = false)
	@XmlMixed
	@Transient
	protected List<Serializable> content;

	@XmlAttribute(name = "cout", required = true)
	protected int cout;

	@XmlAttribute(name = "nb", required = true)
	@Transient
	protected int nb;

	@XmlAttribute(name = "effet", required = true)
	@Transient
	protected String effet;

	@XmlAttribute(name = "codeEffet", required = true)
	protected int codeEffet;

	@XmlAttribute(name = "src")
	@Transient
	protected String src;

	/**
	 * Gets the value of the content property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the content property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getContent().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link JAXBElement }{@code <}{@link Carte.Figure }{@code >} {@link String
	 * }
	 * 
	 * 
	 */
	public List<Serializable> getContent() {
		if (content == null) {
			content = new ArrayList<Serializable>();
		}
		return this.content;
	}

	/**
	 * Obtient la valeur de la propriete cout.
	 * 
	 */
	public int getCout() {
		return cout;
	}

	/**
	 * Definit la valeur de la propriete cout.
	 * 
	 */
	public void setCout(int value) {
		this.cout = value;
	}

	/**
	 * Obtient la valeur de la propriete nb.
	 * 
	 */
	public int getNb() {
		return nb;
	}

	/**
	 * Definit la valeur de la propriete nb.
	 * 
	 */
	public void setNb(int value) {
		this.nb = value;
	}

	/**
	 * Obtient la valeur de la propriete effet.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEffet() {
		return effet;
	}

	/**
	 * Definit la valeur de la propriete effet.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEffet(String value) {
		this.effet = value;
	}

	/**
	 * Obtient la valeur de la propriete codeEffet.
	 * 
	 */
	public int getCodeEffet() {
		return codeEffet;
	}

	/**
	 * Definit la valeur de la propriete codeEffet.
	 * 
	 */
	public void setCodeEffet(int value) {
		this.codeEffet = value;
	}

	/**
	 * Obtient la valeur de la propriete src.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * Definit la valeur de la propriete src.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSrc(String value) {
		this.src = value;
	}

	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 * 
	 * <p>
	 * Le fragment de schema suivant indique le contenu attendu figurant dans
	 * cette classe.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	public static class Figure {

		@XmlAttribute(name = "ref")
		protected String ref;

		/**
		 * Obtient la valeur de la propriete ref.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getRef() {
			return ref;
		}

		/**
		 * Definit la valeur de la propriete ref.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setRef(String value) {
			this.ref = value;
		}

	}

}
