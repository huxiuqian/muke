package com.hpe.muke.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.hpe.muke.dao.MessageDao;
import com.hpe.muke.po.Count;
import com.hpe.muke.po.Message;
import com.hpe.muke.po.MessageCriteria;
import com.hpe.muke.po.MessageSearch;
import com.hpe.muke.util.DBUtil;
import com.hpe.muke.util.Page;

public class MessageDaoImpl implements MessageDao{
	DBUtil dbUtil = new DBUtil();
	@Override
	public Page queryNew(Page page) {
		String sql = "select realname,message.msgid,message.userid,msgtopic,msgcontents,msgtime,msgip,message.theid,message.state,accessCount,replyCount,thename,count from message inner join `user` on message.userid=`user`.userid inner join count on message.msgid=count.msgid inner join theme on message.theid=theme.theid where message.state > -1 order by msgtime desc";
		Object[] params = { };
		try {
			page = dbUtil.getQueryPage(MessageCriteria.class, sql, params, page);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return page;
	}
	@Override
	public Page queryHot(Page page) {
		String sql = "select realname,message.msgid,message.userid,msgtopic,msgcontents,msgtime,msgip,message.theid,message.state,accessCount,replyCount,thename,count from message inner join `user` on message.userid=`user`.userid inner join count on message.msgid=count.msgid inner join theme on message.theid=theme.theid where message.state > -1 order by count.replyCount desc";
		Object[] params = { };
		try {
			page = dbUtil.getQueryPage(MessageCriteria.class, sql, params, page);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return page;
	}

	@Override
	public Page queryTheme(Page page) {
		String sql = "select realname,message.msgid,message.userid,msgtopic,msgcontents,msgtime,msgip,message.theid,message.state,accessCount,replyCount,thename,count from message inner join `user` on message.userid=`user`.userid inner join count on message.msgid=count.msgid inner join theme on message.theid=theme.theid where message.state > -1 order by theme.count desc,count.replyCount desc";
		Object[] params = { };
		try {
			page = dbUtil.getQueryPage(MessageCriteria.class, sql, params, page);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return page;
	}

	@Override
	public Page queryNewById(int userid, Page page) {
		String sql = "select realname,message.msgid,message.userid,msgtopic,msgcontents,msgtime,msgip,message.theid,message.state,accessCount,replyCount,thename,count from message inner join `user` on message.userid=`user`.userid inner join count on message.msgid=count.msgid inner join theme on message.theid=theme.theid where message.userid=? and message.state > -1 order by msgtime desc";
		Object[] params = { userid };
		try {
			page = dbUtil.getQueryPage(MessageCriteria.class, sql, params, page);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		return page;
	}

	@Override
	public void addMessage(Message message) {
		// TODO 自动生成的方法存根
		String sql = "insert into message (userid,msgtopic,msgcontents,msgtime,msgip,theid,state) values (?,?,?,?,?,?,?)";
		Object[] params = { message.getUserid(), message.getMsgtopic(), message.getMsgcontents(), message.getMsgtime(), message.getMsgip(), message.getTheid(), message.getState()};
		try {
			dbUtil.execute(sql, params, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public long queryCountByDate(String startDate, String endDate) {
		String sql = "select COUNT(msgid) msgnums from message where date_format(msgtime,'%Y-%m-%d')>=? and date_format(msgtime,'%Y-%m-%d')<=?";
		Object[] params = { startDate, endDate };
		Count result = null;
		try {
			result = (Count) dbUtil.getObject(Count.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result != null) {
			long resultnum = result.getMsgnums();
			return resultnum;
		} else {
			return 0;
		}
	}

	@Override
	public int updateState(int msgid, int state) {
		String sql = "update message set state=? where msgid=?";
		Object[] params = { state, msgid };
		int result = 0;
		try {
			result = dbUtil.execute(sql, params);
		} catch (Exception e) {
			e.toString();
		}
		return result;
	}

	@Override
	public Page search(MessageSearch messageSearch, Page page) {
		StringBuilder sql = new StringBuilder(
				"SELECT m.msgid,m.msgtopic,m.msgtime,m.state,u.realname FROM message m ,count c ,user u  WHERE m.msgid = c.msgid and m.userid=u.userid ");
		List<Object> parmas = new ArrayList<Object>();
		String key = messageSearch.getKey();
		if (key != null && !key.trim().isEmpty()) {
			sql.append(" and msgtopic like ?");
			parmas.add("%" + key + "%");
		}
		String username = messageSearch.getUsername();
		if (username != null && !username.trim().isEmpty()) {
			sql.append(" and u.username like ?");
			parmas.add("%" + username + "%");
		}
		int theid = messageSearch.getTheid();
		if (theid > 0) {
			sql.append(" and theid = ?");
			parmas.add(theid);
		}
		sql.append(" order by msgtime desc");
		Page page1 = dbUtil.getQueryPage(MessageCriteria.class, sql.toString(), parmas.toArray(), page);
		return page1;
	}

	@Override
	public MessageCriteria getMsgById(int msgid) {
		String sql = "SELECT c.accessCount, m.msgcontents, m.msgid, m.msgip, m.msgtime, m.msgtopic, u.realname, c.replyCount, m.state, m.theid,"
				+ " t.thename, m.userid FROM message m, count c, user u ,theme t"
				+ " WHERE m.msgid = c.msgid and u.userid=m.userid and t.theid=m.theid AND m.msgid=? ";
		Object[] params = { msgid };
		MessageCriteria result = null;
		try {
			result = (MessageCriteria) dbUtil.getObject(MessageCriteria.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
