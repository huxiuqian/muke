package com.hpe.muke.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.muke.po.User;
import com.hpe.muke.service.UserService;
import com.hpe.muke.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		String action = request.getParameter("action");
		if("login".equals(action)){
			login(request, response);
		}else if("exit".equals(action)){
			exit(request, response);
		}else if("register".equals(action)){
			register(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String realname = request.getParameter("realname");		
		String sex = request.getParameter("sex");
		String hobbys = request.getParameter("hobbys");
		String birthday = request.getParameter("birthday");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		Timestamp createtime = new Timestamp(System.currentTimeMillis());
		int state = 0;
		UserService userService = new UserServiceImpl();
		User user = null;
		user= userService.selectUserByName(username);
		if(user != null){
			response.getWriter().print("{\"res\": -1, \"info\":\"用户名已存在，请重新输入！\"}");
		}else{
			user = new User(username,password,realname,sex,hobbys,birthday,city,email,qq,createtime,state);
			userService.addUser(user);
			response.getWriter().print("{\"res\": 1}");
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService userService = new UserServiceImpl();
		User user = userService.userLogin(username, password);
		HttpSession session = request.getSession();
		if(user!=null){
			session.setAttribute("user",user);
			response.getWriter().print("{\"res\": 1}");
		}else{
			response.getWriter().print("{\"res\": -1, \"info\":\"用户名或密码错误，请重新输入！\"}");
		}
	}
	private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  	HttpSession session = request.getSession(false);//防止创建Session   	          
	        session.removeAttribute("user");  
	        response.getWriter().print("{\"res\": 0}");
	}
	
}
