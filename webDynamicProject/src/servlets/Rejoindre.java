package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import domaine.Info;
import domaine.Joueur;
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
		System.out.println("REJOINDRE");
		Info info = new Info();
		gp.initialiser();
		List<Joueur> joueurs = gp.getJoueurs();
		int t = joueurs.size();
		if (joueurs.size() == 1){
			System.out.println("Lancement timer");
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					if (gp.getJoueurs().size()>=2){
						gp.commencerPartie();
					}else{
						System.out.println("ANNULE");
						gp.annulerPartie();
						timer.cancel();
						timer.purge();
						request.setAttribute("message", "La partie a été annulée. Pas assez de joueurs.");
						try {
							response.sendRedirect("menu.html");
						} catch (IOException e) {
							e.printStackTrace();
						}
						return;
					}
				}
			};
			timer.schedule(task, 10000);
		}
		HttpSession session = request.getSession();
		synchronized (session) {
			pseudo = (String) session.getAttribute("user");
		}
		if (gp.rejoindreLaPartie(pseudo)) {
			System.out.println(pseudo + " a rejoint la partie");
			joueurs = gp.getJoueurs();
			info.setUser(pseudo);
			info.getJoueurs().add(pseudo);
			info.setEtat(gp.getEtatPartie());
			ObjectMapper mapper = new ObjectMapper();
			PrintWriter out = response.getWriter();
			mapper.writeValue(out, info);
			return; 
	}
		request.setAttribute("message", "Bienvenue " + pseudo);
	//	gp.rejoindreLaPartie(pseudo); // Utiliser la m�thode avec dernier id plutot que de tout parcourir pour trouver la bonne partie
		System.out.println("Bienvenue "+pseudo);
	//	request.getRequestDispatcher("jeu.html").forward(request, response);
		//getServletContext().getNamedDispatcher("jeu.html").forward(request, response);
	//	response.sendRedirect("jeu.html");
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
