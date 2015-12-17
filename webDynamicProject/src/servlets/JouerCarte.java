package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domaine.Info;
import domaine.JoueurPartie;
import domaine.Partie;
import usecases.GestionParties;

/**
 * Servlet implementation class JouerCarte
 */
@WebServlet("/JouerCarte")
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
		response.getWriter().append("Served at: ").append(request.getContextPath());		
		String pseudo;
		int codeEffet;
		String cible;
		HttpSession session = request.getSession();
		synchronized (session) {
			pseudo = (String) session.getAttribute("user");
			codeEffet = Integer.parseInt(request.getParameter("codeEffet"));
			cible = (String) session.getAttribute("cible");
		}		
		gp.jouerCarte(codeEffet, cible);
		Partie partie = gp.getDernierePartie();
		JoueurPartie joueurcourant = partie.getJoueurCourant();
		synchronized (session) {
			session.setAttribute("joueurCourant", joueurcourant);
		}
		Info info = new Info();
		info.setCartes(joueurcourant.getMainCarte());
		info.setDes(joueurcourant.getMainDe());
		info.setEtat(partie.getEtat());
		info.setJoueurCourant(joueurcourant.getJoueur().getPseudo());
		info.setNbCartes(""+joueurcourant.getMainCarte().size());
		info.setNbDes(""+joueurcourant.getMainDe().size());
		info.setUser(pseudo);
		
//		request.getRequestDispatcher("jeumanager.html").forward(request, response);
		//getServletContext().getNamedDispatcher("jeumanager.html").forward(request, response);
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
