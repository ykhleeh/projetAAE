package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import usecases.GestionParties;

/**
 * Servlet implementation class Rejoindre
 */
@WebServlet("/rejoindre.html")
public class Rejoindre extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB GestionParties gp;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rejoindre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo;
		HttpSession session = request.getSession();
		synchronized (session) {
			pseudo = (String) session.getAttribute("user");
		}
		request.setAttribute("message", "Bienvenue " + pseudo);
		gp.rejoindreLaPartie(pseudo); // Utiliser la méthode avec dernier id plutot que de tout parcourir pour trouver la bonne partie
		System.out.println("Bienvenue "+pseudo);
		request.getRequestDispatcher("jeumanager.html").forward(request, response);
		//getServletContext().getNamedDispatcher("jeumanager.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
