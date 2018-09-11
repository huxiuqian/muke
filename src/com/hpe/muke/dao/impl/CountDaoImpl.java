package com.hpe.muke.dao.impl;

import com.hpe.muke.dao.CountDao;
import com.hpe.muke.po.CountInfo;
import com.hpe.muke.po.MessageCriteria;
import com.hpe.muke.util.DBUtil;

public class CountDaoImpl implements CountDao{
	DBUtil dbUtil = new DBUtil();
	@Override
	public void updateCount(CountInfo ci) {
		String sql = "update count set accessCount=? where msgid=?";
		Object[] params = { ci.getAccessCount(), ci.getMsgid() };
		try {
			dbUtil.execute(sql, params);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	@Override
	public CountInfo getAccessCount(int msgid) {
		String sql = "select * from count where msgid=?";
		Object[] params = { msgid };
		CountInfo result = null;
		try {
			result = (CountInfo) dbUtil.getObject(CountInfo.class, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
