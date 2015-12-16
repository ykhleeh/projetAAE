package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usecases.GestionParties;

/**
 * Servlet implementation class MenuVerif
 */
@WebServlet("/menuverif.html")
public class MenuVerif extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB GestionParties gestionPartie;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuVerif() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean ok = false;
		if(gestionPartie.partieEnCours()){
			ok = true;
		}
		request.setAttribute("verifMenu", ok);
		getServletContext().getNamedDispatcher("menu.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
