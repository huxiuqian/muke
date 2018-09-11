package com.hpe.muke.service;

import com.hpe.muke.po.CountInfo;

public interface CountService {
	CountInfo getAccessCount(int msgid);
	void updateCount(CountInfo ci);
}
