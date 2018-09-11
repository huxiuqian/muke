package com.hpe.muke.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hpe.muke.po.MessageSearch;
import com.hpe.muke.service.MessageService;
import com.hpe.muke.service.impl.MessageServiceImpl;
import com.hpe.muke.util.Page;

/**
 * Servlet implementation class AdminMessageServlet
 */
@WebServlet("/AdminMessageServlet")
public class AdminMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMessageServlet() {
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
		if ("getMsgCount".equals(action)) {
			getMsgCount(request, response);
		} else if ("getReplyCount".equals(action)) {
			getReplyCount(request, response);
		} else if ("deleteMsg".equals(action)) {
			deleteMsg(request, response);
		} else if ("restoreMsg".equals(action)) {
			restoreMsg(request, response);
		} else if ("searchMsg".equals(action)) {
			searchMsg(request, response);
		} else if ("wonderful".equals(action)) {
			wonderful(request, response);
		} else if ("toTop".equals(action)) {
			toTop(request, response);
		}

	}

	private void getMsgCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MessageService msi = new MessageServiceImpl();
		Date date = new Date();// 当前日期
		SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdp.format(date);
		long day = msi.queryMsgCountByDate(date1, date1);
		//System.out.println(day);
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		d.set(Calendar.DATE, d.get(Calendar.DATE) - 7);
		String weekstart = sdp.format(d.getTime());
		long week = msi.queryMsgCountByDate(weekstart, date1);
		//System.out.println(week);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date);
		d2.set(Calendar.DATE, d2.get(Calendar.DATE) - 30);
		String monthstart = sdp.format(d2.getTime());
		long month = msi.queryMsgCountByDate(monthstart, date1);
		//System.out.println(month);
		String json = "{\"day\":" + day + ",\"week\":" + week + ",\"month\":" + month + "}";
		response.getWriter().print(json);

	}
	private void getReplyCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MessageServiceImpl msi = new MessageServiceImpl();
		Date date = new Date();
		SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
		String date1 = sdp.format(date);
		long day = msi.queryReplyCountByDate(date1, date1);
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		d.set(Calendar.DATE, d.get(Calendar.DATE) - 7);
		String weekstart = sdp.format(d.getTime());
		long week = msi.queryReplyCountByDate(weekstart, date1);
		Calendar d2 = Calendar.getInstance();
		d2.setTime(date);
		d2.set(Calendar.DATE, d2.get(Calendar.DATE) - 30);
		String monthstart = sdp.format(d2.getTime());
		long month = msi.queryReplyCountByDate(monthstart, date1);
		String json = "{\"day\":" + day + ",\"week\":" + week + ",\"month\":" + month + "}";
		response.getWriter().print(json);
	}
	private void deleteMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageServiceImpl msi = new MessageServiceImpl();
		String id = request.getParameter("msgid");
		int msgid = Integer.parseInt(id);
		int result = msi.deleteMsg(msgid);
		String json = "";
		if (result == 1) {
			json = "{\"success\":\"false\"}";
			response.getWriter().print(json);
		} else if (result == -1) {
			json = "{\"success\":\"failed\"}";
			response.getWriter().print(json);
		}
	}
	private void restoreMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MessageServiceImpl msi = new MessageServiceImpl();
		String id = request.getParameter("msgid");
		int msgid = Integer.parseInt(id);
		int result = msi.restoreMsg(msgid);
		String json = "";
		if (result == 1) {
			json = "{\"success\":\"false\"}";
			response.getWriter().print(json);
		} else if (result == -1) {
			json = "{\"success\":\"failed\"}";
			response.getWriter().print(json);
		}
	}
	private void searchMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MessageServiceImpl msi = new MessageServiceImpl();
		Page page = new Page();
		String pagenum = request.getParameter("pageNum");
		int curpage = Integer.parseInt(pagenum);
		page.setCurPage(curpage);
		String key = "";
		String username = "";
		int theid = 0;
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
		}
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		if (request.getParameter("theid") != null) {
			String i = request.getParameter("theid");
			theid = Integer.parseInt(i);
		}
		MessageSearch ms = new MessageSearch(key, username, theid);
		Page page2 = msi.getMsg(ms, page);
		// System.out.println(page2.getData());
		Gson gson = new GsonBuilder().setDateFormat("MM-dd HH:mm:ss").create();
		String json = gson.toJson(page2);
		response.getWriter().print(json);
	}
	private void wonderful(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MessageServiceImpl msi = new MessageServiceImpl();
		String id = request.getParameter("msgid");
		int msgid = Integer.parseInt(id);
		int result = msi.wonderforMsg(msgid);
		String json = "";
		if (result == 1) {
			json = "{\"success\":\"false\"}";
			response.getWriter().print(json);
		} else if (result == -1) {
			json = "{\"success\":\"failed\"}";
			response.getWriter().print(json);
		}
	}
	private void toTop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MessageServiceImpl msi = new MessageServiceImpl();
		String id = request.getParameter("msgid");
		int msgid = Integer.parseInt(id);
		int result = msi.topMsg(msgid);
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
