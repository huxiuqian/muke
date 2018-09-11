package com.hpe.muke.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.hpe.muke.util.DBDataSource;

public class TestSource {
	public static void main(String[] args) throws Exception {
		Connection cnn = DBDataSource.getConnectionC3P0();
		try {
			if(!cnn.isClosed()){
				System.out.println("连接成功");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
}
