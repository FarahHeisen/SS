package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import services.UserLogin;

public class UserLoginServlet extends HttpServlet implements Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			Map<String,String[]> pars = req.getParameterMap();
			if(pars.containsKey("log") && pars.containsKey("pwd")){
				String log = req.getParameter("log");
				String pwd = req.getParameter("pwd");
				resp.addHeader("Access-Control-Allow-Origin", "*");
				resp.setContentType("application/json");
				JSONObject user = UserLogin.authentifiateUser(log, pwd);
				resp.getWriter().println(user);
			}
		}	
	
}
