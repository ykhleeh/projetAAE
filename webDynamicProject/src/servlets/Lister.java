package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domaine.Partie;
import usecases.GestionJoueurs;
import usecases.GestionParties;

/**
 * Servlet implementation class Lister
 */
@WebServlet("/lister.html")
public class Lister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB GestionParties gestionParties;
//	@EJB GestionJoueurs gestionJoueurs;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Lister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<Partie> liste = gestionParties.listerParties();
		if (!liste.isEmpty())
			request.setAttribute("liste", liste);
		getServletContext().getNamedDispatcher("liste.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
