package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class T07_Hotel {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner sc;
	ArrayList<Integer> al = new ArrayList<Integer>();
	public T07_Hotel() {
		sc = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new T07_Hotel().start();
	}

	public void start() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();

		while(true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");
			int choice = Integer.parseInt(sc.nextLine());
			if ( choice == 1 ) {
				checkin();
			} else if ( choice == 2 ) {
				checkout();
			} else if ( choice == 3 ) {
				roomstatus();
			} else if ( choice == 4 ) {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}
		}
	}

	public void roomstatus() {
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from hotel_mng";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int roomnum = rs.getInt("room_num");
				String Name = rs.getString("guest_name");
				
				System.out.println("방번호 : " + roomnum + "\t투숙객 : " + Name);
			}
			
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	public void checkin() {
		
		
		try {
			int room = 0;
			conn = DBUtil.getConnection(); //접속정보

			stmt = conn.createStatement();
			String sql = "select * from hotel_mng"; //실행할 SQL문 최대값을 구하기 위해 반드시 order by를 입력한다.
			rs = stmt.executeQuery(sql); //SQL문이 select일 경우.

			while(rs.next()) {
				al.add(rs.getInt("room_num")); // lprod_gu값이 겹치지 않는 구문을 만들기 위해 lprod_gu에 있는 내용들을 arrayList로 넣는 코드.
			}
			
			String gu = null;
			while(true) {
				int count = 0;
				System.out.println("어느방에 체크인 하시겠습니까?");
				System.out.print("방번호 입력 => ");
				room = Integer.parseInt(sc.nextLine());
				for(int i = 0; i<al.size(); i++) {
					int igu = al.get(i);
					if (room == igu) {
						System.out.println("이미 있는 예약된 방입니다. 다시 입력하세요.");
						count++;
					}
				}
				if (count == 0) {
					break;
				}
			}
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 => ");
			String name = sc.nextLine();
			System.out.println("체크인 되었습니다");
			
			//conn = DBUtil.getConnection();
			conn = DBUtil3.getConnection();

			sql = "insert into hotel_mng (room_num, guest_name) values (?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, room);
			pstmt.setString(2, name);
			
			int cnt = pstmt.executeUpdate();

			if(cnt > 0) {
				System.out.println(room + "번 방이 예약되었습니다.");
			} else {
				System.out.println(room + "번 방 예약을 실패하였습니다.");
			}

		} catch(SQLException e) {
			System.out.println("회원 추가 작업 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	public void checkout() {

		System.out.println();
		System.out.print("삭제할 방번호를 입력하세요. ");
		int roomnum = Integer.parseInt(sc.nextLine());
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from hotel_mng where room_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomnum);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0){
				System.out.println(roomnum + "회원 삭제 성공");
			} else {
				System.out.println(roomnum + "회원 삭제 실패");
			}
		} catch(SQLException e) {
			System.out.println(roomnum + "회원 삭제 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void disConnect() {
		// 종료(사용했던 자원을 모두 반납한다.)
		//scan.close();
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
		if(rs != null) try {rs.close();} catch(SQLException e) {}
	}
}