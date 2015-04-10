package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.UserLogoutService;

public class UserLogoutServlet  extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			Map<String,String[]> pars = req.getParameterMap();
			if(pars.containsKey("key")){
				String key = req.getParameter("key");
				resp.setContentType("application/json");
				JSONObject ok = UserLogoutService.logoutUser(key);
				resp.getWriter().println(ok);
			}
		}	
	
}
