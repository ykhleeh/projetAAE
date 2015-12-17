package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import domaine.Info;
import domaine.Joueur;
import domaine.JoueurPartie;
import usecases.GestionJoueurs;
import usecases.GestionParties;

/**
 * Servlet implementation class JeuManager
 */
@WebServlet("/jeu.html")
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
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("user");
		gp.commencerPartie();
		Info info = new Info();
		info.setUser(gp.joueurCourant());
		JoueurPartie joueurPartie = gp.getJoueurPartie(pseudo);
		List<Carte> mainCarte = joueurPartie.getMainCarte();
		info.setCartes(mainCarte);
		info.setDes(joueurPartie.getMainDe());
		info.setEtat(gp.getDernierePartie().getEtat());
		info.setJoueurCourant(joueurPartie.getJoueur().getPseudo());
		info.setDes(joueurPartie.getMainDe());
		info.setCartes(joueurPartie.getMainCarte());
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		mapper.writeValue(out, info);	
		
		
	//	info.setJoueurCourant(gp.getDernierePartie().getJoueurCourant().);
			
	//	request.setAttribute("message", "C'est parti pour une nouvelle partie");		
	//	request.setAttribute("nbJoueurs", gp.getJoueurs().size());		
	//	getServletContext().getNamedDispatcher("jeu.html").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
