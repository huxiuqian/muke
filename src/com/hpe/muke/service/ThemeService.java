package com.hpe.muke.service;

import java.util.List;

import com.hpe.muke.po.Theme;
import com.hpe.muke.util.Page;

public interface ThemeService {
	List<Theme> selectTheme();
	int add(Theme theme);
	int deletetheme(int theid);
	int restoretheme(int theid);
	Page query(String key, Page page);
}
