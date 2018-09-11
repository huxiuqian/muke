package com.hpe.muke.service.impl;

import com.hpe.muke.dao.CountDao;
import com.hpe.muke.dao.impl.CountDaoImpl;
import com.hpe.muke.po.CountInfo;
import com.hpe.muke.service.CountService;

public class CountServiceImpl implements CountService{
	CountDao cs = new CountDaoImpl();
	@Override
	public void updateCount(CountInfo ci) {
		cs.updateCount(ci);
	}
	@Override
	public CountInfo getAccessCount(int msgid) {
		// TODO 自动生成的方法存根
		return cs.getAccessCount(msgid);
	}

}
