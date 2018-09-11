package com.hpe.muke.service;

import com.hpe.muke.po.User;
import com.hpe.muke.util.Page;

public interface UserService {
	User userLogin(String username,String password);
	User selectUserByName(String username);
	void addUser(User user);
	void updateUser(User user);
	void updateUserPwd(User user);
	Page searchByName(String username, Page page);
	int deleteUser(int userid);
	int restoreUser(int userid);
}
