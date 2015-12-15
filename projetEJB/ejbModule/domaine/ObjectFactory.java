//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.12.14 à 01:55:55 PM CET 
//


package domaine;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the domaineBis package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CarteFigure_QNAME = new QName("", "figure");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: domaineBis
     * 
     */
    public ObjectFactory() {
    }
    
    /**
     * Create an instance of {@link Joueur }
     * 
     */
    public Joueur createJoueur(){
    	return new Joueur();
    }

    /**
     * Create an instance of {@link Carte }
     * 
     */
    public Carte createCarte() {
        return new Carte();
    }

    /**
     * Create an instance of {@link De }
     * 
     */
    public De createDe() {
        return new De();
    }

    /**
     * Create an instance of {@link Face }
     * 
     */
    public Face createFace() {
        return new Face();
    }

    /**
     * Create an instance of {@link Carte.Figure }
     * 
     */
    public Carte.Figure createCarteFigure() {
        return new Carte.Figure();
    }

    /**
     * Create an instance of {@link Wazabi }
     * 
     */
    public Wazabi createWazabi() {
        return new Wazabi();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Carte.Figure }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "figure", scope = Carte.class)
    public JAXBElement<Carte.Figure> createCarteFigure(Carte.Figure value) {
        return new JAXBElement<Carte.Figure>(_CarteFigure_QNAME, Carte.Figure.class, Carte.class, value);
    }

}
