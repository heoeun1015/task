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

/*문제)

호텔 운영을 관리하는 프로그램 작성.(DB 이용)
 - 키값은 방번호 
 
실행 예시)

**************************
호텔 문을 열었습니다.
**************************

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 101 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 홍길동 <-- 입력
체크인 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 성춘향 <-- 입력
체크인 되었습니다

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향
방번호 : 101, 투숙객 : 홍길동

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
체크아웃 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 허준 <-- 입력
102방에는 이미 사람이 있습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
101방에는 체크인한 사람이 없습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 4 <-- 입력

**************************
호텔 문을 닫았습니다.
**************************


 호텔운영 프로그램 테이블 생성 스크립트 
create table hotel_mng (
    room_num number not null,  -- 방번호
    guest_name varchar2(10) not null -- 투숙객 이름
);
*/
public class T07_H호텔운영프로그램 {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	// 프로그램을 시작하는 메서드
	public void HotelStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************\n");

		while (true) {

			displayMenu(); // 메뉴 출력

			int menuNum = scan.nextInt(); // 메뉴 번호 입력

			switch (menuNum) {
			case 1:
				checkin(); // 체크인
				break;
			case 2:
				checkout(); // 체크아웃
				break;
			case 3:
				room(); // 객실상태
				break;
			case 4:
				System.out.println();
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}

	// 메뉴를 출력하는 메서드
	public void displayMenu() {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?.");
		System.out.print("1.체크인  ");
		System.out.print("2.체크아웃  ");
		System.out.print("3.객실상태  ");
		System.out.println("4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 => ");
	}

	// 체크인을 하는 메서드
	// (이미 체크인된 방에는 체크인 할 수 없다.)
	private void checkin() {

		System.out.println();
		String roomNum = "";
		boolean chk = true;

		do {
			System.out.println();
			System.out.println("어느 방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			roomNum = scan.next();

			chk = getCheckIn(roomNum);

			if (chk == true) {
				System.out.println("이미 체크인된 방입니다.");
			}
		} while (chk == true);

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next(); // 공백 기준

		try {

			conn = DBUtil.getConnection();
//			conn = DBUtil2.getConnection();
//			conn = DBUtil3.getConnection();

			String sql = "INSERT INTO hotel_mng" + "(ROOM_NUM, GUEST_NAME) " + " VALUES (?, ?) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			pstmt.setString(2, name);

			int cnt = pstmt.executeUpdate(); // executeUpdate는 int값

			if (cnt > 0) {
				System.out.println("체크인되었습니다.");
			} else {
				System.out.println("체크인되지 않았습니다.");
			}

		} catch (SQLException e) {
			System.out.println("체크인되지 않았습니다.");
			e.printStackTrace();
		} finally {
			disConnect(); // 자원반납 메서드
		}

		System.out.println(roomNum + "방에 체크인 되었습니다");
	}

	// 체크인된 방인지 확인하는 메서드
	private boolean getCheckIn(String roomNum) {
		boolean chk = false;

		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from hotel_mng " + "where ROOM_NUM = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);

			rs = pstmt.executeQuery(); // executeQuery는 resultset을 리턴

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

	// 체크아웃을 하는 메서드
	private void checkout() {

		boolean chk = true;

		System.out.println();
		System.out.println("어느 방에 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String roomNum = scan.next();
		do {

			chk = getCheckIn(roomNum);

			if (chk == false) {
				System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			}
		} while (chk == true);

		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM hotel_mng WHERE ROOM_NUM=? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(roomNum + "방이 체크아웃 되었습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}

	// 객실상태를 확인하는 메서드
	private void room() {

		System.out.println();
		System.out.println("-----------------------");
		System.out.println("방번호\t투숙객");

		try {
			conn = DBUtil.getConnection();

			String sql = "select * from hotel_mng";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String roomNum = rs.getString("ROOM_NUM");
				String name = rs.getString("GUEST_NAME");

				System.out.println(roomNum + "\t" + name);
			}
			System.out.println("-----------------------");
			System.out.println("출력작업 끝...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}

	private void disConnect() {
		// 사용했던 자원 반납
		if (rs != null)try {rs.close();} catch (SQLException e) {}
		if (pstmt != null)try {pstmt.close();} catch (SQLException e) {}
		if (stmt != null)try {stmt.close();} catch (SQLException e) {}
		if (conn != null)try {conn.close();} catch (SQLException e) {}
	}

	public static void main(String[] args) {
		new T07_H호텔운영프로그램().HotelStart();
	}
}
