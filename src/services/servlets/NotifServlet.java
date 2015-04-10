package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.NotificationLoadService;

@SuppressWarnings("serial")
public class NotifServlet extends HttpServlet implements Servlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		@SuppressWarnings("unchecked")
		Map<String,String[]> pars = req.getParameterMap();
		resp.setContentType("application/json");
		if(pars.containsKey("logKey")){
			String key = req.getParameter("logKey");
			resp.getWriter().println(NotificationLoadService.getNotif(key));
		}
	}
}
