package com.hpe.muke.dao.impl;

import java.util.ArrayList;
import java.util.List;


import com.hpe.muke.dao.ThemeDao;
import com.hpe.muke.po.Theme;
import com.hpe.muke.util.DBUtil;
import com.hpe.muke.util.Page;

public class ThemeDaoImpl implements ThemeDao{
	private DBUtil dbUtil = new DBUtil();
	@Override
	public List<Theme> selectTheme() {
		String sql = "select * from theme";
		List list = null;
		Object[] params = {};
		try {
			list = dbUtil.getQueryList(Theme.class, sql, params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int add(Theme theme) {
		String sql = "insert into theme(thename,count,state) value(?,?,?)";
		Object[] params = { theme.getThename(),theme.getCount(),theme.getState() };
		int resultUser = 0;
		try {
			resultUser = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUser;
	}
	@Override
	public int updateState(int theid, int state) {
		String sql = "update theme set state=? where theid=?";
		Object[] params = { state, theid };
		int result = 0;

		try {
			result = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.toString();
		}
		return result;
	}
	@Override
	public Page query(String key, Page page) {
		StringBuilder sql = new StringBuilder("select * from theme");
		List<Object> parmas = new ArrayList<Object>();
		System.out.println(key);
		if (key != null && !key.trim().isEmpty()) {
			sql.append(" where thename like ?");
			parmas.add("%" + key + "%");
		}
		Page page1 = dbUtil.getQueryPage(Theme.class, sql.toString(), parmas.toArray(), page);
		return page1;
	}

}
