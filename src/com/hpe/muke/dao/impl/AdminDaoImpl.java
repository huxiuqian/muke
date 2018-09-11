package com.hpe.muke.dao.impl;

import com.hpe.muke.dao.AdminDao;
import com.hpe.muke.po.Admin;

import com.hpe.muke.util.DBUtil;

public class AdminDaoImpl implements AdminDao{
	DBUtil dbUtil = new DBUtil();
	Admin admin= null;
	@Override
	public Admin adminLogin(String name, String pwd) {
		String sql = "select * from admin where name=? and pwd=? ";
		Object[] params = { name, pwd};
		try {
			admin = (Admin) dbUtil.getObject(Admin.class,sql, params);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return admin;
	}
	
	@Override
	public void updateAdminPwd(Admin admin) {
		String sql = "update admin set pwd=? where id=?";
		Object[] params = { admin.getPwd(), admin.getId() };
		try {
			dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
