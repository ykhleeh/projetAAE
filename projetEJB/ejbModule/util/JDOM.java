package util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import domaine.Carte;
import domaine.De;
import domaine.Face;
import domaine.Wazabi;

public class JDOM {

	static DocumentBuilderFactory factory;
	static DocumentBuilder builder;
	static Document document;
	static Element racine;
	
	public static void main(String[] args) {
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		try {
			document = builder.parse(new File("ejbModule/META-INF/wazabi.xml"));
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
// RACINE
		racine = document.getDocumentElement();
		System.out.println(racine.getNodeName());
// DES
		NodeList de = racine.getElementsByTagName("de");
		Element seulDe = (Element) de.item(0); // 1 seul dé donc a 0
		NodeList faces = seulDe.getElementsByTagName("face");
		Element face;
		List<Face> listeFaces = new ArrayList<Face>();
		for (int i = 0; i<faces.getLength(); i++){
			face = (Element) faces.item(i);
			String figure = face.getAttribute("figure");
			String identif = face.getAttribute("identif");
			String src = face.getAttribute("src");
			int nbFaces = Integer.parseInt(face.getAttribute("nbFaces"));
			listeFaces.add(new Face(figure, identif, src, nbFaces));
		}
		int nbParJoueur = Integer.parseInt(seulDe.getAttribute("nbParJoueur"));
		int nbTotalDes = Integer.parseInt(seulDe.getAttribute("nbTotalDes"));
		De deDomaine = new De(listeFaces, nbParJoueur, nbTotalDes);
// CARTE
		List<Carte> listeCartes = new ArrayList<>();
		
		NodeList cartes = racine.getElementsByTagName("carte");
		for (int i = 0; i<cartes.getLength(); i++){
			Element carteCourante = (Element) cartes.item(i);
			int cout = Integer.parseInt(carteCourante.getAttribute("cout"));
			int codeEffet = Integer.parseInt(carteCourante.getAttribute("codeEffet"));
			int nb = Integer.parseInt(carteCourante.getAttribute("nb"));
			String effet = carteCourante.getAttribute("effet");
			String src = carteCourante.getAttribute("src");
			NodeList refsListe = carteCourante.getElementsByTagName("figure");
			String contenu = carteCourante.getTextContent();
			System.out.println(contenu);
			List<Serializable> refffs = new ArrayList<>();
			for (int j = 0 ; j<refsListe.getLength(); j++){
				String ceQuOnVeut = ((Element)refsListe.item(j)).getAttribute("ref");
				System.out.println("REFS = " + ceQuOnVeut);
				// TODO on fait quoi avec les contenus et les Serializableeee? 
				// J'ai compris que ce content était les références mais comment on est censé garder ca???
			}
			Carte c = new Carte(null, cout, nb, effet, codeEffet, src);
			listeCartes.add(c);
		}
		System.out.println(listeCartes.size());
// WAZABI
		String but = racine.getAttribute("but");
		int nbCartesParJoueur = Integer.parseInt(racine.getAttribute("nbCartesParJoueur"));
		int nbCartesTotal = Integer.parseInt(racine.getAttribute("nbCartesTotal"));
		int minJoueurs = Integer.parseInt(racine.getAttribute("minJoueurs"));
		int maxJoueurs = Integer.parseInt(racine.getAttribute("maxJoueurs"));

		Wazabi jeu = new Wazabi(deDomaine, listeCartes, but, nbCartesParJoueur, nbCartesTotal, minJoueurs, maxJoueurs);
	}
	

}
