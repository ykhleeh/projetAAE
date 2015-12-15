package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domaine.Joueur;
import usecases.GestionJoueurs;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.html")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB GestionJoueurs gestionJoueurs;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO mettre le login courant dans le context pour gérer l'attente
		String pseudo = request.getParameter("pseudo");
		String passwd = request.getParameter("mdp");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (pseudo.equals("") || passwd.equals("")) {
			System.out.println("merde");
			request.setAttribute("message", "Veuillez remplir tous les champs!!");
			request.getRequestDispatcher("index.html").forward(request, response);
//			getServletContext().getNamedDispatcher("login.html").forward(request, response);
		}
		Joueur j = gestionJoueurs.rechercherJoueur(pseudo);
		if (j != null)
			System.out.println("youhou");
		if (!gestionJoueurs.authentifier(pseudo, passwd)) {
			request.setAttribute("message", "Le pseudo ou le mot de passe est incorrect!!");
			request.getRequestDispatcher("index.html").forward(request, response);
		}
		HttpSession session = request.getSession();
		synchronized (session) {
			session.setAttribute("login", pseudo);
		}
		request.setAttribute("message", "Connecte MOTHAFUCKA!lljflkhslfh!!!");
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
