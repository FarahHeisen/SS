package services.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.FindingService;
import services.ServicesTools;

public class FindingServlet extends HttpServlet implements Servlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		Date after=null, before=null;
		@SuppressWarnings("unchecked")
		Map<String,String[]> pars = req.getParameterMap();
		if( pars.containsKey("followed") && pars.containsKey("shared")){
			String key = req.getParameter("key");
			String[] author = req.getParameterValues("author");
			String[] hashtags = req.getParameterValues("hashtag");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy-hh-mm-ss");
			resp.setContentType("application/json");
			try {
				if(pars.containsKey("after"))
					after = sdf.parse(req.getParameter("after"));
				if(pars.containsKey("before"))
					before = sdf.parse(req.getParameter("before"));
			} catch (ParseException e) {
				resp.getWriter().println(ServicesTools.error("Format date ", e.getMessage()));
			}
			boolean followed = Boolean.parseBoolean(req.getParameter("followed"));
			boolean shared = Boolean.parseBoolean(req.getParameter("shared"));
			//String[] keyWord = req.getParameterValues("keyWord"); //recherche de mots présents dans le commentaire recherché
			resp.getWriter().println(FindingService.find(key,author,hashtags, after, before, followed,shared/*, keyWord*/));
		}
	}
}