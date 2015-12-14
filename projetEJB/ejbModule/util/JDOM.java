package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import domaine.De;
import domaine.Face;

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
		System.out.println(faces.getLength());
		List<Face> listeFaces = new ArrayList<Face>();
		for (int i = 0; i<faces.getLength(); i++){
			face = (Element) faces.item(i);
			String figure = face.getAttribute("figure");
			System.out.println(figure);
			String identif = face.getAttribute("identif");
			System.out.println(identif);
			String src = face.getAttribute("src");
			System.out.println(src);
			int nbFaces = Integer.parseInt(face.getAttribute("nbFaces"));
			System.out.println(nbFaces);
			listeFaces.add(new Face(figure, identif, src, nbFaces));
		}
		int nbParJoueur = Integer.parseInt(seulDe.getAttribute("nbParJoueur"));
		int nbTotalDes = Integer.parseInt(seulDe.getAttribute("nbTotalDes"));
		De deDomaine = new De(listeFaces, nbParJoueur, nbTotalDes);
// CARTE
		
		NodeList cartes = racine.getElementsByTagName("carte");
		for (int i = 0; i<cartes.getLength(); i++){
			Element carteCourante = (Element) cartes.item(i);
			
		}
	}
	

}
