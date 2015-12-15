package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import domaine.Carte;
import domaine.De;
import domaine.Face;
import domaine.ObjectFactory;
import domaine.Wazabi;

public class JDOM {

	DocumentBuilderFactory factory;
	 DocumentBuilder builder;
	 Document document;
	 Element racine;
	 ObjectFactory fabrique = new ObjectFactory();
	
	public Wazabi getJeu(){
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
		racine = document.getDocumentElement();
		String but = racine.getAttribute("but");
		int nbCartesParJoueur = Integer.parseInt(racine.getAttribute("nbCartesParJoueur"));
		int nbCartesTotal = Integer.parseInt(racine.getAttribute("nbCartesTotal"));
		int minJoueurs = Integer.parseInt(racine.getAttribute("minJoueurs"));
		int maxJoueurs = Integer.parseInt(racine.getAttribute("maxJoueurs"));

		Wazabi jeu = fabrique.createWazabi();
		jeu.setBut(but);
		jeu.setDe(getDe());
		jeu.setCarte(listeCartes());
		jeu.setMaxJoueurs(maxJoueurs);
		jeu.setMinJoueurs(minJoueurs);
		jeu.setNbCartesParJoueur(nbCartesParJoueur);
		jeu.setNbCartesTotal(nbCartesTotal);
		return jeu;
	}
	
	
	public De getDe(){
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
			Face f = fabrique.createFace();
			f.setFigure(figure);
			f.setIdentif(identif);
			f.setNbFaces(nbFaces);
			f.setSrc(src);
			listeFaces.add(f);
		}
		int nbParJoueur = Integer.parseInt(seulDe.getAttribute("nbParJoueur"));
		int nbTotalDes = Integer.parseInt(seulDe.getAttribute("nbTotalDes"));
		De d = fabrique.createDe();
		d.setFace(listeFaces);
		d.setNbParJoueur(nbParJoueur);
		d.setNbTotalDes(nbTotalDes);
		return d;
	}
	
	public List<Carte> listeCartes(){
		List<Carte> listeCartes = new ArrayList<>();
		
		NodeList cartes = racine.getElementsByTagName("carte");
		for (int i = 0; i<cartes.getLength(); i++){
			Element carteCourante = (Element) cartes.item(i);
			int cout = Integer.parseInt(carteCourante.getAttribute("cout"));
			int codeEffet = Integer.parseInt(carteCourante.getAttribute("codeEffet"));
			int nb = Integer.parseInt(carteCourante.getAttribute("nb"));
			String effet = carteCourante.getAttribute("effet");
			String src = carteCourante.getAttribute("src");
		//	String contenu = carteCourante.getTextContent();
		//	System.out.println(contenu); 
		// TODO mettre le xml aussi dans le jsp afin de l'afficher par rapport au codeEffet de la carte jouée
			Carte c = fabrique.createCarte();
			c.setCodeEffet(codeEffet);
			c.setCout(cout);
			c.setEffet(effet);
			c.setNb(nb);
			c.setSrc(src);
			listeCartes.add(c);
		}
//		System.out.println(listeCartes.size());
		return listeCartes;
	}

}
