package com.hpe.muke.dao;

import com.hpe.muke.po.CountInfo;

public interface CountDao {
	CountInfo getAccessCount(int msgid);
	void updateCount(CountInfo ci);
}
