package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.OperationService;

public class OperationServlet extends HttpServlet implements Servlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6365031382882349582L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException{
		Map<String,String[]> pars = req.getParameterMap();
		if(pars.containsKey("op") && pars.containsKey("a") && pars.containsKey("b")){
			String op = req.getParameter("op");
			Double valueA = Double.parseDouble(req.getParameter("a"));
			Double valueB = Double.parseDouble(req.getParameter("b"));
			resp.setContentType("text/plain");
			try {
				double res = OperationService.calcul(valueA, valueB, op);
				resp.getWriter().println("resultat : " + res);
			}
			catch (ArithmeticException e){
				resp.getWriter().println("Erreur : division par zéro");
			}
		}
		
		
	}
	
}
