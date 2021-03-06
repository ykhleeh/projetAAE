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
import domaine.JoueurPartie;
import usecases.GestionParties;
import usecasesimpl.GestionDesImpl;

/**
 * Servlet implementation class LancerDe
 */
@WebServlet("/lancerdes.html")
public class LancerDe extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB GestionParties gp;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LancerDe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Info info = gp.lancerDes();
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
