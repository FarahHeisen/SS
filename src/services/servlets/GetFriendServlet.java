package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.GetFriendService;
import services.NotificationLoadService;

public class GetFriendServlet extends HttpServlet implements Servlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		@SuppressWarnings("unchecked")
		Map<String,String[]> pars = req.getParameterMap();
		resp.setContentType("application/json");
		if(pars.containsKey("key")){
			String key = req.getParameter("key");
			resp.getWriter().println(GetFriendService.getFriend(key));
		}
	}
}
