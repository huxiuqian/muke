package com.hpe.muke.service.impl;

import com.hpe.muke.dao.MessageDao;
import com.hpe.muke.dao.ReplyDao;
import com.hpe.muke.dao.impl.MessageDaoImpl;
import com.hpe.muke.dao.impl.ReplyDaoImpl;
import com.hpe.muke.po.Message;
import com.hpe.muke.po.MessageCriteria;
import com.hpe.muke.po.MessageSearch;
import com.hpe.muke.po.Reply;
import com.hpe.muke.service.MessageService;
import com.hpe.muke.util.Page;

public class MessageServiceImpl implements MessageService{
	MessageDao messageDao = new MessageDaoImpl();
	ReplyDao replyDao = new ReplyDaoImpl();
	@Override
	public Page queryNew(Page page) {
		// TODO 自动生成的方法存根
		return messageDao.queryNew(page);
	}

	@Override
	public Page queryHot(Page page) {
		// TODO 自动生成的方法存根
		return messageDao.queryHot(page);
	}

	@Override
	public Page queryTheme(Page page) {
		// TODO 自动生成的方法存根
		return messageDao.queryTheme(page);
	}

	@Override
	public Page queryNewById(int userid, Page page) {
		// TODO 自动生成的方法存根
		return messageDao.queryNewById(userid, page);
	}

	@Override
	public void addMessage(Message message) {
		// TODO 自动生成的方法存根
		messageDao.addMessage(message);
	}

	@Override
	public long queryMsgCountByDate(String startDate, String endDate) {
		// TODO 自动生成的方法存根
		return messageDao.queryCountByDate(startDate, endDate);
	}

	@Override
	public long queryReplyCountByDate(String startDate, String endDate) {
		// TODO 自动生成的方法存根
		return replyDao.queryCountByDate(startDate, endDate);
	}

	@Override
	public int deleteMsg(int msgid) {
		int state = -1;
		int result = messageDao.updateState(msgid, state);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public int restoreMsg(int msgid) {
		int state = 0;
		int result = messageDao.updateState(msgid, state);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Page getMsg(MessageSearch messageSearch, Page page) {
		return messageDao.search(messageSearch, page);
	}

	@Override
	public int replyMsg(Reply reply) {
		ReplyDao rdi = new ReplyDaoImpl();
		int result = rdi.replyMsg(reply);
		if (result != -1) {
			return 1;// 添加成功
		} else {
			return -1;// 添加失败
		}
	}

	@Override
	public MessageCriteria getMsgById(int msgid) {
		// TODO 自动生成的方法存根
		return messageDao.getMsgById(msgid);
	}

	@Override
	public int wonderforMsg(int msgid) {
		int state = 1;
		int result = messageDao.updateState(msgid, state);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public int topMsg(int msgid) {
		int state = 2;
		int result = messageDao.updateState(msgid, state);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}

}
