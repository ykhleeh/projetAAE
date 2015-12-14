package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login.html")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		getServletContext().setAttribute("login", "LOGINNNN");
		String pseudo = request.getParameter("pseudo");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if (pseudo.equals("")) {
			System.out.println("merde");
			request.setAttribute("message", "pseudo vide mothafucka!!  :(");
			request.getRequestDispatcher("index.html").forward(request, response);
//			getServletContext().getNamedDispatcher("login.html").forward(request, response);
		}
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
