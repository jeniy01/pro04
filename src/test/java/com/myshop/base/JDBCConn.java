package com.myshop.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCConn {
	private static final Logger logger = LoggerFactory.getLogger(JDBCConn.class);
	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testJDBC() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3308/myshop?serverTimezone=Asia/Seoul","root","1234");
			if(conn!=null) {
				logger.info("마리아 DB 연결 성공");
			} else {
				logger.info("마리아 DB 연결 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
