//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.14 à 11:43:48 AM CET 
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
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}carte" maxOccurs="unbounded"/>
 *       &lt;/sequence>
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
    "carte"
})
@XmlRootElement(name = "cartes")
public class Cartes {

    @XmlElement(required = true)
    protected List<Carte> carte;
    @XmlAttribute(name = "nbCartesParJoueur", required = true)
    protected int nbCartesParJoueur;
    @XmlAttribute(name = "nbCartesTotal", required = true)
    protected int nbCartesTotal;
    @XmlAttribute(name = "minJoueurs", required = true)
    protected int minJoueurs;
    @XmlAttribute(name = "maxJoueurs", required = true)
    protected int maxJoueurs;

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
     * Obtient la valeur de la propriété nbCartesParJoueur.
     * 
     */
    public int getNbCartesParJoueur() {
        return nbCartesParJoueur;
    }

    /**
     * Définit la valeur de la propriété nbCartesParJoueur.
     * 
     */
    public void setNbCartesParJoueur(int value) {
        this.nbCartesParJoueur = value;
    }

    /**
     * Obtient la valeur de la propriété nbCartesTotal.
     * 
     */
    public int getNbCartesTotal() {
        return nbCartesTotal;
    }

    /**
     * Définit la valeur de la propriété nbCartesTotal.
     * 
     */
    public void setNbCartesTotal(int value) {
        this.nbCartesTotal = value;
    }

    /**
     * Obtient la valeur de la propriété minJoueurs.
     * 
     */
    public int getMinJoueurs() {
        return minJoueurs;
    }

    /**
     * Définit la valeur de la propriété minJoueurs.
     * 
     */
    public void setMinJoueurs(int value) {
        this.minJoueurs = value;
    }

    /**
     * Obtient la valeur de la propriété maxJoueurs.
     * 
     */
    public int getMaxJoueurs() {
        return maxJoueurs;
    }

    /**
     * Définit la valeur de la propriété maxJoueurs.
     * 
     */
    public void setMaxJoueurs(int value) {
        this.maxJoueurs = value;
    }

}
