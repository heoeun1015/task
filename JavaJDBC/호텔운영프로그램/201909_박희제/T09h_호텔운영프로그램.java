package kr.or.ddit.basic;
/*
문제)

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class T09h_호텔운영프로그램 {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan;

	
	public T09h_호텔운영프로그램() {
		scan = new Scanner(System.in);
	}
	
	// 프로그램을 시작하는 메서드
	public void start(){
		System.out.println("**************************"); 
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");

		
		while(true){
			System.out.println();
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			System.out.println();
			
			switch(menuNum){
				case 1 : checkIn();		// 체크인
					break;
				case 2 : checkOut();	// 체크아웃
					break;
				case 3 : roomState();	// 객실상태
					break;
				case 4 : 				// 업무종료
					System.out.println("**************************"); 
					System.out.println("호텔 문을 닫았습니다.");
					System.out.println("**************************");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}


	// 체크인
	private void checkIn() {
		boolean chk = true;
		String num = "";
		
		do {
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			num = scan.next();
			
			chk = getRoom(num);
			if(chk) {
				System.out.println(num + "번 방은 이미 사람이 있습니다.");
			}
		
		} while (chk);
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();
		
		try {
			conn = DBUtil.getConnection();
		
			String sql = "INSERT INTO hotel_mng (room_num, guest_name) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.setString(2, name);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(num + "번 방 체크인 성공");
			} else {
				System.out.println(num + "번 방 체크인 실패!");
			}
			
		} catch (SQLException e) {
			System.out.println(num + "번 방 체크인 실패!");
			e.printStackTrace();
		} finally {
			disConnect();
		}


	}
	
	
	// 체크아웃
	private void checkOut() {
		boolean chk = true;
		String num = "";

		do {
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			num = scan.next();
			
			chk = getRoom(num);
			if(!chk) {
				System.out.println(num + "번 방에는 체크인 한 사람이 없습니다.");
			}
		
		} while (!chk);
		
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM hotel_mng WHERE room_num = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(num + "번 방 체크아웃 성공..");
			} else {
				System.out.println(num + "번 방 체크아웃 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	
	// 객실상태
	private void roomState() {
		System.out.println();
		System.out.println("--------------");
		System.out.println(" 방번호\t투숙객");
		System.out.println("--------------");

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT * FROM hotel_mng";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String boardNo = rs.getString("room_num");
				String boardTitle = rs.getString("guest_name");

				System.out.println(boardNo + "\t" + boardTitle);
			}
			System.out.println("--------------");
			System.out.println("출력작업 끝...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	
	//체크인 된 방인지 확인하는 메서드
	private boolean getRoom(String roomNum) {
		boolean chk = false;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM hotel_mng WHERE room_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);

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
	
	
	// 자원반납
	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException e){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException e){}
		if(conn!=null)try{ conn.close(); }catch(SQLException e){}
	}
	
	
	public static void main(String[] args) {
		new T09h_호텔운영프로그램().start();
	}
}
