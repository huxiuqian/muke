package com.hpe.muke.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hpe.muke.service.UserService;
import com.hpe.muke.service.impl.UserServiceImpl;
import com.hpe.muke.util.Page;

/**
 * Servlet implementation class AdminUserServlet
 */
@WebServlet("/AdminUserServlet")
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserServlet() {
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
		if ("getuser".equals(action)) {
			getUser(request, response);
		} else if ("deleteuser".equals(action)) {
			deleteUser(request, response);
		} else if ("restoreuser".equals(action)) {
			restoreUser(request, response);
		}
	}

	private void getUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserService usi = new UserServiceImpl();
		Page page = new Page();
		String pagenum = request.getParameter("pagenum");
		int curpage = Integer.parseInt(pagenum);
		page.setCurPage(curpage);
		String username = "";
		username = request.getParameter("username");
		System.out.println(username);
		Page page1 = usi.searchByName(username, page);
		Gson gson = new GsonBuilder().setDateFormat("MM-dd HH:mm:ss").create();
		String json = gson.toJson(page1);
		response.getWriter().print(json);	
	}
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserServiceImpl usi = new UserServiceImpl();
		String id = request.getParameter("userid");
		int userid = Integer.parseInt(id);
		int result = usi.deleteUser(userid);
		String json = "";
		if (result == 1) {
			json = "{\"success\":\"false\"}";
			response.getWriter().print(json);

		} else if (result == -1) {
			json = "{\"success\":\"failed\"}";
			response.getWriter().print(json);

		}
	}
	private void restoreUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserServiceImpl usi = new UserServiceImpl();
		String id = request.getParameter("userid");
		int userid = Integer.parseInt(id);
		int result = usi.restoreUser(userid);
		String json = "";
		if (result == 1) {
			json = "{\"success\":\"false\"}";
			response.getWriter().print(json);

		} else if (result == -1) {
			json = "{\"success\":\"failed\"}";
			response.getWriter().print(json);

		}
	}
}
