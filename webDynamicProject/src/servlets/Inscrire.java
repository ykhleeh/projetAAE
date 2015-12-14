package servlets;


import java.io.IOException;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/inscrire.html")
public class Inscrire extends HttpServlet {
	//@EJB 
//	private GestionPartie gestionPartie;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*		
		String pseudo = request.getParameter("pseudo");
//		List<String> joueurs = gestionPartie.listerJoueurs();

		final ServletContext ctx = getServletContext();
		synchronized (ctx) {
			if (ctx.getAttribute("joueurs") == null) {
				ctx.setAttribute("joueurs", new HashSet<String>());
			}
			@SuppressWarnings("unchecked")
			final HashSet<String> joueurs = (HashSet<String>) ctx.getAttribute("joueurs");
			if (joueurs.contains(pseudo)) {
				String message = "Ce pseudo est deje utilise. Veuillez en utiliser un autre S.V.P.";
				request.setAttribute("message", message);
				getServletContext().getNamedDispatcher("index.html").forward(request, response);
				return;				
			}
			if (joueurs.isEmpty()) {
				ctx.removeAttribute("vainqueur");
				ctx.removeAttribute("partie");
				ctx.removeAttribute("tousLesPoints");
				// lancer le timer d'inscription
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					
					@Override
					public void run() {
						if (joueurs.size() >= 2) {
//							gestionPartie.commencerPartie();
//							ctx.setAttribute("joueurCourant", gestionPartie.joueurCourant());
							ctx.setAttribute("partie", "encours");
	//						ctx.setAttribute("jouants", gestionPartie.listerJoueurs());
							
						} else {
//							gestionPartie.annulerPartie();
							ctx.setAttribute("partie", "annulee");
							ctx.removeAttribute("joueurs");
						}
						
						
					}
				};
				timer.schedule(task, 30000);
			}
//			if (gestionPartie.rejoindreLaPartie(pseudo)) {
				joueurs.add(pseudo);
				HttpSession session = request.getSession();
				synchronized (session) {
					session.setAttribute("user", pseudo);
					session.setAttribute("nb", 3);
				}
			} // else watcher
			getServletContext().getNamedDispatcher("index.html").forward(request, response);
		}
*/
		getServletContext().getNamedDispatcher("index.html").forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getNamedDispatcher("index.html").forward(request, response);
	}

}
