package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class Hotel_jdbc {
	private Scanner scan;

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public Hotel_jdbc() {
		scan = new Scanner(System.in);
	}

	//메뉴 출력
	public void menu() {
		System.out.println();
		System.out.println("**********************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인  2.체크아웃  3. 객실상태  4.업무종료");
		System.out.println("**********************************");
		System.out.print("메뉴선택 => ");	
	}

	//프로그램 시작
	public void hotelStart() {
		System.out.println("*****************");
		System.out.println(" 호텔 문을 열었습니다. ");
		System.out.println("*****************");

		while(true) {
			menu();	//메뉴 출력

			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			switch(menuNum) {
			case 1: checkIn();break;	//체크인
			case 2: checkOut();break;	//체크아웃
			case 3: checkRoom();break;	//객실상태
			case 4: System.out.println("*****************\r"+"호텔 문을 열었습니다.\r"+"*****************");break;
			//업무종료
			} // switch문
		} // while 문
	}

	/**
	 * 체크인
	 */
	private void checkIn() {
		String room = "";
		boolean chk= false;
		while(!chk) {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			room = scan.next();
			chk = getRoom(room);
			if(chk) {
				System.out.println(room + "방은 이미 투숙 중입니다.");
				System.out.println("다른방을 선택해주세요.");
			}
			break;
		}
		System.out.print("투숙객 이름 >> ");
		String memName = scan.next();

		try {
			//conn = DBUtil.getConnection();	// 기본
			//conn = DBUtil2.getConnection();   // Properties를 이용한 커넥션
			conn = DBUtil3.getConnection();		// ResourceBundle를 이용한 커넥션	
			String sql = "insert into hotel_mng (room_num, guest_name ) values(?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room);
			pstmt.setString(2, memName);


			int cnt = pstmt.executeUpdate();

			if(cnt > 0) {
				System.out.println(room + "방 예약 완료");
			}else {
				System.out.println(room + "방 예약 실패!!");
			}
		} catch (SQLException e) {
			System.out.println(room + "방 예약 작업 실패!!");
			e.printStackTrace();
		}finally {
			disConnect();

		}

	}

	/**
	 * 투숙가능한 방인지 확인하는 메서드
	 * @param room
	 * @return
	 */
	private boolean getRoom(String room) {
		boolean chk = false;
		try {	
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from hotel_mng where room_num = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				chk = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}

		return false;
	}

	/**
	 * 예외처리하는 메서드
	 */
	private void disConnect() {
		if(pstmt !=null) try {rs.close();}catch(SQLException e) {}
		if(rs!=null) try {rs.close();}catch(SQLException e) {}
		if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
		if(conn!=null) try {conn.close();}catch(SQLException e) {}

	}

	/**
	 * 체크아웃
	 */
	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String room = scan.next();

		try {
			conn = DBUtil.getConnection();
			String sql = "delete from hotel_mng where room_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room);

			int cnt = pstmt.executeUpdate();
			System.out.println("성공여부" + cnt);
			if(cnt > 0) {
				System.out.println(room + "방 퇴실 성공");
			}else {
				System.out.println(room + "방은 빈방입니다!!!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}

	}
	/**
	 * 객실상태 확인
	 */
	private void checkRoom() {
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("방번호\t이  름");
		System.out.println("---------------------------------");

		try {
			conn = DBUtil.getConnection();
			String sql = "select * from hotel_mng";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				String room = rs.getString("room_num");
				String memName = rs.getString("guest_name");

				System.out.println(room + "\t" + memName);
			}
			System.out.println("---------------------------------");
			System.out.println("출력작업 끝...");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	public static void main(String[] args) {
		new Hotel_jdbc().hotelStart();
	}

}