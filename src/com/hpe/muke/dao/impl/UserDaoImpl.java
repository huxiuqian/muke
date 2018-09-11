package com.hpe.muke.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hpe.muke.dao.UserDao;
import com.hpe.muke.po.User;
import com.hpe.muke.util.DBUtil;
import com.hpe.muke.util.Page;

public class UserDaoImpl implements UserDao{
	DBUtil dbUtil = new DBUtil();
	User user = null;
	@Override
	public User userLogin(String username, String password) {
		// TODO 自动生成的方法存根
		String sql = "select * from user where username=? and password=? ";
		Object[] params = { username, password};
		try {
			user = (User) dbUtil.getObject(User.class,sql, params);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return user;
	}
	@Override
	public User selectUserByName(String username) {
		String sql = "select * from user where username=?";
		Object[] params = { username };
		User user = null;
		try {
			user = (User) dbUtil.getObject(User.class,sql, params);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return user;
	}
	@Override
	public void addUser(User user) {
		String sql = "insert into user (username,password,realname,sex,hobbys,birthday,city,email,qq,createtime,state) values (?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { user.getUsername(), user.getPassword(), user.getRealname(), user.getSex(), user.getHobbys(), 
				user.getBirthday(), user.getCity(), user.getEmail(), user.getQq(), user.getCreatetime(), user.getState() };
		try {
			dbUtil.execute(sql, params, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateUser(User user) {
		String sql = "update user set realname=?, sex=?, hobbys=?, birthday=?, city=?, email=?, qq=? where userid=?";
		Object[] params = { user.getRealname(), user.getSex(), user.getHobbys(), user.getBirthday(), 
				user.getCity(), user.getEmail(), user.getQq(), user.getUserid()};
		try {
			dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	@Override
	public void updateUserPwd(User user) {
		String sql = "update user set password=? where userid=?";
		Object[] params = { user.getPassword(), user.getUserid()};
		try {
			dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	@Override
	public Page searchByName(String username, Page page) {
		StringBuilder sql = new StringBuilder("select * from user");
		List<Object> parmas = new ArrayList<Object>();
		if (username != null && !username.trim().isEmpty()) {
			sql.append(" where username like ?");
			parmas.add("%" + username + "%");
		}	
		sql.append(" order by createtime desc");
		Page page1 = dbUtil.getQueryPage(User.class, sql.toString(), parmas.toArray(), page);
		return page1;
	}
	@Override
	public int updateState(int userid, int state) {
		String sql = "update user set state=? where userid=?";
		Object[] params = { state, userid };
		int result = 0;
		try {
			result = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.toString();
		}
		return result;
	}

}
