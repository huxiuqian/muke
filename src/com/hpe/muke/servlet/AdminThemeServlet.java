package com.hpe.muke.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hpe.muke.po.Theme;
import com.hpe.muke.service.impl.ThemeServiceImpl;
import com.hpe.muke.util.Page;

/**
 * Servlet implementation class AdminThemeServlet
 */
@WebServlet("/AdminThemeServlet")
public class AdminThemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminThemeServlet() {
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
		if ("search".equals(action)) {
			searchTheme(request, response);
		} else if ("add".equals(action)) {
			add(request, response);
		}  else if ("deletetheme".equals(action)) {
			deleteTheme(request, response);
		} else if ("restoretheme".equals(action)) {
			restoreTheme(request, response);
		}
	}
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Theme theme = new Theme();
		String name = request.getParameter("name");
		ThemeServiceImpl tsi = new ThemeServiceImpl();
		theme.setThename(name);
		theme.setCount(0);
		theme.setState(0);
		int result = tsi.add(theme);
		String json = "";
		if (result == 1) {
			json = "{\"res\":\"1\"}";
		} else {
			json = "{\"res\":\"2\"}";
		}
		response.getWriter().print(json);
	}

	private void deleteTheme(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ThemeServiceImpl tsi = new ThemeServiceImpl();
		String id = request.getParameter("theid");
		int theid = Integer.parseInt(id);
		int result = tsi.deletetheme(theid);
		String json = "";
		if (result == 1) {
			json = "{\"success\":\"false\"}";
			response.getWriter().print(json);
		} else if (result == -1) {
			json = "{\"success\":\"failed\"}";
			response.getWriter().print(json);
		}
	}

	private void restoreTheme(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ThemeServiceImpl tsi = new ThemeServiceImpl();
		String id = request.getParameter("theid");
		int theid = Integer.parseInt(id);
		int result = tsi.restoretheme(theid);
		String json = "";
		if (result == 1) {
			json = "{\"success\":\"false\"}";
			response.getWriter().print(json);
		} else if (result == -1) {
			json = "{\"success\":\"failed\"}";
			response.getWriter().print(json);
		}
	}

	private void searchTheme(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ThemeServiceImpl tsi = new ThemeServiceImpl();
		String key = request.getParameter("key");
		System.out.println(key);
		Page page = new Page();
		String curpage = request.getParameter("pagenum");
		int curPage = Integer.parseInt(curpage);
		page.setCurPage(curPage);
		Page page2 = tsi.query(key, page);
		Gson gson = new Gson();
		String json = gson.toJson(page2);
		response.getWriter().print(json);

	}
}
