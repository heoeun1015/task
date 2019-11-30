package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC 드라이버를 로딩하고 Connection 객체를 생성하는 메서드로 구성된 클래스
public class DBUtil {
	
	static {	// DBUtil의 시작과 동시에 실행된다. DBUtil 을 시작할 때 메모리에 올린다. static 초기화 블럭.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {
			System.out.println("▷ 드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"pc16",
					"java");
		}catch(SQLException e) {
			System.out.println("▷ DB 연결 실패");
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
}
