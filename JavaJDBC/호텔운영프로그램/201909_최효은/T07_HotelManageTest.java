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

import kr.or.ddit.util.DBUtil2;

public class T07_HotelManageTest {
	
	private Scanner s;
	private Map<Integer, Hotel> hotelManage;
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public T07_HotelManageTest() {
		s = new Scanner(System.in);
		hotelManage = new HashMap<>();
	}
	
	public static void main(String[] args) {
		new T07_HotelManageTest().mainMenu();
	}
	
	public void displayMenu(){
		
		System.out.println("─────────────────────────────────────");
		System.out.println("1.체크인 / 2.체크아웃 / 3.객실상태 / 4.업무종료");
		System.out.println("─────────────────────────────────────");
		System.out.print("▷ 어떤 업무를 하시겠습니까?: ");
		
	}

	public void mainMenu(){
		
		int menu = 0;
		
		System.out.println("─────────────────────────────────────");
		System.out.println();
		System.out.println("▷ 호텔 문을 열었습니다.");
		System.out.println();
		
		do {
			
			displayMenu();
			
			menu = Integer.parseInt(s.nextLine());
			
			switch(menu) {
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
				System.out.println("─────────────────────────────────────");
				System.out.println();
				System.out.println("▷ 호텔 문을 닫았습니다.");
				System.out.println();
				System.out.println("─────────────────────────────────────");
				break;
			default:
				System.out.println("▷ 다시 선택해주세요.");
				break;
			}
			
		}while(menu != 4);
	}

	public void roomState() {
		
		System.out.println();
		Set<Integer> keySet = hotelManage.keySet();
		Iterator<Integer> its = keySet.iterator();
		int cnt = 0;
		
		System.out.println("\t《 투숙객 리스트 》");
		System.out.println("─────────────────────────────────────");
		System.out.println();
		try {

			conn = DBUtil2.getConnection();

			String sql = "SELECT * FROM hotel_mng";

			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				int roomNum = rs.getInt("room_num");
				String guestName = rs.getString("guest_name");

				System.out.println(roomNum + "\t" + guestName);
			}
			System.out.println("------------------------------------------");
			System.out.println("▶ 출력 작업 끝");

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		System.out.println();
		
		
	}

	public void checkOut() {
		
		System.out.println();
		System.out.print("▷ 어느 방을 체크아웃 하시겠습니까?: ");
		int room_num = Integer.parseInt(s.nextLine());
		System.out.println();
		
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "DELETE FROM hotel_mng WHERE room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, room_num);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println();
				System.out.println("▶  체크아웃 되었습니다.");
			}else {
				System.out.println();
				System.out.println("▶ 체크아웃이 실패하였습니다.");
				System.out.println("  체크인하지 않은 방입니다. 다시 선택해주세요.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}

	public void checkIn() {
		
		System.out.println("─────────────────────────────────────");
		System.out.println(" (방 번호 형식: 세 자리 숫자)");
		System.out.print("▷ 어느 방을 체크인 하시겠습니까?: ");
		int room_num = Integer.parseInt(s.nextLine());
		System.out.println();
		System.out.print("▷ 누구를 체크인 하시겠습니까?: ");
		String guest_name = s.nextLine();
		System.out.println();
		boolean chk = getCheck(room_num);
		
		if(chk) {
			System.out.println("▷ " + room_num + "번 방에는 이미 사람이 있습니다.");
			return;
		}
		
		// 데이터베이스 저장
		try {
			conn = DBUtil2.getConnection();

			String sql = "INSERT INTO hotel_mng (room_num, guest_name) VALUES (?, ?) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, room_num);
			pstmt.setString(2, guest_name);

			int cnt = pstmt.executeUpdate();

			if(cnt > 0) {
				System.out.println();
				System.out.println("▷ 체크인 되었습니다.");
			}else {
				System.out.println();
				System.out.println("▶ 체크인 실패하였습니다.");
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();	// 자원 반납 메서드 생성
		}
		
		
		
		
	}

	
	private boolean getCheck(int room_num) {
		boolean chk = false;

		try {
			conn = DBUtil2.getConnection();
			String sql = "SELECT count(*) cnt FROM hotel_mng WHERE room_num = ? ";

			pstmt = conn.prepareStatement(sql);

			// 물음표를 채워주자.
			pstmt.setInt(1, room_num);

			rs = pstmt.executeQuery();
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");	
			}
			if(cnt > 0) {
				chk = true;
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}

		return chk;
	}

	// 자원 반납
	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException e){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException e){}
		if(conn!=null)try{ conn.close(); }catch(SQLException e){}
	}
	
}






class Hotel{
	
	private int roomNum;
	private String name;
	
	public Hotel(int roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "방번호: " + roomNum + ", 투숙객: " + name;
	}
	
	
	
}
