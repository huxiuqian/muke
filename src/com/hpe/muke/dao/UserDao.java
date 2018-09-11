package com.hpe.muke.dao;

import com.hpe.muke.po.User;
import com.hpe.muke.util.Page;

public interface UserDao {
	User userLogin(String username,String password);
	User selectUserByName(String username);
	void addUser(User user);
	void updateUser(User user);
	void updateUserPwd(User user);
	Page searchByName(String username, Page page);
	int updateState(int userid, int state);
}
