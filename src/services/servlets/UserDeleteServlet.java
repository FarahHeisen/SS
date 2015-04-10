package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UserDeleteService;

public class UserDeleteServlet extends HttpServlet implements Servlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			Map<String,String[]> pars = req.getParameterMap();
			if(pars.containsKey("log")){
				String log = req.getParameter("log");
				resp.setContentType("text/javascript");
				resp.getWriter().println(UserDeleteService.deleteUser(log));
				}
			}
}
