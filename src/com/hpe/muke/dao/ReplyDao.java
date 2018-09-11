package com.hpe.muke.dao;

import com.hpe.muke.po.Reply;
import com.hpe.muke.util.Page;

public interface ReplyDao {
	long queryCountByDate(String startDate, String endDate);
	int replyMsg(Reply reply);
	Page queryBymsgid(int msgid, Page page);
	void delReply(int replyid);
}
