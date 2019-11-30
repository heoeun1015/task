package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * db.propertice파일의 내용으로 DB정보를 설정하기
 * @author pc09
 *
 */
public class DButil2 {

	static Properties prop; // properties객체 변수 선언
	
	static { 
			
		prop = new Properties(); // 객체생성
		
		File file = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis); // properties객체안에 있는 값들을 가지고옴
			
			Class.forName(prop.getProperty("driver")); // "driver"-> db.properties객체에 있는 key값
		
		
		}catch(IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패");
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
			
			}catch(SQLException e) {
				
				System.out.println("DB연결실패");
			e.printStackTrace();
			
			return null;
			}
		
		}
	
	
}
