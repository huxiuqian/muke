package com.hpe.muke.dao;

import com.hpe.muke.po.Message;
import com.hpe.muke.po.MessageCriteria;
import com.hpe.muke.po.MessageSearch;
import com.hpe.muke.util.Page;

public interface MessageDao {
	Page queryNewById(int userid,Page page);
	Page queryNew(Page page);
	Page queryHot(Page page);
	Page queryTheme(Page page);
	void addMessage(Message message);
	long queryCountByDate(String startDate, String endDate);
	int updateState(int msgid, int state);
	Page search(MessageSearch messageSearch, Page page);
	MessageCriteria getMsgById(int msgid);
}
