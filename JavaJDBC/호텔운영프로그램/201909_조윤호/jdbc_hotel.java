package kr.or.ddit.basic;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;


public class jdbc_hotel {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("***********************************************");
		System.out.println("메뉴를 선택하세요.");
		System.out.print(" 1. 체크인");
		System.out.print(" 2. 체크아웃");
		System.out.print(" 3. 객실상태");
		System.out.println(" 4. 업무종료");
		System.out.println("***********************************************");
		System.out.print(" 메뉴선택 >> ");		
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		System.out.println("===============================================");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("===============================================");
		
		while(true){
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			
			switch(menuNum){
				case 1 : checkin();		// 체크인
					break;
				case 2 : checkout();		// 체크아웃
					break;
				case 3 : room();		// 객실상태
					break;
				case 4 :
					System.out.println("호텔 문을 닫았습니다.");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}
	

	
	private void room() {
		System.out.println();
		String num = "";
		boolean chk = true;
		
		do {
			System.out.println("방번호를 입력하세요 >> ");
			num = scan.next();
			
			chk = gethotel(num);
			if(chk == false) {
				System.out.println("등록된 방이 없습니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(chk == false);
		
		System.out.println();
		System.out.println("===================================================");
		System.out.println(" 방번호\t투숙객");
		System.out.println("===================================================");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_hotel where hotel_no = " + num;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				num = rs.getString("hotel_no");
				String name = rs.getString("hotel_name");
				
				System.out.println(num + "\t" 
								   + name);
			}
			System.out.println("===================================================");
			System.out.println("출력 작업 끝!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}

	private void checkout() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String no = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_hotel where hotel_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(no + "체크아웃 성공...");
			}else {
				System.out.println(no + "체크아웃 실패!!!");
			}
		}catch(SQLException e) {
			System.out.println(no + "체크아웃 실패!!!");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	

	private void checkin() {
		System.out.println();
		String num = "";
				
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 >> ");
		num = scan.next();
		
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		
		try {
			//conn = DBUtil.getConnection();
			conn = DBUtil2.getConnection();
			String sql = "insert into jdbc_hotel" 
					   + "(hotel_no, hotel_name)"
					   + " values (?,?)";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,num);
			pstmt.setString(2,name);
			
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println("체크인 작업 성공");
			}else {
				System.out.println("체크인 작업 실패!!!");
			}
			
		}catch(SQLException e) {
			System.out.println("체크인 작업 실패!!!");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	
	
	private void disConnect() {
			//  사용했던 자원 반납
			if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
			if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
			if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	
	//게시판 검색
	private boolean gethotel(String num) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from jdbc_hotel "
					   + "where hotel_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				chk = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return chk;
	}

	public static void main(String[] args) {
		jdbc_hotel memObj = new jdbc_hotel();
		memObj.start();
	}

}


