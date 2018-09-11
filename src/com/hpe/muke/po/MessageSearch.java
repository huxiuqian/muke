package com.hpe.muke.po;

public class MessageSearch {
	private int userid;

	private String key = "";// 标题关键字
	private String username = "";// 用户姓名
	private int theid = 0;// 主题id

	public MessageSearch(String key, String username, int theid) {
		super();
		this.key = key;
		this.username = username;
		this.theid = theid;
	}

	public MessageSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTheid() {
		return theid;
	}

	public void setTheid(int theid) {
		this.theid = theid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
}
