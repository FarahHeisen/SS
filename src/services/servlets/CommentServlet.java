package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.CommentService;

//Il faut retiré logKey ou ID ou ne doit en transporté que 1 dans les pages
public class CommentServlet extends HttpServlet implements Servlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
			Map<String,String[]> pars = req.getParameterMap();
			if(pars.containsKey("key") && pars.containsKey("comment") && pars.containsKey("humeur")){
				String logKey = req.getParameter("key");
				String comment = req.getParameter("comment");
				String humeur = req.getParameter("humeur");
				resp.setContentType("application/json");
				//utilisé service
				resp.getWriter().println(CommentService.comment(logKey, comment,humeur));
				}
			}
}
//li328.lip6.fr:8280/BERTRAND_DHOUIB/write?id=111&key=tralala&comment=Youpie
