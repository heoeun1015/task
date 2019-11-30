package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class Hotel_DB {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner sc = new Scanner(System.in);

	
	public Hotel_DB () { 
		sc = new Scanner(System.in); 
	}
	 

	// 메뉴를 출력하는 메서드
	public void menu() {
		System.out.println("---------------------------------------------\r" 
				+ "\t   호텔 문을 열었습니다.\r"
				+ "---------------------------------------------");
		System.out.println("\t   어떤 업무를 하시겠습니까 ? \r " 
				+ "1. 체크인 2. 체크아웃 3. 객실상태 4. 업무종료");
		System.out.println("---------------------------------------------");
		System.out.print("메뉴 선택 > ");
	}

	// 프로그램을 시작하는 메서드
	public void start() {
		int menu;
		do {
			menu();
			menu = sc.nextInt();
			switch (menu) {
				case 1:
					CheckIn(); // 체크인
					break;
		
				case 2:
					CheckOut(); // 체크아웃
					break;
		
				case 3:
					RoomStatus();// 객실상태
					break;
		
				case 4:
					System.out.println("영업을 종료합니다.");// 업무종료
					return;
		
				default:
					System.out.println("잘못 입력했습니다. 다시 입력하세요.");
				}
			}while(menu != 4);
	}

	
	public static void main(String[] args) { 
		new Hotel_DB().start(); 
	}
	 

	private void CheckIn() {

		boolean chk = false;

		String room_num;

		do {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까 ?");
			System.out.print("방번호 입력 >");
			room_num = sc.next();
			chk = getCheck(room_num);
			if (chk) {
				System.out.println(room_num + "호는 이미 체크인된 방입니다.");
				return;
			}
		} while (chk);

		System.out.println();
		System.out.print("이름 입력 > ");
		String guest_name = sc.next();

		try {
			conn = DBUtil.getConnection();

			String sql = "insert into hotel_mng values (?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_num);
			pstmt.setString(2, guest_name);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("체크인 되었습니다.");
			} else {
				System.out.println("체크인 실패 ㅠㅠ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void disConnect() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
		}
	}
	
	private boolean getCheck(String room_num) {
		boolean chk = false;

		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from hotel_mng where room_num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_num);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return chk;
	}



	private void CheckOut() {
		System.out.println();
		System.out.println("체크아웃 방번호를 입력하세요.");
		System.out.print("방번호 > ");
		String room_num = sc.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete hotel_mng where room_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_num);
			
			int cnt = pstmt.executeUpdate();

			if(cnt > 0) {
				System.out.println("체크아웃 성공 !!");
			}else {
				System.out.println("해당 방은 빈방입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}

	private void RoomStatus() {
		System.out.println();
		System.out.println("---------------- 체크인 상태 ----------------");
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from hotel_mng";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String room_num = rs.getString("room_num");
				String guest_name = rs.getString("guest_name");
				
				System.out.println("방번호 : " + room_num + "\t\t" + "투숙객 이름 : " + guest_name);
			}
			System.out.println("---------------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
}