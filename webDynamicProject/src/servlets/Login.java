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

	@EJB
	GestionJoueurs gestionJoueurs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String passwd = request.getParameter("mdp");
		// String dispatcher = "/menuverif.html";
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (pseudo.equals("") || passwd.equals("")) {
			request.setAttribute("message", "Veuillez remplir tous les champs!!");
			// dispatcher = "index.html";
			getServletContext().getNamedDispatcher("index.html").forward(request, response);
		} else {
			Joueur j = gestionJoueurs.rechercherJoueur(pseudo);
			if (j == null) {
				request.setAttribute("message", "Vous n'êtes pas encore inscrit");
				// dispatcher = "inscription.html";
				getServletContext().getNamedDispatcher("inscription.html").forward(request, response);
			} else {
				if (!gestionJoueurs.authentifier(pseudo, passwd)) {
					request.setAttribute("message", "Le pseudo ou le mot de passe est incorrect!!");
					getServletContext().getNamedDispatcher("index.html").forward(request, response);
					// dispatcher ="index.html";
				} else {
					HttpSession session = request.getSession();
					synchronized (session) {
						session.setAttribute("user", pseudo);
					}
					// getServletContext().getNamedDispatcher("/menuverif.html").forward(request,
					// response);
					request.getRequestDispatcher("/menuverif.html").forward(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
