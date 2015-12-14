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
@XmlType(name = "", propOrder = {
    "face"
})
@XmlRootElement(name = "de")
public class De {

    @XmlElement(required = true)
    protected List<Face> face;
    @XmlAttribute(name = "nbParJoueur", required = true)
    protected int nbParJoueur;
    @XmlAttribute(name = "nbTotalDes", required = true)
    protected int nbTotalDes;

    /**
     * Gets the value of the face property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the face property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Face }
     * 
     * 
     */
    public List<Face> getFace() {
        if (face == null) {
            face = new ArrayList<Face>();
        }
        return this.face;
    }

    /**
     * Obtient la valeur de la propriété nbParJoueur.
     * 
     */
    public int getNbParJoueur() {
        return nbParJoueur;
    }

    /**
     * Définit la valeur de la propriété nbParJoueur.
     * 
     */
    public void setNbParJoueur(int value) {
        this.nbParJoueur = value;
    }

    /**
     * Obtient la valeur de la propriété nbTotalDes.
     * 
     */
    public int getNbTotalDes() {
        return nbTotalDes;
    }

    /**
     * Définit la valeur de la propriété nbTotalDes.
     * 
     */
    public void setNbTotalDes(int value) {
        this.nbTotalDes = value;
    }

}
