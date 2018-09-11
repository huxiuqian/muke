package com.hpe.muke.service;

import com.hpe.muke.po.Message;
import com.hpe.muke.po.MessageCriteria;
import com.hpe.muke.po.MessageSearch;
import com.hpe.muke.po.Reply;
import com.hpe.muke.util.Page;

public interface MessageService {
	Page queryNewById(int userid,Page page);
	Page queryNew(Page page);
	Page queryHot(Page page);
	Page queryTheme(Page page);
	void addMessage(Message message);
	long queryMsgCountByDate(String startDate, String endDate);
	long queryReplyCountByDate(String startDate, String endDate);
	int deleteMsg(int msgid);
	int restoreMsg(int msgid);
	int wonderforMsg(int msgid);
	int topMsg(int msgid);
	int replyMsg(Reply reply);
	Page getMsg(MessageSearch messageSearch, Page page);
	MessageCriteria getMsgById(int msgid);
}
