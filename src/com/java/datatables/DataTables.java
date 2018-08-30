package com.java.datatables;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

@WebServlet("/DataTables")
public class DataTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DataTables() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("request");
	UserEntity userEntity = new UserEntity("小张","12","女");
	  ArrayList<UserEntity> arrayList = new	ArrayList<>();
		
	for(int i=0;i<200;i++){
		if(i%2==0){
			userEntity = new UserEntity("小张"+i,"12"+i,"女"+i);
		}else{
			userEntity = new UserEntity("猪八戒"+i,"12"+i,"女"+i);
		}
		arrayList.add(userEntity);
	}
	
	//调用Serivce(parameter)层的出后台数据是集合
	//在把后台数据集合,组装到dataUser对象中,
	//后面的步骤不变
	/***
	 * 
	 * 在把集合组装层一个对象并返回json数据  jsp用ajax接受
	***/
     DataUser dataUser = new	DataUser();
         dataUser.setData(arrayList);
         //把dataUser转换成json数据
         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");  
         PrintWriter writer = response.getWriter();
         //对象转换成json数据
         JSONObject fromObject = JSONObject.fromObject(dataUser);
  System.out.println("writer///"+fromObject);
         writer.print(fromObject);
         writer.flush();
         writer.close();
	}
}
