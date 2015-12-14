//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.14 à 11:43:48 AM CET 
//


package domaine;

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
 *         &lt;element ref="{}de"/>
 *         &lt;element ref="{}cartes"/>
 *       &lt;/sequence>
 *       &lt;attribute name="but" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "cartes"
})
@XmlRootElement(name = "wazabi")
public class Wazabi {

    @XmlElement(required = true)
    protected De de;
    @XmlElement(required = true)
    protected Cartes cartes;
    @XmlAttribute(name = "but")
    protected String but;

    /**
     * Obtient la valeur de la propriété de.
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
     * Définit la valeur de la propriété de.
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
     * Obtient la valeur de la propriété cartes.
     * 
     * @return
     *     possible object is
     *     {@link Cartes }
     *     
     */
    public Cartes getCartes() {
        return cartes;
    }

    /**
     * Définit la valeur de la propriété cartes.
     * 
     * @param value
     *     allowed object is
     *     {@link Cartes }
     *     
     */
    public void setCartes(Cartes value) {
        this.cartes = value;
    }

    /**
     * Obtient la valeur de la propriété but.
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
     * Définit la valeur de la propriété but.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBut(String value) {
        this.but = value;
    }

}
