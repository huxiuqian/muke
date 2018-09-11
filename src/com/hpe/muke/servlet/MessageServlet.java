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
import com.hpe.muke.dao.ReplyDao;
import com.hpe.muke.dao.impl.ReplyDaoImpl;
import com.hpe.muke.po.CountInfo;
import com.hpe.muke.po.MessageCriteria;
import com.hpe.muke.po.User;
import com.hpe.muke.service.CountService;
import com.hpe.muke.service.MessageService;
import com.hpe.muke.service.impl.CountServiceImpl;
import com.hpe.muke.service.impl.MessageServiceImpl;
import com.hpe.muke.util.Page;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MessageService messageService = new MessageServiceImpl();
    CountService countService = new CountServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
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
		if("new".equals(action)){
			topNew(request, response);
		}else if("hot".equals(action)){
			topHot(request, response);
		}else if("theme".equals(action)){
			topTheme(request, response);
		}else if("getMsg".equals(action)){
			getMsg(request, response);
		}else if("getReply".equals(action)){
			getReply(request, response);
		}else if("del".equals(action)){
			del(request, response);
		}
	}

	private void topNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		Page page = new Page();
		if(pageNum == null || pageNum.equals("")){
			pageNum = "1";
		}
		page.setCurPage(Integer.parseInt(pageNum));
		Page resPage = messageService.queryNew(page);
		
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();
		String dateJson = gson.toJson(resPage);
		//System.out.println(dateJson);
		response.getWriter().print("{\"data\":"+dateJson+"}");
	}
	private void topHot(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		Page page = new Page();
		if(pageNum == null || pageNum.equals("")){
			pageNum = "1";
		}
		page.setCurPage(Integer.parseInt(pageNum));
		Page resPage = messageService.queryHot(page);
		
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();
		String dateJson = gson.toJson(resPage);
		//System.out.println(dateJson);
		response.getWriter().print("{\"data\":"+dateJson+"}");
	}
	private void topTheme(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		Page page = new Page();
		if(pageNum == null || pageNum.equals("")){
			pageNum = "1";
		}
		page.setCurPage(Integer.parseInt(pageNum));
		Page resPage = messageService.queryTheme(page);
		
		Gson gson = new GsonBuilder().setDateFormat("M/d").create();
		String dateJson = gson.toJson(resPage);
		//System.out.println(dateJson);
		// \"res\": 1, 
		response.getWriter().print("{\"data\":"+dateJson+"}");
	}
	private void getMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String iString = request.getParameter("msgid");
		int msgid = Integer.parseInt(iString);
		MessageCriteria mc = messageService.getMsgById(msgid);
		CountInfo ci = countService.getAccessCount(msgid);
		int ac = ci.getAccessCount()+1;
		ci.setAccessCount(ac);
		countService.updateCount(ci);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(mc);
		// System.out.println(json);
		response.getWriter().print(json);
	}
	private void getReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String pagen = request.getParameter("pagenum");
		String id = request.getParameter("msgid");
		int msgid = Integer.parseInt(id);
		int pagenum = Integer.parseInt(pagen);
		Page page = new Page();
		page.setCurPage(pagenum);
		ReplyDao rdi = new ReplyDaoImpl();
		Page page2 = rdi.queryBymsgid(msgid, page);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String json = gson.toJson(page2);
		// System.out.println(json);
		response.getWriter().print(json);
	}
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		String rid = request.getParameter("replyid");
		int replyid = Integer.parseInt(rid);
		String uid = request.getParameter("userid");
		int userid = Integer.parseInt(uid);
		if(user != null){
			if(userid == user.getUserid()){
				ReplyDao rd = new ReplyDaoImpl();
				rd.delReply(replyid);
				response.getWriter().print("{\"res\": 1}");
			}else{
				response.getWriter().print("{\"res\": -1}");
			}			
		}else{
			response.getWriter().print("{\"res\": -2}");
		}
	}
}
