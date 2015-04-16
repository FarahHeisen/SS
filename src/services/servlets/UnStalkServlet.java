package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.UnStalkService;

@SuppressWarnings("serial")
public class UnStalkServlet extends HttpServlet implements Servlet {
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			Map<String,String[]> pars = req.getParameterMap();
			if(pars.containsKey("key") && pars.containsKey("his_id")){
				String key = req.getParameter("key");
				int id = Integer.parseInt(req.getParameter("his_id"));
				resp.setContentType("application/json");

				resp.getWriter().println(UnStalkService.unfollow(key, id));
				}
			}
}
