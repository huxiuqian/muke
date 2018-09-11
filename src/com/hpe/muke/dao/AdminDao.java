package com.hpe.muke.dao;

import com.hpe.muke.po.Admin;

public interface AdminDao {
	Admin adminLogin(String name,String pwd);
	void updateAdminPwd(Admin admin);
}
