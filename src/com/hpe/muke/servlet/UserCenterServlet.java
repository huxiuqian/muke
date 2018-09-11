package com.hpe.muke.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hpe.muke.po.User;
import com.hpe.muke.service.UserService;
import com.hpe.muke.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserCenterServlet
 */
@WebServlet("/UserCenterServlet")
public class UserCenterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCenterServlet() {
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
		if("update".equals(action)){
			update(request, response);
		}else if("updatepwd".equals(action)){
			updatepwd(request, response);
		}else if("getUser".equals(action)){
			getUser(request, response);
		}
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String realname = request.getParameter("realname");		
		String sex = request.getParameter("sex");
		String hobbys = request.getParameter("hobbys");
		String birthday = request.getParameter("birthday");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String qq = request.getParameter("qq");
		UserService userService = new UserServiceImpl();
		User user = null;
		HttpSession session=request.getSession();
		user = (User) session.getAttribute("user");
		//System.out.println(user.getUserid()+user.getUsername());
		//user = new User(realname,sex,hobbys,birthday,city,email,qq);
		user.setRealname(realname);
		user.setSex(sex);
		user.setHobbys(hobbys);
		user.setBirthday(birthday);
		user.setCity(city);
		user.setEmail(email);
		user.setQq(qq);
		userService.updateUser(user);
		response.getWriter().print("{\"res\": 1}");		
	}
	private void updatepwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String oldpassword = request.getParameter("oldpassword");	
		String newpassword = request.getParameter("newpassword");	
		UserService userService = new UserServiceImpl();
		User user = null;
		HttpSession session=request.getSession();
		user = (User) session.getAttribute("user");
		//System.out.println(user.getUserid()+user.getPassword());
		//user = new User(realname,sex,hobbys,birthday,city,email,qq);
		if(user.getPassword().equals(oldpassword)){
			user.setPassword(newpassword);;
			userService.updateUserPwd(user);    
	        session.removeAttribute("user");  
			response.getWriter().print("{\"res\": 1}");	
		}else{
			response.getWriter().print("{\"res\": -1, \"info\":\"密码错误，请重新输入！\"}");
		}
	}
	private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserService userService = new UserServiceImpl();
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		User user1 = userService.selectUserByName(user.getUsername());
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();
		String dateJson = gson.toJson(user1);
		//System.out.println(dateJson);
		response.getWriter().print("{\"res\": 1, \"data\":"+dateJson+"}");
	}
}
