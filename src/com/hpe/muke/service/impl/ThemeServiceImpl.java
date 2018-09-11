package com.hpe.muke.service.impl;

import java.util.List;

import com.hpe.muke.dao.ThemeDao;
import com.hpe.muke.dao.impl.ThemeDaoImpl;
import com.hpe.muke.po.Theme;
import com.hpe.muke.service.ThemeService;
import com.hpe.muke.util.Page;

public class ThemeServiceImpl implements ThemeService{
	ThemeDao themeDao = new ThemeDaoImpl();
	@Override
	public List<Theme> selectTheme() {
		// TODO 自动生成的方法存根
		return themeDao.selectTheme();
	}
	@Override
	public int add(Theme theme) {
		int result = themeDao.add(theme);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}
	@Override
	public int deletetheme(int theid) {
		int state = -1;
		int result = themeDao.updateState(theid, state);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}
	@Override
	public int restoretheme(int theid) {
		int state = 0;
		int result = themeDao.updateState(theid, state);
		if (result != 0) {
			return 1;
		} else {
			return -1;
		}
	}
	@Override
	public Page query(String key, Page page) {
		// TODO 自动生成的方法存根
		return themeDao.query(key, page);
	}

}
