package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domaine.Partie;
import usecases.GestionParties;

/**
 * Servlet implementation class Creer
 */
@WebServlet("/creer.html")
public class Creer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	GestionParties gestion;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Creer() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom = request.getParameter("nom");
		
		gestion.creer(nom);
		getServletContext().setAttribute("nomPartie", nom);
		request.setAttribute("message", "La partie a �t� cr��e! Attendez des joueurs mtn.");

		getServletContext().getNamedDispatcher("attente.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
