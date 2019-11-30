package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;


public class HotelTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println("*******************************************\r\n" + "어떤 업무를 하시겠습니까?\r\n"
				+ "1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n" + "*******************************************\r\n" + "메뉴선택 =>");
	}
	
		
	public void bookStart() {
		System.out.println("**************************\r\n" + "호텔 문을 열었습니다.\r\n" + "**************************\r\n"
				+ "\r\n");
		while (true) {
			displayMenu();
			int menu = Integer.parseInt(scan.nextLine());
			switch (menu) {
			case 1:
				checkIn();
				break;
			case 2:
				checkout();
				break;
			case 3:
				status();
				break;
			case 4:
				end();
				System.out.println("작업을 마칩니다.");
				return;
			default:
				bookStart();
				break;
			}
		}
	}

	//종료
	public void end() {
		System.out.println(" 호텔 문을 닫았습니다.\r\n" + "***************************");
	}
	
	//체크아웃
	private void checkout() {
	
		System.out.println();
		System.out.println(" * 어느방을 체크아웃 하시겠습니까? 방번호 입력 =>\r\n" + "*******************************************");
		String room_n = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from hotel_mng where room_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_n);

			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(room_n + "호 체크아웃 완료");
			}else {
				System.out.println("투숙객이 존재하지 않습니다.");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}

	//객실상태
public void status() {
		
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println(" 객실\t이름");
		System.out.println("-----------------------------");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql ="select * from hotel_mng";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String room_n = rs.getString("room_num");
				String guest = rs.getString("guest_name");
				
				System.out.println(room_n + "\t" + guest);
			}
			System.out.println("-----------------------------");
			System.out.println("출력작업 끝");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			disConnect();
		}
}

	//객실예약하는 메서드
		private void checkIn() {
			boolean chk= false;
			
			String room_n;
			
			do {
				System.out.println();
				System.out.println("객실번호를 입력하세요.");
				System.out.print("객실번호 >>");
				room_n = scan.nextLine();
				chk = getRoom(room_n);
				if(chk) {
					System.out.println(room_n + "호는 이미 투숙객이 존재합니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(chk);//false일때 종료
			
			System.out.print("이름 >>");
			String guest = scan.nextLine();

			
			try {
				//conn = DBUtil.getConnection();
				//conn = DBUtil2.getConnection();
				conn = DBUtil3.getConnection();
				
				String sql = "insert into hotel_mng (room_num, guest_name) values(?,?) ";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, room_n);
				pstmt.setString(2, guest);
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt>0){
					System.out.println("체크인 되었습니다.");
				}else {
					System.out.println("체크인 작업 실패");
				}
			}catch(SQLException e) {
				System.out.println("체크인 작업 실패");
				e.printStackTrace();
			}finally {
				disConnect();
				
			}

		}
	
	//자원 반납
	private void disConnect() {
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		
	}
	
	//객실번호를 이용해서 중복여부체크
	private boolean getRoom(String room_n) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from hotel_mng where room_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_n);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt"); 
			}
			if(cnt>0) {
				chk = true;//중복값이 있음
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return chk;
	}
	

	public static void main(String[] args) {
		new HotelTest().bookStart();
	}


}
