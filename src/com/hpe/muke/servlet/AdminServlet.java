package com.hpe.muke.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.muke.po.Admin;
import com.hpe.muke.po.User;
import com.hpe.muke.service.AdminService;
import com.hpe.muke.service.UserService;
import com.hpe.muke.service.impl.AdminServiceImpl;
import com.hpe.muke.service.impl.UserServiceImpl;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("login".equals(action)){
			login(request, response);
		}else if("exit".equals(action)){
			exit(request, response);
		}else if("updatepwd".equals(action)){
			updatepwd(request, response);
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		//System.out.println(name+pwd);
		AdminService adminService = new AdminServiceImpl();
		Admin admin = adminService.adminLogin(name, pwd);
		HttpSession session = request.getSession();
		if(admin!=null){
			session.setAttribute("admin",admin);
			response.getWriter().print("{\"res\": 1}");
		}else{
			response.getWriter().print("{\"res\": -1, \"info\":\"用户名或密码错误，请重新输入！\"}");
		}
	}
	private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  	HttpSession session = request.getSession(false);//防止创建Session   	          
        session.removeAttribute("admin");  
        response.getWriter().print("{\"res\": 0}");
	}
	private void updatepwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String oldpassword = request.getParameter("oldpassword");	
		String newpassword = request.getParameter("newpassword");	
		AdminService adminService = new AdminServiceImpl();
		Admin admin = null;
		HttpSession session=request.getSession();
		admin = (Admin) session.getAttribute("admin");
		if(admin.getPwd().equals(oldpassword)){
			admin.setPwd(newpassword);;
			adminService.updateAdminPwd(admin);    
	        session.removeAttribute("admin");  
			response.getWriter().print("{\"res\": 1}");	
		}else{
			response.getWriter().print("{\"res\": -1, \"info\":\"密码错误，请重新输入！\"}");
		}
	}
}
