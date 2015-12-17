package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String pseudo;
		HttpSession session = request.getSession();
		synchronized (session) {
			pseudo = (String) session.getAttribute("user");
		}
		gestion.creer(nom, pseudo);
		getServletContext().setAttribute("nomPartie", nom);
		request.setAttribute("message", "La partie a été créée! Attendez des joueurs mtn.");

		response.sendRedirect("attente.html");
		//getServletContext().getNamedDispatcher("attente.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
