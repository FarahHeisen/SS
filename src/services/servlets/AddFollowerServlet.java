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
			if(pars.containsKey("key") && pars.containsKey("id")){
				String my_key = req.getParameter("key");
				int his_id = Integer.parseInt(req.getParameter("id"));
				resp.setContentType("application/json");
				//utilisé service
				resp.getWriter().println(AddFollowerService.addFollower(my_key, his_id));
				}
			}
}
