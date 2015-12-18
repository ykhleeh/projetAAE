package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import domaine.Carte;
import domaine.De;
import domaine.Info;
import domaine.Joueur;
import domaine.JoueurPartie;
import usecases.GestionJoueurs;
import usecases.GestionParties;

/**
 * Servlet implementation class JeuManager
 */
@WebServlet("/jeumanager.html")
public class JeuManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB GestionParties gp;
	@EJB GestionJoueurs gj;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JeuManager() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Jeumanager envoyé");
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("user");
		System.out.println("PSEUDO = " + pseudo);
		gp.commencerPartie();
		Info info = new Info();
		info.setUser(pseudo);
		JoueurPartie joueurPartie = gp.getJoueurPartie(pseudo);
		List<Carte> mainCarte = joueurPartie.getMainCarte();
		info.setCartes(mainCarte);
		List<De> mainDe = joueurPartie.getMainDe();
		for (De de : mainDe){
			System.out.println("De n° " + de.getId());
		}
		if (!mainDe.isEmpty())
			info.setDes(mainDe);
			
		info.setEtat(gp.getDernierePartie().getEtat());
		info.setJoueurCourant(joueurPartie.getJoueur().getPseudo());
		List<De> des = joueurPartie.getMainDe();
		info.setDes(des);
		List<String> list = new ArrayList<>();
		for (Joueur j : gp.getJoueurs()){
			list.add(j.getPseudo());
		}
		info.setJoueurs(list);
		info.setCartes(joueurPartie.getMainCarte());
		System.out.println(info.toString());
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		mapper.writeValue(out, info);	
	}

}
