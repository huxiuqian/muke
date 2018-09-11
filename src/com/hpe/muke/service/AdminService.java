package com.hpe.muke.service;

import com.hpe.muke.po.Admin;

public interface AdminService {
	Admin adminLogin(String name,String pwd);
	void updateAdminPwd(Admin admin);
}
