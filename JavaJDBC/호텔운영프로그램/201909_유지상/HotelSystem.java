package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import kr.or.ddit.util.DBUtil;

public class HotelSystem {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Scanner scan = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		System.out.println("*************************");
			System.out.println("호텔 문을 열었습니다.");
			System.out.println("*************************");
			new HotelSystem().Start();
		}
		
		
	//========================================================================
		
		
	public void Start() {
		int menu = 99;
		do {
		try {
			
		System.out.println();
		System.out.println("********************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인 | 2. 체크아웃 | 3. 객실 상태 | 4. 업무 종료");
		System.out.println("********************************************");
		System.out.print("메뉴 선택 -> ");
		menu = Integer.parseInt(scan.nextLine());
		
			switch(menu) {
				case 1: checkIn(); 		break;
				case 2: checkOut();		break;
				case 3: roomStatus();	break;
				case 4: end();			break;
			}
				
		}catch(Exception e) {
			System.out.println("숫자로 입력해주세요.\n");
			e.printStackTrace();
		}
		}while(menu != 4);
	}
		
		
	public void checkIn() {
		boolean check = true;
		
		int roomNumber;
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 -> ");
		roomNumber = Integer.parseInt(scan.nextLine());
		
		System.out.println("\n누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 -> ");
		String name = scan.nextLine();
		
		//----------------------------------------------------------
		check = getGuest(roomNumber);
		if(check) {
			System.out.println(roomNumber+"방에는 이미 사람이 있습니다.");
			return;
		}
		//----------------------------------------------------------
		
		try {
			conn = DBUtil.getConnection();
			String sqlSave = "INSERT INTO hotel_mng(room_num, guest_name) "
							+ "VALUES (?, ?)";
			pstmt = conn.prepareStatement(sqlSave);
				
			pstmt.setInt(1, roomNumber);
			pstmt.setString(2, name);
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println("체크인 되었습니다.");
			}else {
				System.out.println("체크인에 실패했습니다.");
			}
				
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}


		
	public void checkOut() {
		boolean check;
		
		System.out.println();
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 -> ");
		int roomNumber = Integer.parseInt(scan.nextLine());
		
		check = getGuest(roomNumber);
		if(check != true) {
			System.out.println(roomNumber+"방에는 체크인한 사람이 없습니다.");
			return;
		}
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "DELETE FROM hotel_mng WHERE room_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNumber);
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				System.out.println("체크아웃 되었습니다.");
			}else {
				System.out.println("체크아웃에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	
	public void roomStatus() {
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(room_num) cnt"
						+" FROM hotel_mng"; 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			System.out.println("\n===========================================");
			if(rs.next()) {
				int Ccnt = rs.getInt("cnt");
				
				if(Ccnt == 0) {
					System.out.println("체크인 된 방이 없습니다.");
					System.out.println("===========================================");
					return;
				}
			
			// -----------------------------------------------------------------	
				
			sql = "SELECT * FROM hotel_mng"
				+" ORDER BY room_num";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
				
			System.out.println("[ 투숙객 정보 ]");
			System.out.println("-------------------------------------------");
			while(rs.next()) {
				int roomNum = rs.getInt("room_num");
				String Gname = rs.getString("guest_name");
				System.out.println("방번호: " + roomNum +", 투숙객: " + Gname);
			}
			System.out.println("===========================================");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	
	public void end() {
		System.out.println();
		System.out.println("*************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("*************************");
	}
	
	
	private void disConnect() {
		if(pstmt!=null){ try {pstmt.close();} catch(SQLException e){} }
		if(conn!=null){ try {conn.close();} catch(SQLException e){} }
		if(rs!=null){ try {rs.close();} catch(SQLException e){} }
	}
	
	private boolean getGuest(int roomNumber) {
		boolean check = false;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT count(*) cnt FROM hotel_mng"
						+" WHERE room_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNumber);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {	cnt = rs.getInt("cnt");	}
			if(cnt > 0) { check = true; }
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return check;
	}
	
	
	
	
	
}



