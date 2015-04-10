package services.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ShareService;

@SuppressWarnings("serial")
public class ShareServlet extends HttpServlet implements Servlet {
	@SuppressWarnings("unchecked")
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		Map<String,String[]> pars = req.getParameterMap();
		if(pars.containsKey("logKey") && pars.containsKey("commentID")&& pars.containsKey("authorID")){
			String logKey = req.getParameter("logKey");
			String commentID = req.getParameter("commentID");
			int authorID=Integer.parseInt(req.getParameter("authorID"));
			resp.setContentType("application/json");
			//utilisé service
			resp.getWriter().println(ShareService.share(logKey, commentID,authorID));
		}
	}
}
