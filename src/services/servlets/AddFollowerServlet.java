package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AddFollowerService;

@SuppressWarnings("serial")
public class AddFollowerServlet extends HttpServlet implements Servlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		
			@SuppressWarnings("unchecked")
			Map<String,String[]> pars = req.getParameterMap();
			if(pars.containsKey("my_Key") && pars.containsKey("his_id")){
				String my_key = req.getParameter("my_Key");
				int his_id = Integer.parseInt(req.getParameter("his_id"));
				resp.setContentType("application/json");
				//utilisé service
				resp.getWriter().println(AddFollowerService.addFollower(my_key, his_id));
				}
			}
}
