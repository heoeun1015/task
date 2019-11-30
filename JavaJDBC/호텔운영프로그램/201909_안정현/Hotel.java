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

public class Hotel {
	private Scanner scan;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	Hotel() {
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new Hotel().display();

		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();

	}

	public void display() {
		while (true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.println("메뉴선택 =>");
			int n = Integer.parseInt(scan.nextLine());
			switch (n) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				 roomState();
				break;
			case 4:
				System.out.println("종료되었습니다.");
				return;
			default:
				System.out.println("다시입력해주세요");
			}

		}
	}

	private void roomState() {
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("호실\t이름");
		System.out.println("-------------------------------------");

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT * FROM hotel_mng";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int roomNum = rs.getInt("room_num");
				String guestName = rs.getString("guest_name");
				System.out.println(roomNum + "\t" + guestName);
			}
			System.out.println("-------------------------------------");
			System.out.println("출력작업 끝");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}

	private void checkOut() {
		System.out.println();
		System.out.println("체크아웃할 호실을 입력하세요 >> ");
		int roomNum = scan.nextInt();
		scan.nextLine();

		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM hotel_mng where room_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(roomNum + "호실 체크아웃 성공");
			} else {
				System.out.println(roomNum + "호실 체크아웃 실패");
			}
		} catch (SQLException e) {
			System.out.println(roomNum + "호실 체크아웃 실패");
		} finally {
			disConnect();
		}
	}

	public void checkIn() {
		boolean chk = false;

		int roomNum;

		do {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.println("방번호 입력 =>");
			roomNum = scan.nextInt();
			chk = getRoom(roomNum);
			if (chk) {
				System.out.println(roomNum + "호실은 이미 예약되었습니다.");
				System.out.println("다시 입력하세요.");
			}
		} while (chk);

		System.out.print("예약자 이름 >>");
		scan.nextLine();
		String guestName = scan.nextLine();

		try {
			conn = DBUtil.getConnection();

			String sql = "insert into hotel_mng (room_num, guest_name) values(?,?) ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, roomNum);
			pstmt.setString(2, guestName);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(roomNum + "호실 예약 성공");
			} else {
				System.out.println(guestName + "호실 예약 성공");
			}
		} catch (SQLException e) {
			System.out.println(roomNum + "호실 예약 실패");
			e.printStackTrace();
		} finally {
			disConnect();

		}

	}

	private boolean getRoom(int roomnum) {
		boolean chk = false;

		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from hotel_mng where room_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomnum);

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

	private void disConnect() {
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
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}

	}
}
