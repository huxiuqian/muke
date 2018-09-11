package com.hpe.muke.po;

import java.sql.Timestamp;

public class User {
	private int userid;				//用户ID
	private String username;		//用户名
	private String password;		//用户密码
	private String realname;		//真实姓名
	private String sex;				//性别
	private String hobbys;			//兴趣
	private String birthday;		//生日
	private String city;			//地址
	private String email;			//邮箱
	private String qq;				//QQ
	private Timestamp createtime;	//创建时间   -timestamp格式
	private int state;				//状态   0：正常   -1：删除  

	public User() {
		
	}
	
	public User(String username, String password, String realname, String sex, String hobbys, String birthday,
			String city, String email, String qq, Timestamp createtime, int state) {
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.sex = sex;
		this.hobbys = hobbys;
		this.birthday = birthday;
		this.city = city;
		this.email = email;
		this.qq = qq;
		this.createtime = createtime;
		this.state = state;
	}

	public User(String realname, String sex, String hobbys, String birthday, String city, String email, String qq) {
		super();
		this.realname = realname;
		this.sex = sex;
		this.hobbys = hobbys;
		this.birthday = birthday;
		this.city = city;
		this.email = email;
		this.qq = qq;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHobbys() {
		return hobbys;
	}
	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Timestamp getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", realname=" + realname
				+ ", sex=" + sex + ", hobbys=" + hobbys + ", birthday=" + birthday + ", city=" + city + ", email="
				+ email + ", qq=" + qq + ", createtime=" + createtime + ", state=" + state + "]";
	}

}
