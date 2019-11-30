package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil2;

public class HotelReservation {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner s = new Scanner(System.in);
	
	// 메뉴 출력
	public void displayMenu() {
		System.out.println("*******************************************");
		System.out.println("******************호텔 open*****************");
		System.out.println("*******************************************");
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");		
	}
	//시작 메소드
	public void start() {
		int choice;
		
		do {
			displayMenu();
			choice = s.nextInt();
			switch(choice) {
				case 1: //체크인
					checkIn();
					break;
				case 2: // 체크 아웃
					checkOut();
					break;
				case 3: // 객실상태
					displayRoom();
					break;
				case 4:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
 			}
		}while(choice !=5);
	}
	private void checkOut() {
		System.out.println();
		System.out.println("체크아웃할 회원 아이디를 입력하세요.");
		String room = s.next();
		
		try {
			conn = DBUtil2.getConnection();
			String sql = "delete hotel_mng where room_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,room);
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(" 체크아웃 하였습니다.");
			}else {
				System.out.println(room+ "에는 이미 사람이 있습니다.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("체크아웃에 실패하였습니다.");
		}finally {
			disConnect();
		}
		
	}
	private void displayRoom() {
		System.out.println();
		System.out.println("============================");
		System.out.println("객실번호\t손님");
		System.out.println("============================");
		
		try {
			conn = DBUtil2.getConnection();
			String sql = "select * from hotel_mng";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String room = rs.getString("room_num");
				String guestName = rs.getString("guest_name");
				System.out.println(room + "\t" + guestName);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	private void checkIn() {
		boolean chk = false;
		
		String room;
		do {
			System.out.println();
			System.out.println("체크인 회원 정보를 입력하세요.");
			System.out.println("방 번호를 입력하세요");
			room = s.next();
			
			chk = getRoom(room);
			if(chk) {
				System.out.println(room+ "에는 이미 사람이 있습니다.");
			}
			
		}while(chk);
		try {
			conn = DBUtil2.getConnection();
			System.out.println("회원 ID >>");
			String guestName = s.next();
			String sql = "insert into hotel_mng " + " values (?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room);
			pstmt.setString(2, guestName);
			
			int cnt = pstmt.executeUpdate();
			if(cnt >0) {
				System.out.println("예약 성공");
			}else {
				System.out.println(room + "추가 실패");
			}
		}catch(SQLException e) {
			System.out.println(room + "추가 실패");
			e.printStackTrace();
		}finally {
			disConnect();
		}
			
	}
	
	//회원 정보를 있는 지 체크하는 메소드
	private boolean getRoom(String room) {
		boolean chk = false;
		try {
			conn = DBUtil2.getConnection();
			String sql = "select count(*) cnt from hotel_mng"+
						" where room_num = ? ";
						
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt >0) {
				chk = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return chk;
	}
	private void disConnect() {
		if(rs!=null) try {rs.close();} catch(SQLException e) {}
		if(conn!=null) try {conn.close();} catch(SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
	}
	public static void main(String[] args) {
		HotelReservation hr = new HotelReservation();
		hr.start();
	}
}
