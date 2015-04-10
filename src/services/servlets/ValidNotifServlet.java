package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ServicesTools;
import services.ValideNotifService;

@SuppressWarnings("serial")
public class ValidNotifServlet extends HttpServlet implements Servlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		@SuppressWarnings("unchecked")
		Map<String,String[]> pars = req.getParameterMap();
		resp.setContentType("application/json");
		if(pars.containsKey("notifId")){
			String notifId = req.getParameter("notifId");
			resp.getWriter().println(ValideNotifService.validate(notifId));
		}else{
			resp.getWriter().println(ServicesTools.error("PB Param", "Il manque un des paramètre primordial"));
		}
	}
}
