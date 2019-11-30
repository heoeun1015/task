package report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil3;

public class JDBC_Hotel {
	static Scanner s = new Scanner(System.in);
	
	static Connection conn = null;
	static PreparedStatement pstmt =null;
	
	public static void main(String[] args) {
		conn = DBUtil3.getConnection();
		
		int numberMenu=0;
		
		System.out.println("**************************\r\n" + 
				"호텔 문을 열었습니다.\r\n" + 
				"**************************");
		
		while(numberMenu!=4)
		{
			System.out.print("\r\n" + 
					"*******************************************\r\n" + 
					"어떤 업무를 하시겠습니까?\r\n" + 
					"1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n" + 
					"*******************************************\r\n" + 
					"메뉴선택 => ");
			numberMenu = Integer.parseInt(s.nextLine());
			
			switch(numberMenu)
			{
				case 1:
					checkIn();
					break;
				case 2:
					checkOut();
					break;
				case 3:
					roomInfo();
				default:
					break;
			}
		}
		
		System.out.println("**************************\r\n" + 
				"호텔 문을 닫았습니다.\r\n" + 
				"**************************");
		s.close();
		if(conn!=null) try {conn.close();} catch (SQLException e) {};
	}

	private static void roomInfo() {
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM hotel_mng";
		
		try {
			stmt = conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next())
			{
				System.out.println("방번호 : " + rs.getInt("room_num")
						+ ", 투숙객 : " + rs.getString("guest_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();} catch (SQLException e) {};
			if(stmt!=null) try {stmt.close();} catch (SQLException e) {};
		}
	}

	private static void checkOut() {
		int roomNumber=0;
		
		System.out.print("\n어느방을 체크아웃 하시겠습니까?\r\n" + 
				"방번호 입력 => ");
		roomNumber = Integer.parseInt(s.nextLine());
		
		String sql = "DELETE hotel_mng WHERE room_num = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, roomNumber);
			
			if((pstmt.executeUpdate())>0)
			{
				System.out.println("체크아웃 되었습니다.");
			}else {
				System.out.println(roomNumber+"방에는 체크인한 사람이 없습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {};
		}
	}

	private static void checkIn() {
		System.out.print("어느방에 체크인 하시겠습니까?\r\n" + 
				"방번호 입력 => ");
		int roomNumber = Integer.parseInt(s.nextLine());
		System.out.print("누구를 체크인 하시겠습니까?\r\n" + 
				"이름 입력 => ");
		String guestName = s.nextLine();
		
		String sql = "INSERT INTO hotel_mng (room_num, guest_name) VALUES (?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNumber);
			pstmt.setString(2, guestName);
			
			if(pstmt.executeUpdate()>0)
			{
				System.out.println("체크인 되었습니다.");
			} else {
				System.out.println(roomNumber + "방에는 이미 사람이 있습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {};
		}
	}
}
