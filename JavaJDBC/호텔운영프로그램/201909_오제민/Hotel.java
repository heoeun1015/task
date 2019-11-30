package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil3;

public class Hotel {
	private Scanner s = new Scanner(System.in);
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static void main(String[] args) {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		new Hotel().hotelStart();
		System.out.println();
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
	}

	private void hotelStart() {
		while(true) {
			System.out.println();
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴 선택 => ");
			int select = Integer.parseInt(s.nextLine());
			switch(select) {
			case 1 : checkin(); break;
			case 2 : checkout(); break;
			case 3 : roomState(); break;
			case 4 : return;
			default : break;
			}
		}
		
	}
	
	private void checkin() { //체크인 메서드
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 번호 입력 => ");
		int roomNum = Integer.parseInt(s.nextLine());
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String guest = s.nextLine();
		
		conn = DBUtil3.getConnection();
		String sql = "SELECT COUNT(*) FROM hotel_mng WHERE room_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("COUNT(*)");
			}
			if(cnt > 0) {
				System.out.println(roomNum + "번 방에는 이미 사람이 있습니다."); return;
			}
			
			sql = "INSERT INTO hotel_mng VALUES (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			pstmt.setString(2, guest);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println("체크인 되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void checkout() { //체크아웃 메서드
		System.out.println();
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방 번호 입력 => ");
		int roomNum = Integer.parseInt(s.nextLine());
		
		conn = DBUtil3.getConnection();
		String sql = "SELECT COUNT(*) FROM hotel_mng WHERE room_num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("COUNT(*)");
			}
			if(cnt == 0) {
				System.out.println(roomNum + "방에는 체크인한 사람이 없습니다."); return;
			}
			
			sql = "DELETE hotel_mng WHERE room_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println("체크아웃 되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void roomState() { //객실 상태 메서드
		System.out.println();
		conn = DBUtil3.getConnection();
		String sql = "SELECT COUNT(*) FROM hotel_mng";
		
		try {
			pstmt = conn.prepareStatement(sql);
						
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("COUNT(*)");
			}
			if(cnt == 0) {
				System.out.println("현재 객실은 모두 투숙가능합니다."); return;
			}
			
			sql = "SELECT * FROM hotel_mng";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int roomNum = rs.getInt("room_num");
				String guest = rs.getString("guest_name");
				
				System.out.println("방 번호 : " + roomNum + ", 투숙객 : " + guest);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void disConnect() {
		//  사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException e){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
		if(conn!=null)try{ conn.close(); }catch(SQLException e){}
		
	}

}
