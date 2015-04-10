package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UserCreation;

public class UserCreationServlet extends HttpServlet implements Servlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			Map<String,String[]> pars = req.getParameterMap();
			if(pars.containsKey("log") && pars.containsKey("pwd") && pars.containsKey("prenom") && pars.containsKey("nom")){
				String login = req.getParameter("log");
				String pwd = req.getParameter("pwd");
				String prenom = req.getParameter("prenom");
				String nom = req.getParameter("nom");
				resp.setContentType("application/json");
				resp.getWriter().println(UserCreation.createUser(login, pwd, prenom, nom)); // on renvoie un objet JSON
				}
			}
}
