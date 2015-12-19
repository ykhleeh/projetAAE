package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import domaine.Info;
import domaine.Joueur;
import domaine.JoueurPartie;
import usecases.GestionParties;

/**
 * Servlet implementation class AfficherMainsJoueur
 */
@WebServlet("/affichermains.html")
public class AfficherMainsJoueur extends HttpServlet {
	@EJB GestionParties gp;

	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherMainsJoueur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = (String) request.getParameter("pseudo");
		JoueurPartie j = gp.getJoueurPartie(pseudo);
		Info info = new Info();
		info.setNbCartes(String.valueOf(j.getMainCarte().size()));
		info.setNbDes(String.valueOf(j.getMainDe().size()));
		
		response.setContentType("application/json");
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = response.getWriter();
		om.writeValue(pw, info);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
