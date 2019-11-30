package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import kr.or.ddit.util.DButil;


public class Board {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	
	public void displayMenu() {
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println();
		System.out.println("1. 새글작성");
		System.out.println("2. 글 수정");
		System.out.println("3. 글 삭제");
		System.out.println("4. 전체 게시글 출력");
		System.out.println("5. 검색");
		System.out.println("0. 작업 끝");
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println("원하는 작업 선택  : ");
	}
	
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴출력
			choice = scan.nextInt();
			switch (choice) {
			case 1: // 새글입력
				newboard();
				break;
			case 2: // 글 수정
				updateBoard();
				break;
			case 3: // 글 삭제
				deleteBoard();
				break;
			case 4: // 전체 게시글 출력
				displayAll();
				break;
			case 5: // 글 검색
				searchContent();
				break;
			case 0: // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;

			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}while(choice != 6);
	}
	
	/**
	 * 글 존재여부를 알려주는 메서드
	 */
	private void searchContent() {
		System.out.println();
		
		try {
			boolean chk;
			String number ="";
			
		do {
			System.out.println("찾고싶은 글을 입력하세요. :");
			number = scan.next();
			
			chk = getNumber(number);
			
			if(chk==false) {
				System.out.println(number +"의 글은 존재하지 않습니다.");
				System.out.println("다시 입력하세요.");
				System.out.println();
				
			}
		}while(chk==false);
		
		conn = DButil.getConnection();
		String sql = "select * from jbc_board" + "where board_no =?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, number);
		
		rs = pstmt.executeQuery();
		
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("번호 \t 제목 \t 작성자 \t 작성날짜 \t\t 내용");
		System.out.println("--------------------------------------------");
		System.out.println();
		System.out.println("글이 존재합니다.");
		System.out.println();
		System.out.println("--------------------------------------------");
		
		
		
		}catch(SQLException e) {
			System.out.println();
			System.out.println("글 가져오기 실패");
			e.printStackTrace();
		}finally {
			System.out.println();
			System.out.println("검색작업 끝....");
			disConnect(); // 자원반납
		}
	}
	
	private boolean getNumber(String number) {
	
		return false;
	}

	/**
	 * 전체 게시글을 출력하는 메서드
	 */
	private void displayAll() {
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println("번호 \t 제목 \t 작성자 \t 작성날짜 \t\t 내용");
		System.out.println("--------------------------------------------");
		
		try {
			conn = DButil.getConnection();
			String sql = "select * from jdbc_board";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String number = rs.getString("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				Date date = rs.getDate("board_date");
				String content = rs.getString("board_content");
				
				SimpleDateFormat a = new SimpleDateFormat("YYYY-MM-DD");
				String formaDate = a.format(date);
				
				System.out.println(number +"  "+ title +"  "+ writer +"  "+ date +"  "+ content);
				
			}
			System.out.println("--------------------------------------------");
			System.out.println("전체 목록 출력 완료 : ");
		
		}catch(SQLException e) {
			System.out.println();
			System.out.println("전체 게시글 가져오기 실패");
			e.printStackTrace();
		}finally {
			disConnect(); // 자원 반납
		}
		
	}
	
	/*
	 * 글 수정하는 메서드
	 */
	private void updateBoard() {
		System.out.println();
		boolean chk = true;
		String number = "";
		
		do {
			System.out.println("수정할 글의 번호를 입력하세요 : ");
			number = scan.next();
			
			chk = getNumber(number); // 현재 존재하는 게시글 번호확인
			if(chk == false) {
				System.out.println(number + "가 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
				System.out.println();
			}
		}while(chk == false);
		
		System.out.println();
		System.out.println("수정할 글 제목 입력 : ");
		String title = scan.next();
		
		System.out.println("작성자 이름을 입력하세요 : ");
		String writer = scan.next();
		
		scan.next(); // 입력 버퍼 지우기
		
		System.out.println("입력날짜입력 : ex)20191118 ");
		String date = scan.next();
		
		scan.next(); // 입력 버퍼 지우기
		
		System.out.println("작성할 글 내용을 입력하세요 : ");
		String content = scan.next();
		
		try {
			conn = DButil.getConnection();
			
			String sql = "update jdbc_board" + "set board_title = ? ," +"border_writer =? , " + "border_date = ? ," + "border_content =? ,"+ "where border_no =?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, date);
			pstmt.setString(4, content);
			pstmt.setString(5, number);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println(number + "번째 글을 수정했습니다.");
			}else {
				System.out.println(number + "의 글 수정을 실패했습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			disConnect(); // 자원 반납
		}
	}
	/**
	 * 게시글 삭제하는 메서드
	 */
	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 글 번호를 입력하세요 : ");
		
		String number = scan.next();
		
		try {
			conn = DButil.getConnection();
			String sql = "delete from jdbc_board where board_no =?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, number);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt >0) {
				System.out.println(number + "번 글 삭제 성공...");
			}else {
				System.out.println(number + "번 글 삭제 실패");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect(); // 자원 반납
		}
	}
	
	/**
	 * 게시글 추가하는 메서드
	 */
	
	private void newboard() {
		boolean chk = false;
		
		System.out.println("글 제목을 입력하세요 : ");
		String title = scan.next();
		
		scan.nextLine(); // 입력버퍼 지우기
		System.out.println("작성자 이름을 입력하세요 : ");
		String writer = scan.next();
		
		System.out.println("작성날짜 입력 ex) 20191118");
		String date = scan.next();
		
		scan.nextLine(); // 입력버퍼 지우기
		System.out.println("작성한 글 내용을 입력하세요 : ");
		String content = scan.nextLine();
		
		try {
			conn = DButil.getConnection();
			String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content)"
					+ "values(board_seq.nextval,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, date);
			pstmt.setString(4, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0) {
				System.out.println();
				System.out.println("글 입력 성공");
			}else {
				System.out.println();
				System.out.println("글 입력 실패");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println();
			disConnect(); // 자원 반납
		}
	}
	
	/**
	 * 자원 반납 메서드
	 */
	private void disConnect() {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException e) {
			}
		}if(stmt != null) {
			try {
				stmt.close();
			}catch(SQLException e) {
			}
		}if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
			}
		}if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
			}
	}
	
}
	
	public static void main(String[] args) {
		Board hb = new Board();
		hb.start();
	}
}