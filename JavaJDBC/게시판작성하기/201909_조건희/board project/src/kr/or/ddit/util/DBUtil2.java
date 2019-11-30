package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * db.properties파일의 내용으로 DB정보를 설정하기
 */

public class DBUtil2 {
	static Properties prop;
		
	// static 초기화블럭  DBUtil을 사용할떄 로딩될떄 실행된다.
	// 한번 로딩 후에는 계속 가져다 쓴다.
	static {
		prop = new Properties(); 
		
		File file = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			
			// key와 value로 뽑아온다.
			prop.load(fis);
			
			Class.forName(prop.getProperty("driver"));
		} catch(IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
		} catch(SQLException e) {
			System.out.println("DB연결 실패!!!");
			e.printStackTrace();
			return null;
		}
	}
}
