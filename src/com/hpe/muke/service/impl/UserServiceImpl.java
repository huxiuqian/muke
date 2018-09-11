package com.hpe.muke.service.impl;

import com.hpe.muke.dao.UserDao;
import com.hpe.muke.dao.impl.UserDaoImpl;
import com.hpe.muke.po.User;
import com.hpe.muke.service.UserService;
import com.hpe.muke.util.Page;

public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl();
	@Override
	public User userLogin(String username, String password) {
		return userDao.userLogin(username, password);
	}
	@Override
	public User selectUserByName(String username) {
		return userDao.selectUserByName(username);
	}
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}
	@Override
	public void updateUser(User user) {
		// TODO 自动生成的方法存根
		userDao.updateUser(user);
	}
	@Override
	public void updateUserPwd(User user) {
		// TODO 自动生成的方法存根
		userDao.updateUserPwd(user);
	}
	@Override
	public Page searchByName(String username, Page page) {
		// TODO 自动生成的方法存根
		return userDao.searchByName(username, page);
	}
	@Override
	public int deleteUser(int userid) {
		int state = -1;
		int result = userDao.updateState(userid, state);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}
	@Override
	public int restoreUser(int userid) {
		int state = 0;
		int result = userDao.updateState(userid, state);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
