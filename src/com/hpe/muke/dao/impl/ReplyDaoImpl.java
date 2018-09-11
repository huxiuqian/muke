package com.hpe.muke.dao.impl;

import com.hpe.muke.dao.ReplyDao;
import com.hpe.muke.po.Count;
import com.hpe.muke.po.Reply;
import com.hpe.muke.po.ReplyInfo;
import com.hpe.muke.util.DBUtil;
import com.hpe.muke.util.Page;

public class ReplyDaoImpl implements ReplyDao{
	DBUtil dbUtil = new DBUtil();
	@Override
	public long queryCountByDate(String startDate, String endDate) {
		String sql = "select COUNT(replyid) replynums from reply where date_format(replytime,'%Y-%m-%d')>=? and date_format(replytime,'%Y-%m-%d')<=?";
		Object[] params = { startDate, endDate };
		Count result = null;
		try {
			result = (Count) dbUtil.getObject(Count.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result != null) {
			long resultnum = result.getReplynums();

			return resultnum;
		} else {
			return 0;
		}
	}
	@Override
	public int replyMsg(Reply reply) {
		String sql = "insert into reply(msgid,userid,replycontents,replytime,replyip) VALUES(?,?,?,?,?)";
		Object[] params = { reply.getMsgid(), reply.getUserid(), reply.getReplycontents(), reply.getReplytime(),
				reply.getReplyip() };
		int resultUser = -1;
		try {
			resultUser = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUser;
	}
	@Override
	public Page queryBymsgid(int msgid, Page page) {
		String sql = "SELECT r.replyid, r.msgid,r.userid,r.replycontents,r.replytime,r.replyip,u.realname,"
				+ "u.sex,u.city FROM reply r,message m,USER u WHERE m.msgid = r.msgid AND u.userid = r.userid AND m.msgid = ?";
		Object[] params = { msgid };
		Page page1 = dbUtil.getQueryPage(ReplyInfo.class, sql, params, page);
		return page1;
	}
	@Override
	public void delReply(int replyid) {
		// TODO 自动生成的方法存根
		String sql = "delete from reply where replyid = ?";
		Object[] params = { replyid };
		try {
			dbUtil.execute(dbUtil.getConnection(),sql, params);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
