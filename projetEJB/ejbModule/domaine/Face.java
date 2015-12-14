//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2015.12.14 � 01:55:55 PM CET 
//


package domaine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="figure" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="identif" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="src" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nbFaces" use="required" type="{}deType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "face")
public class Face {

    @XmlAttribute(name = "figure", required = true)
    protected String figure;
    @XmlAttribute(name = "identif", required = true)
    protected String identif;
    @XmlAttribute(name = "src")
    protected String src;
    @XmlAttribute(name = "nbFaces", required = true)
    protected int nbFaces;

    public Face(String figure, String identif, String src, int nbFaces) {
		super();
		this.figure = figure;
		this.identif = identif;
		this.src = src;
		this.nbFaces = nbFaces;
	}

    public Face(){
    	super();
    }
    
	/**
     * Obtient la valeur de la propri�t� figure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFigure() {
        return figure;
    }

    /**
     * D�finit la valeur de la propri�t� figure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFigure(String value) {
        this.figure = value;
    }

    /**
     * Obtient la valeur de la propri�t� identif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentif() {
        return identif;
    }

    /**
     * D�finit la valeur de la propri�t� identif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentif(String value) {
        this.identif = value;
    }

    /**
     * Obtient la valeur de la propri�t� src.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrc() {
        return src;
    }

    /**
     * D�finit la valeur de la propri�t� src.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrc(String value) {
        this.src = value;
    }

    /**
     * Obtient la valeur de la propri�t� nbFaces.
     * 
     */
    public int getNbFaces() {
        return nbFaces;
    }

    /**
     * D�finit la valeur de la propri�t� nbFaces.
     * 
     */
    public void setNbFaces(int value) {
        this.nbFaces = value;
    }

}
