package com.hpe.muke.po;

import java.sql.Timestamp;

public class MessageCriteria {
	private String realname;
	private int msgid;
	private int userid;
	private String msgtopic;
	private String msgcontents;
	private Timestamp msgtime;
	private String msgip;
	private int theid;
	private int state;
	private int accessCount;
	private int replyCount;
	private String thename;
	private int count;
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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
	public String getMsgtopic() {
		return msgtopic;
	}
	public void setMsgtopic(String msgtopic) {
		this.msgtopic = msgtopic;
	}
	public String getMsgcontents() {
		return msgcontents;
	}
	public void setMsgcontents(String msgcontents) {
		this.msgcontents = msgcontents;
	}
	public Timestamp getMsgtime() {
		return msgtime;
	}
	public void setMsgtime(Timestamp msgtime) {
		this.msgtime = msgtime;
	}
	public String getMsgip() {
		return msgip;
	}
	public void setMsgip(String msgip) {
		this.msgip = msgip;
	}
	public int getTheid() {
		return theid;
	}
	public void setTheid(int theid) {
		this.theid = theid;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getAccessCount() {
		return accessCount;
	}
	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public String getThename() {
		return thename;
	}
	public void setThename(String thename) {
		this.thename = thename;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "MessageCriteria [realname=" + realname + ", msgid=" + msgid + ", userid=" + userid + ", msgtopic="
				+ msgtopic + ", msgcontents=" + msgcontents + ", msgtime=" + msgtime + ", msgip=" + msgip + ", theid="
				+ theid + ", state=" + state + ", accessCount=" + accessCount + ", replyCount=" + replyCount
				+ ", thename=" + thename + ", count=" + count + "]";
	}

}
