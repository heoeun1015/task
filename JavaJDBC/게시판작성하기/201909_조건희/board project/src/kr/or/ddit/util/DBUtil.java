package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * JDBC 드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 클래스
 *  
 */
public class DBUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			String url = "jdbc:oracle:thin:@localhost:1523:orcl";
			String userId = "PC02";
			String password = "java";
			return DriverManager.getConnection(url, userId, password);
		} catch(SQLException e) {
			System.out.println("DB연결 실패!!!");
			e.printStackTrace();
			return null;
		}
	}
}
