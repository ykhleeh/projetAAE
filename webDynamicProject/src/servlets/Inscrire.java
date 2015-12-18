package servlets;


import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domaine.Joueur;
import usecases.GestionJoueurs;
import usecasesimpl.GestionJoueursImpl;

@SuppressWarnings("serial")
@WebServlet("/inscrire.html")
public class Inscrire extends HttpServlet {
	@EJB 
	GestionJoueurs gestion;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");
		
		if (pseudo.equals("") || mdp.equals("")) {
			System.out.println("merde");
			request.setAttribute("message", "Veuillez remplir tous les champs!!");
			request.getRequestDispatcher("index.html").forward(request, response);
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ YOUHOU");
//			getServletContext().getNamedDispatcher("inscription.html").forward(request, response);
//			response.sendRedirect("inscription.html");
//			request.getRequestDispatcher("inscription.html").forward(request, response);
		}
		
		List<Joueur> autres = gestion.listerPseudos();
		for (Joueur j : autres){
			if (pseudo.equals(j.getPseudo())){
				System.err.println("Pseudo déjà  présent");
				request.setAttribute("message", "Le pseudo est déjà  utilisé. Réessayez!!!");
				getServletContext().getNamedDispatcher("inscription.html").forward(request, response);
				return;
			}
		}

		gestion.enregistrer(new Joueur(pseudo, mdp));
		
		request.setAttribute("message", "Enregistrement ok");

		getServletContext().getNamedDispatcher("index.html").forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getNamedDispatcher("index.html").forward(request, response);
	}

}
