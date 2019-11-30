package homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class MessageBoard {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner sc = new Scanner(System.in);
	
	public MessageBoard() {
		sc = new Scanner(System.in);
	}
	
	public void menu () {
		System.out.println("-------------------------------------------------------\r"
				+ "\t\t     게   시   판\r"
				+ "-------------------------------------------------------\r"
				+ "1. 게시물 등록       2. 게시물 삭제  3. 게시물 수정\r"
				+ "4. 전체 게시물 조회  5. 게시물 검색  6. 프로그램 종료\r"
				+ "-------------------------------------------------------");
	}
	
	public void start () {
		
		int choice;
		
		do {		
			menu();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				select();
				break;
			case 5:
				search();
				break;
			case 6:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}while(choice != 6);
	}
	
	public static void main(String[] args) {
		new MessageBoard().start();
	}
	
	private void insert() {

		System.out.print("게시물 제목을 입력하세요 > ");
		String board_title = sc.next();
		
		System.out.print("작성자를 입력하세요 > ");
		String board_writer = sc.next();
		
		System.out.print("내용을 입력해주세요 > ");
		String board_content = sc.next();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "insert into jdbc_board values (board_seq.nextVal, ?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_writer);
			pstmt.setString(3, board_content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시물 등록이 완료되었습니다.");
			}else {
				System.out.println("게시물 등록이 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void disConnect() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
		}
	}

	private void delete() {
		System.out.println();
		try {
			conn = DBUtil.getConnection();
			String sql = "select board_no, board_title from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				
				System.out.println("게시물 번호 : " + board_no + "\t게시물 제목 : " + board_title);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("삭제하려는 게시물의 번호를 입력하세요 > ");
		String board_no = sc.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시물이 삭제되었습니다.");
			}else {
				System.out.println("게시물 삭제에 실패했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void update() {
		System.out.println();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select board_no, board_title from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				
				System.out.println("게시물 번호 : " + board_no + "\t게시물 제목 : " + board_title);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.print("수정할 게시물 번호를 선택해주세요 > ");
		String board_no = sc.next();
		
		System.out.print("게시물 제목을 입력하세요 > ");
		String board_title = sc.next();
		
		System.out.print("내용을 입력해주세요 > ");
		String board_content = sc.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_date = sysdate, board_content = ? where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_content);
			pstmt.setString(3, board_no);
			
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시물 수정 성공 !");
			}else {
				System.out.println("게시물 수정 실패 !");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void select() {
		System.out.println("┌----------------------- 게시물 ----------------------┐");
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				
				System.out.println("   No\t제목\t\t작성자  작성일");
				System.out.println("   "+ board_no + "\t" + board_title + "\t\t" + board_writer + "\t" + board_date);
				System.out.println("\t\t\t내용");
				System.out.println("   "+ board_content);
			}
			System.out.println("└-----------------------------------------------------┘");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void search() {
		System.out.println();
		System.out.print("검색할 게시물 번호를 입력하세요 > ");
		String board_no = sc.next();
		
		System.out.println("┌----------------------- 게시물 ----------------------┐");
		
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			pstmt.executeUpdate();
			
			rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				
				System.out.println("   No\t\t제목\t작성자  작성일");
				System.out.println("   "+ board_no + "\t\t" + board_title + "\t" + board_writer + "\t" + board_date);
				System.out.println("\t\t\t내용");
				System.out.println("   "+ board_content);
			}
			System.out.println("└-----------------------------------------------------┘");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
}
