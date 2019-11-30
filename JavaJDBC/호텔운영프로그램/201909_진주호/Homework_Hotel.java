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

import kr.or.ddit.util.DBUtil3;

public class Homework_Hotel {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Scanner s;
	private Map<String, Reserve> hotelReserve;

	public Homework_Hotel() {
		s = new Scanner(System.in);
		hotelReserve = new HashMap<String, Reserve>();

	}

	public static void main(String[] args) {
		new Homework_Hotel().hotelReserveStart();
	}

	// 메뉴 보여주기
	public void displayMenu() {

		System.out.println();
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.print(" 1. 체크인");
		System.out.print(" 2. 체크아웃");
		System.out.print(" 3. 객실상태");
		System.out.print(" 4. 업무종료");
		System.out.println();
		System.out.println("입력>>");
	}

	// 프로그램을 시작하는 메서드
	public void hotelReserveStart() {

		System.out.println("****************************");
		System.out.println("\r 호텔문 열었습니다");
		System.out.println("****************************");

		while (true) {

			displayMenu();// 메뉴출력

			int num = s.nextInt(); // 메뉴번호입력

			switch (num) {

			case 1:
				cin(); // 체크인
				break;

			case 2:
				cout(); // 체크아웃
				break;

			case 3:
				state(); // 객실상태
				break;

			case 4:
				System.out.println("업무를 종료합니다...");
				return;

			default:
				System.out.println("잘못 입력햇습니다. 다시입력하세요");

			}

		}

	}

	private void state() {

		Set<String> keySet = hotelReserve.keySet();
		System.out.println();
		System.out.println("****************************");
		System.out.println("방번호             이름	            ");
		System.out.println("****************************");

		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from  hotel_mng ";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				String room = rs.getString("room_num");
				String name = rs.getString("guest_name");

				System.out.println(room + "     " + name + "   ");
			}

			System.out.println("****************************");
			System.out.println("출력 작업 끝 !!");
		} catch (SQLException e) {
			System.out.println("자료 출력 실패 ^^");
			System.out.println("다시시도해봐");
			e.printStackTrace();
		}

	}

	private void cout() { // 체크아웃 --> 정보삭제하기

		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호입력>>");
		String room = s.next();

		try {
			conn = DBUtil3.getConnection();
			String sql = " delete from hotel_mng where room_num = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, room);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println();
				System.out.println(room + "은 체크아웃 되었습니다.");
			} else {
				System.out.println(room + "은 예약된 방번호가 아닙니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

	private void cin() { // 체크인

		boolean chk = false;
		String room;

		System.out.println("어느방에 체크인 하시겟습니까?	");
		System.out.print("방번호입력>>	");
		room = s.next();

		// 이미 예약된 방인지 검사

		if (hotelReserve.get(room) != null) {
			System.out.println(room + " 번 방은 이미 예약된 방입니다.");
			return;
		}
		System.out.println();
		System.out.println("누구를 체크인 하시겟습니까?>>");
		String name = s.next();

		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into hotel_mng(room_num ,guest_name)" + " values(?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, room);
			pstmt.setString(2, name);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				hotelReserve.put(room, new Reserve(room, name));
				System.out.println(name + "씨 \t" + room + "번 방 예약 완료 되었습니다.");
				// System.out.println(name+"님의 "+room+"번 방 예약이 완료 되었습니다.");
			} else {
				System.out.println(room + "번 방 예약 실패 ! ");
				System.out.println("다시 시도해주세요 ^^");
			}

		} catch (SQLException e) {
			System.out.println(room + "번 방 예약 실패 ! ");
			System.out.println("다시 시도해주세요 ^^");
			e.printStackTrace();
		}

	}

	class Reserve { // 예약Vo

		private String room; // 방번호
		private String name; // 이름

		public Reserve(String room, String name) {
			super();
			this.room = room;
			this.name = name;
		}

		public String getRoom() {
			return room;
		}

		public void setRoom(String room) {
			this.room = room;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}
}