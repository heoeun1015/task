package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil3;

public class HotelManage {
	private static Scanner s;

	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	public HotelManage() {
		s = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		new HotelManage().start();
	}

	private static void start() {
		System.out.println("**************************\r\n"
							+ "호텔 문을 열었습니다.\r\n" 
							+ "**************************");
		
		
		while(true) {
			System.out.println("*******************************************\r\n" 
								+ "어떤 업무를 하시겠습니까?\r\n"
								+ "1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n"
								+ "*******************************************");
			
			System.out.print("메뉴 선택 =>");
			int selectMenu = Integer.parseInt(s.nextLine());
			
			switch(selectMenu) {
			case 1: 
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomStatus();
				break;
			case 4:
				System.out.println("\r\n**************************\r\n" 
									+ "호텔 문을 닫았습니다.\r\n" 
									+ "**************************");
				return;
			}
			
		}
		
	}
	private static void checkIn() {
		int roomNum;
		while(true) {
			System.out.print("어느방에 체크인 하시겠습니까?\r\n" 
					+ "방번호 입력 =>");
			roomNum = Integer.parseInt(s.nextLine());
			
			if(!isExist(roomNum)) {
				break;
			} else if(roomNum > 0 && roomNum < 10000) {
				System.out.println(roomNum + "은 이미 체크인 되어있습니다.");
			} else {
				System.out.println("입력값이 올바르지 않습니다.");
			}
			
		}
		
		System.out.print("누구를 체크인 하시겠습니까?\r\n"
							+ "이름 입력 =>");
		String name = s.next();
		
		s.nextLine(); 	// 입력버퍼 비우기
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "INSERT INTO hotel_mng (room_num, guest_name)"
					+ " values (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			pstmt.setString(2, name);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크인이 완료되었습니다..");
			} else {
				System.out.println("체크인에 실패하였습니다.");
			}
		} catch (SQLException e) {
			System.out.println("체크인에 실패하였습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private static void checkOut() {
		roomStatus();
		
		int roomNum;
		while(true) {
			System.out.print("어느방을 체크아웃 하시겠습니까?\r\n" 
					+ "방번호 입력 =>");
			roomNum = Integer.parseInt(s.nextLine());
			
			if(isExist(roomNum)) {
				break;
			} else if(roomNum > 0 && roomNum < 10000) {
				System.out.println(roomNum + "은 체크인한 사람이 없습니다.");
			} else {
				System.out.println("입력값이 올바르지 않습니다.");
			}
			
		}
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "DELETE FROM hotel_mng WHERE room_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println("체크아웃 되었습니다.");
			} else {
				System.out.println("체크아웃 실패");
			}
		} catch (SQLException e) {
			System.out.println("체크아웃 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private static void roomStatus() {
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		System.out.println(" 방 번호\t\t투숙객");
		System.out.println("-------------------------------------------------------------------");
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM hotel_mng ORDER BY room_num DESC";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int roomNum = rs.getInt("room_num");
				String guest = rs.getString("guest_name");
				
				System.out.println(roomNum + "\t\t" + guest);
			}
			System.out.println("-------------------------------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private static boolean isExist(int rNum) {
		boolean chk = false;
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT COUNT(*) cnt from hotel_mng where room_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNum);
			
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
		} finally {
			disConnect();
		}
		return chk;
	}
	
	private static void disConnect() {
		if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
		if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
		if(conn != null) try { conn.close(); } catch(SQLException e) {}
		if(rs != null) try { rs.close(); } catch(SQLException e) {}
	}
}

class Hotel{
	private int room;
	private String name;
	
	public Hotel(int room, String name) {
		super();
		this.room = room;
		this.name = name;
	}
	
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}