package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import kr.or.ddit.util.DBUtil3;

/*문제)

호텔 운영을 관리하는 프로그램 작성.(Map이용)
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

create table hotel (
    room_num number not null,  -- 방번호
    mem_name varchar2(10) not null -- 투숙객 이름
);
*/
public class JDBC_Hotel {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 

	public void displayMenu() {
		System.out.println();
		System.out.println("*******************************************");
		System.out.println(" 어떤 업무를 하시겠습니까?");
		System.out.print(" 1.체크인");
		System.out.print(" 2.체크아웃");
		System.out.print(" 3.객실상태");
		System.out.println(" 4.업무종료");
		System.out.println("*******************************************");
		System.out.print(" 메뉴선택 => ");
	}

	public void HotelCheckStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");

		while (true) {

			displayMenu();

			int menuNum = Integer.parseInt(scan.nextLine());

			switch (menuNum) {
			case 1:
				checkIn(); 
				break;
			case 2:
				chectOut();
				break;
			case 3:
				displayAll();
				break;
			case 4:
				System.out.println();
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}
	
	private void displayAll() {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println(" 호수\t이름");
		System.out.println("-----------------------------");
		
		try {
			conn = DBUtil3.getConenction();
			
			String sql = "select * from hotel";
			
			stmt= conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String roomNum = rs.getString("room_num");
				String memName = rs.getString("mem_name");
				
				System.out.println(roomNum + "\t" 
									+ memName
									);
			}
			System.out.println("-----------------------------");
			System.out.println("출력작업 끝");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void checkIn() {

		boolean chk = false;
		
		String roomNum;
		
		do {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력  >> ");
			roomNum = scan.next();
			chk = getMember(roomNum); // 기존에 있는 회원인지 체크
			if (chk) {
				System.out.println(roomNum + "호에는 이미 사람이 있습니다.");
				System.out.println("다시 입력하세요.");
			}
		} while (chk);
		
		System.out.print("이름 입력 >> ");
		String memName = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		
		try {
			conn = DBUtil3.getConenction();
			
			String sql = "insert into hotel "
						+ "(room_num, mem_name) "
						+ " values (?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			pstmt.setString(2, memName);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(roomNum + " 체크인 성공");
			} else {
				System.out.println(roomNum + " 체크인 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	public void chectOut() {
		
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 >> ");
		String roomNum = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		
		try {
			conn = DBUtil3.getConenction();
			String sql = "delete from hotel where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(roomNum + "체크아웃 성공");
			} else {
				System.out.println(roomNum + "체크아웃 실패!!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	public boolean getMember(String memId) {

		boolean chk = false;
		
		try {
			conn = DBUtil3.getConenction();
			String sql = "select count(*) cnt from hotel "
						+ "where room_num = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				chk = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return chk;
	}

	public static void main(String[] args) {
		new JDBC_Hotel().HotelCheckStart();
		JDBC_Hotel hotel = new JDBC_Hotel();
		hotel.HotelCheckStart();
	}
}
