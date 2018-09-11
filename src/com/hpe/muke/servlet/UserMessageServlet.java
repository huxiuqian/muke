package com.hpe.muke.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hpe.muke.po.Message;
import com.hpe.muke.po.Reply;
import com.hpe.muke.po.User;
import com.hpe.muke.service.MessageService;
import com.hpe.muke.service.ThemeService;
import com.hpe.muke.service.UserService;
import com.hpe.muke.service.impl.MessageServiceImpl;
import com.hpe.muke.service.impl.ThemeServiceImpl;
import com.hpe.muke.service.impl.UserServiceImpl;
import com.hpe.muke.util.IPUtil;
import com.hpe.muke.util.Page;

/**
 * Servlet implementation class UserMessageServlet
 */
@WebServlet("/UserMessageServlet")
public class UserMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MessageService messageService = new MessageServiceImpl();
	ThemeService themeService = new ThemeServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMessageServlet() {
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
		if("getMyMsg".equals(action)){
			getMyMsg(request, response);
		}else if("getTheme".equals(action)){
			getTheme(request, response);
		}else if("add".equals(action)){
			add(request, response);
		}else if ("replyMsg".equals(action)) {
			replyMsg(request, response);
		}
	}
	private void getMyMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		Page page = new Page();
		if(pageNum == null || pageNum.equals("")){
			pageNum = "1";
		}
		page.setCurPage(Integer.parseInt(pageNum));
		System.out.println(user.getUserid());
		Page resPage = messageService.queryNewById(user.getUserid(),page);	
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();
		String dateJson = gson.toJson(resPage);
		//System.out.println(dateJson);
		response.getWriter().print("{\"data\":"+dateJson+"}");
	}
	private void getTheme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		List themeList = themeService.selectTheme();
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();
		String dateJson = gson.toJson(themeList);
		System.out.println(dateJson);
		response.getWriter().print("{\"data\":"+dateJson+"}");
	}
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		int userid = user.getUserid();
		String msgtopic = request.getParameter("msgtopic");
		String msgcontents = request.getParameter("msgcontents");		
		Timestamp msgtime = new Timestamp(System.currentTimeMillis());
		InetAddress addr = InetAddress.getLocalHost();
		String msgip = addr.getHostAddress().toString();
		int theid = Integer.parseInt(request.getParameter("theid"));
		int state = 0;
		Message message = new Message(userid,msgtopic,msgcontents,msgtime,msgip,theid,state);
		messageService.addMessage(message);
		response.getWriter().print("{\"res\": 1}");	
	}
	private void replyMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Timestamp d = new Timestamp(System.currentTimeMillis());
		HttpSession session = request.getSession();
		String mid = request.getParameter("msgid");
		String replycontents = request.getParameter("rc");
		User user = (User) session.getAttribute("user");
		int msgid = Integer.parseInt(mid);
		int userid = user.getUserid();
		InetAddress addr = InetAddress.getLocalHost();
		String replyip = addr.getHostAddress().toString();
		// System.out.println(ip);
		Reply reply = new Reply(msgid, userid, replycontents, d, replyip);
		int result = messageService.replyMsg(reply);
		String json = "";
		if (result == 1) {
			json = "{\"success\":\"true\"}";
			response.getWriter().print(json);
		} else if (result == -1) {
			json = "{\"success\":\"false\"}";
			response.getWriter().print(json);
		}
	}		
}
