package kr.or.ddit.util;
/*
 * db.properties파일의 내용으로 DB정보를 설정하기
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil2 {
	static Properties prop; //properties객체변수 선언
	
	static {
		prop = new Properties(); //객체 생성
		
		File file = new File("res/db.properties");
		
		try {
			FileInputStream fis = new FileInputStream(file);
			prop.load(fis);//file의 키와밸류값 읽어서 뽑아옴
			
			Class.forName(prop.getProperty("driver"));//value값 가져옴
		}catch(IOException e) {
			System.out.println("파일이 없거나 입출력 오류입니다.");
		}catch(ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!");
		}
	}
	
	
	public static Connection getConnection() {//util같은 기능은 보통 static
		try {
			//properties파일을 통해 환경설정을 읽도록 함
			return DriverManager.getConnection(prop.getProperty("url"), 
					prop.getProperty("user"), 
					prop.getProperty("pass"));
		}catch(SQLException e) {
			System.out.println("DB연결 실패");
			e.printStackTrace();
			return null;
		}
	}
	
}
