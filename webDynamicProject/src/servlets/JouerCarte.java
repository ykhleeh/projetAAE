package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import domaine.Info;
import domaine.JoueurPartie;
import domaine.Partie;
import usecases.GestionParties;

/**
 * Servlet implementation class JouerCarte
 */
@WebServlet("/jouercarte.html")
public class JouerCarte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@EJB GestionParties gp;
	
    public JouerCarte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codeS = (String) request.getParameter("code");
		String cible = (String) request.getParameter("cible");
		System.out.println("CODE S = " +codeS);
		System.out.println("CIBLE = " + cible);
		String pseudo;
		HttpSession session = request.getSession();
		//codeEffet = Integer.parseInt(codeS);
		synchronized (session) {
			pseudo = (String) session.getAttribute("user");
			//cible = (String) session.getAttribute("cible");
		}
		//TODO G�rer la cible
		Info info = gp.jouerCarte(codeS, cible);
		info.setJoueurCourant(gp.joueurCourant());
		Partie partie = gp.getDernierePartie();
		JoueurPartie joueurcourant = partie.getJoueurCourant();
		synchronized (session) {
			session.setAttribute("joueurCourant", joueurcourant);
		}
		response.setContentType("application/json");
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		om.writeValue(pw, info);
		
//		request.getRequestDispatcher("jeumanager.html").forward(request, response);
		//getServletContext().getNamedDispatcher("jeumanager.html").forward(request, response);
		//getServletContext().getNamedDispatcher("jeu.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
