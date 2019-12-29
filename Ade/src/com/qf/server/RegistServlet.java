package com.qf.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.qf.control.UserControl;
import com.qf.model.BaseBean;

@WebServlet("/registservlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//放置乱码,修改编码格式
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
				
		//获取上传参数
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		String likename = request.getParameter("name");
		String phone = request.getParameter("phone");
		
				
		UserControl control = new UserControl();
		BaseBean bean = control.regist(username, password, likename, phone);
				
		//将对象转换为json格式的字符串
		Gson gson = new Gson();
		String json = gson.toJson(bean);
				
		//返回数据
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
