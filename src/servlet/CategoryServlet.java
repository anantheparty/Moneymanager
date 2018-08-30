package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.impl.CategoryServiceImpl;




public class CategoryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CategoryServiceImpl service =new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("allCategory".equals(method)) {
			allCategory(req,resp);
		} 
	}
	private void allCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String result = service.findAll();
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write(result);
	}
	

}
