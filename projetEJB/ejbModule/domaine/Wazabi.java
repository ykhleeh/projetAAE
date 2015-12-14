//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2015.12.14 � 01:55:55 PM CET 
//


package domaine;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}de"/>
 *         &lt;element ref="{}carte" maxOccurs="24"/>
 *       &lt;/sequence>
 *       &lt;attribute name="but" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nbCartesParJoueur" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="nbCartesTotal" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="minJoueurs" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="maxJoueurs" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "de",
    "carte"
})
@XmlRootElement(name = "wazabi")
public class Wazabi {

    @XmlElement(required = true)
    protected De de;
    @XmlElement(required = true)
    protected List<Carte> carte;
    @XmlAttribute(name = "but")
    protected String but;
    @XmlAttribute(name = "nbCartesParJoueur", required = true)
    protected int nbCartesParJoueur;
    @XmlAttribute(name = "nbCartesTotal", required = true)
    protected int nbCartesTotal;
    @XmlAttribute(name = "minJoueurs", required = true)
    protected int minJoueurs;
    @XmlAttribute(name = "maxJoueurs", required = true)
    protected int maxJoueurs;

    
    
    public Wazabi(De de, List<Carte> carte, String but, int nbCartesParJoueur, int nbCartesTotal, int minJoueurs,
			int maxJoueurs) {
		super();
		this.de = de;
		this.carte = carte;
		this.but = but;
		this.nbCartesParJoueur = nbCartesParJoueur;
		this.nbCartesTotal = nbCartesTotal;
		this.minJoueurs = minJoueurs;
		this.maxJoueurs = maxJoueurs;
	}

	public Wazabi() {
		super();
	}

	/**
     * Obtient la valeur de la propri�t� de.
     * 
     * @return
     *     possible object is
     *     {@link De }
     *     
     */
    public De getDe() {
        return de;
    }

    /**
     * D�finit la valeur de la propri�t� de.
     * 
     * @param value
     *     allowed object is
     *     {@link De }
     *     
     */
    public void setDe(De value) {
        this.de = value;
    }

    /**
     * Gets the value of the carte property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carte property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarte().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Carte }
     * 
     * 
     */
    public List<Carte> getCarte() {
        if (carte == null) {
            carte = new ArrayList<Carte>();
        }
        return this.carte;
    }

    /**
     * Obtient la valeur de la propri�t� but.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBut() {
        return but;
    }

    /**
     * D�finit la valeur de la propri�t� but.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBut(String value) {
        this.but = value;
    }

    /**
     * Obtient la valeur de la propri�t� nbCartesParJoueur.
     * 
     */
    public int getNbCartesParJoueur() {
        return nbCartesParJoueur;
    }

    /**
     * D�finit la valeur de la propri�t� nbCartesParJoueur.
     * 
     */
    public void setNbCartesParJoueur(int value) {
        this.nbCartesParJoueur = value;
    }

    /**
     * Obtient la valeur de la propri�t� nbCartesTotal.
     * 
     */
    public int getNbCartesTotal() {
        return nbCartesTotal;
    }

    /**
     * D�finit la valeur de la propri�t� nbCartesTotal.
     * 
     */
    public void setNbCartesTotal(int value) {
        this.nbCartesTotal = value;
    }

    /**
     * Obtient la valeur de la propri�t� minJoueurs.
     * 
     */
    public int getMinJoueurs() {
        return minJoueurs;
    }

    /**
     * D�finit la valeur de la propri�t� minJoueurs.
     * 
     */
    public void setMinJoueurs(int value) {
        this.minJoueurs = value;
    }

    /**
     * Obtient la valeur de la propri�t� maxJoueurs.
     * 
     */
    public int getMaxJoueurs() {
        return maxJoueurs;
    }

    /**
     * D�finit la valeur de la propri�t� maxJoueurs.
     * 
     */
    public void setMaxJoueurs(int value) {
        this.maxJoueurs = value;
    }

}
