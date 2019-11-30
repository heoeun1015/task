package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil3;

public class Hotel_del_luna {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	public static void main(String[] args) {
		System.out.println("**************************************");
		System.out.println("델루나 호텔에 오신 것을 환영합니다.");
		System.out.println("**************************************");
		Hotel_del_luna hotel = new Hotel_del_luna();
		hotel.open();
	}

	//오픈 메서드
	private void open() {
		int choice;
		
		do {
			displayMenu();
			choice = Integer.parseInt(scan.nextLine());
			
			switch(choice){
				case 1 : //체크인 
					checkIn();
					break;
				case 2 : //체크아웃
					checkOut();
					break;
				case 3 : //객실상태
					status();
					break;
				case 4 : //업무종료
					System.out.println("죄송합니다. 지금은 이용이 불가합니다.");
					System.out.println("다음날 아침 7시 이후에 방문해주십시오.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다.");
			}
		}while(choice != 4);
	}
	
	//객실 현황을 보여주는 메서드
	private void status() {
		System.out.println();
		System.out.println("**************************************");
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from hotel_mng";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				String roomNum = rs.getString("room_num");
				String guestName = rs.getString("guest_name");
				
				System.out.println("방번호 : " + roomNum + " , 투숙객 : " + guestName);
			}
			System.out.println("**************************************");
			System.out.println("예약된 객실 정보를 모두 출력하였습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	//체크아웃 메서드
	private void checkOut() {
		System.out.println();
		String roomNum = "";
		boolean check = true;
		boolean out = true;
		
		do {
			System.out.println();
			System.out.println("어느 방을 체크아웃 하시겠습니까?");
			roomNum = scan.nextLine();
			
			check = getRoom(roomNum);
			
			if(check == false) {
				System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
				System.out.println("다른 방 번호를 입력하시겠습니까?");
				System.out.println("1. 예 2. 아니오(홈으로 돌아갑니다)");
				int choice = Integer.parseInt(scan.nextLine());
				
				switch(choice) {
					case 1 ://예
						break;
					case 2 ://아니오
						out = false;
						check = true;
						break;
				}
			}
		}while(check == false);
		
		if(out == true) {
			try {
				conn = DBUtil3.getConnection();
				String sql = "delete from hotel_mng where room_num = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, roomNum);
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt > 0) {
					System.out.println("체크아웃 되었습니다.");
				}else {
					System.out.println("체크아웃에 실패했습니다.");
				}
			}catch(SQLException e) {
				System.out.println("체크아웃에 실패했습니다.");
				e.printStackTrace();
			}finally {
				disConnect();
			}
		}
	}

	//체크인 메서드
	private void checkIn() {
		boolean chk = false;
		String roomNum;
		
		do {
			System.out.println();
			System.out.println("어느 방에 체크인 하시겠습니까?");
			roomNum = scan.nextLine();
			
			chk = getRoom(roomNum);
			if(chk) {
				System.out.println(roomNum + "방은 이미 이용중인 고객님이 있습니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(chk);
		
		System.out.println("이용하실 고객님 성함을 알려주세요.");
		String name = scan.nextLine();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "insert into hotel_mng (room_num, guest_name) values (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			pstmt.setString(2, name);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크인 되었습니다.");
			}else {
				System.out.println("체크인에 실패했습니다.");
			}
		}catch(SQLException e) {
			System.out.println("체크인에 실패했습니다.");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}

	//자원 반납
	private void disConnect() {
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
	}

	//방이 이미 예약되어 있는지 확인하는 메서드
	private boolean getRoom(String roomNum) {
		boolean check = false;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT COUNT(*) cnt from hotel_mng where room_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
	
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				check = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return check;
	}

	//메뉴를 보여주는 메서드
	private void displayMenu() {
		System.out.println();
	
		System.out.println("**************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인   2. 체크아웃   3. 객실상태   4. 업무종료");
		System.out.println("**************************************");
	}

}
