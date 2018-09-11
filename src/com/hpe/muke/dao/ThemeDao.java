package com.hpe.muke.dao;

import java.util.List;

import com.hpe.muke.po.Theme;
import com.hpe.muke.util.Page;

public interface ThemeDao {
	List<Theme> selectTheme();
	int add(Theme theme);
	int updateState(int theid, int state);
	Page query(String key, Page page);

}
