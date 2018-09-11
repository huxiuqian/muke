package com.hpe.muke.po;

public class CountInfo {
	private int msgid;
	private int accessCount;
	private int replyCount;
	public int getMsgid() {
		return msgid;
	}
	public void setMsgid(int msgid) {
		this.msgid = msgid;
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
	@Override
	public String toString() {
		return "CountInfo [msgid=" + msgid + ", accessCount=" + accessCount + ", replyCount=" + replyCount + "]";
	}
	
}
