package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountItem;

import service.impl.AccountItemServiceImpl;




public class AccountItemServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AccountItemServiceImpl service =new AccountItemServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String method = req.getParameter("method");
		if("getAccountItemList".equals(method)) {
			getAccountItemList(req,resp);
		} else if("addAccountItem".equals(method)){
			addAccountItem(req,resp);
		} else if("delAccountItem".equals(method)){
			delAccountItem(req,resp);
		} else if("mdfAccountItem".equals(method)){
			mdfAccountItem(req,resp);
		} else if("getAccountItemListInfo".equals(method)){
			getAccountItemListInfo(req,resp);
		}
	}
	private void getAccountItemList(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String frDate = req.getParameter("frDate");
		String toDate = req.getParameter("toDate");
		String[] arr  = req.getParameterValues("type");
		List<String> list = (arr == null || arr.length == 0 ) ? null:Arrays.asList(arr);
		if(frDate == "") frDate=null; 
		if(toDate == "") toDate=null;
		String result = service.getAccountList(list, frDate, toDate);
		System.out.println(result);
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write(result);
	}
	private void getAccountItemListInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String frDate = req.getParameter("frDate");
		String toDate = req.getParameter("toDate");
		String[] arr  = req.getParameterValues("type");
		List<String> list = (arr == null || arr.length == 0 ) ? null:Arrays.asList(arr);
		if(frDate == "") frDate=null; 
		if(toDate == "") toDate=null;
		String result = service.getAccountListInfo(list, frDate, toDate);
		System.out.println(result);
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().write(result);
	}
	private void addAccountItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		AccountItem item = new AccountItem();
		String cName;
		item.setName(req.getParameter("name"));
		item.setDate(req.getParameter("date"));
		item.setMoney(Double.parseDouble(req.getParameter("money")));
		item.setDetails(req.getParameter("details"));
		cName = req.getParameter("type");
		System.out.println(item.getDate());
		service.add(item,cName);
		resp.sendRedirect("index.jsp");
	}
	private void delAccountItem(HttpServletRequest req, HttpServletResponse resp){
		int id = Integer.parseInt(req.getParameter("id"));
		String cName = req.getParameter("cName");
		service.delete(id, cName);
	}
	private void mdfAccountItem(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		AccountItem item = new AccountItem();
		String cName;
		item.setName(req.getParameter("name"));
		item.setDate(req.getParameter("date"));
		item.setMoney(Double.parseDouble(req.getParameter("money")));
		item.setId(Integer.parseInt(req.getParameter("id")));
		item.setDetails(req.getParameter("details"));
		cName = req.getParameter("type");
		System.out.println(item.getDate());
		service.modify(item,cName);
		resp.sendRedirect("index.jsp");
	}
	

}
