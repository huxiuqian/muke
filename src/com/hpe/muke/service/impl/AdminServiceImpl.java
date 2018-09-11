package com.hpe.muke.service.impl;

import com.hpe.muke.dao.AdminDao;
import com.hpe.muke.dao.impl.AdminDaoImpl;
import com.hpe.muke.po.Admin;
import com.hpe.muke.service.AdminService;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao = new AdminDaoImpl();
	@Override
	public Admin adminLogin(String name, String pwd) {
		// TODO 自动生成的方法存根
		return adminDao.adminLogin(name, pwd);
	}
	@Override
	public void updateAdminPwd(Admin admin) {
		// TODO 自动生成的方法存根
		adminDao.updateAdminPwd(admin);
	}

}
