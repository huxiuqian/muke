package com.hpe.muke.po;

import java.sql.Timestamp;

public class ReplyInfo {
	private int replyid;
	private int msgid;
	private int userid;
	private String replycontents;
	private Timestamp replytime;
	private String replyip;
	private String realname;
	private String sex;
	private String city;
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	public int getMsgid() {
		return msgid;
	}
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getReplycontents() {
		return replycontents;
	}
	public void setReplycontents(String replycontents) {
		this.replycontents = replycontents;
	}
	public Timestamp getReplytime() {
		return replytime;
	}
	public void setReplytime(Timestamp replytime) {
		this.replytime = replytime;
	}
	public String getReplyip() {
		return replyip;
	}
	public void setReplyip(String replyip) {
		this.replyip = replyip;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "ReplyInfo [replyid=" + replyid + ", msgid=" + msgid + ", userid=" + userid + ", replycontents="
				+ replycontents + ", replytime=" + replytime + ", replyip=" + replyip + ", realname=" + realname
				+ ", sex=" + sex + ", city=" + city + "]";
	}

}
