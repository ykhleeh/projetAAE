package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domaine.Carte;
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("user");
		List<Joueur> joueurs = gj.listerPseudos();
		for (Joueur j : joueurs){
			JoueurPartie joueurPartie = gp.getJoueurPartie(j.getPseudo());
			
			List<Carte> mainCarte = joueurPartie.getMainCarte();
			System.out.println(j.getPseudo());
			for (Carte c : mainCarte){
				System.out.println(c.getCodeEffet());
			}
		}
		
		
//		request.setAttribute("listeCarte", mainCarte);
		request.setAttribute("message", "C'est parti pour une nouvelle partie");		
		request.setAttribute("nbJoueurs", gp.getJoueurs().size());		
		getServletContext().getNamedDispatcher("jeu.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
