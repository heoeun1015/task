package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class Board {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 게시글 작성");
		System.out.println("  2. 게시글 수정");
		System.out.println("  3. 게시글 삭제");
		System.out.println("  4. 전체 게시글 출력");
		System.out.println("  5. 검색");
		System.out.println("  6. 종료");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 게시글 작성
					insertBoard();
					break;
				case 2 :  // 게시글 수정
					updateBoard();
					break;
				case 3 :  // 게시글 삭제
					deleteBoard();
					break;
				case 4 :  // 전체 게시글 출력
					displayBoardAll();
					break;
				case 5 :  // 검색
					searchBoard();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	//게시글 검색 메서드
	private void searchBoard() {
		boolean chk = true;
		
		String boardNo;
		
		do {
			System.out.println();
			System.out.println("검색할 게시글 번호를 입력하세요.");
			System.out.println("게시글 번호 >> ");
			boardNo = scan.next();
			chk = getBoard(boardNo);
			if(!chk) {
				System.out.println(boardNo + "번 게시글은 존재하지 않습니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(!chk);
		
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println(" 게시글 번호\t제목\t작성자\t작성날짜\t\t\t내용");
		System.out.println("------------------------------------------------");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNo + "\t" + boardTitle 
								+ "\t" + boardWriter + "\t" + boardDate
								+ "\t" + boardContent);
			}
			System.out.println("------------------------------------------------");
			System.out.println("출력 작업 끝...");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	//전체 게시글 출력 메서드
	private void displayBoardAll() {
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println(" 게시글 번호\t제목\t작성자\t작성날짜\t\t\t내용");
		System.out.println("------------------------------------------------");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNo + "\t" + boardTitle 
								+ "\t" + boardWriter + "\t" + boardDate
								+ "\t" + boardContent);
			}
			System.out.println("------------------------------------------------");
			System.out.println("출력 작업 끝...");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	//게시글 삭제 메서드
	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시글 번호를 입력하세요.");
		String boardNo = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(boardNo + "번 게시글 삭제 성공..");
			}else {
				System.out.println(boardNo + "번 게시글 삭제 실패!");
			}
		}catch(SQLException e) {
			System.out.println(boardNo + "번 게시글 삭제 실패!");
			e.printStackTrace();
		}finally {
			disConnect();
		}		
	}
	//게시글 수정 메서드
	private void updateBoard() {
		System.out.println();
		String boardNo = "";
		boolean chk = true;
		
		do {
			System.out.println("수정할 게시글 번호를 입력하세요 >> ");
			boardNo = scan.next();
			
			chk = getBoard(boardNo);
			if(chk == false) {
				System.out.println(boardNo + "번 게시글은 없습니다.");
				System.out.println("수정할 게시글이 없으니 다시 입력하세요.");
			}
		}while(chk == false);
		 
		System.out.println("수정할 내용을 입력하세요.");
		System.out.println("수정할 게시글 제목 >> ");
		String boardTitle = scan.next();
		
		scan.nextLine();
		System.out.println("수정할 게시글 내용 >> ");
		String boardContent = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
					
			String sql = "update jdbc_board " + "set board_title = ? " 
											+ " ,board_content = ? " 
											+ "where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(boardNo + "번 게시글을 수정했습니다.");
			}else {
				System.out.println("게시글 수정 실패!");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	//게시글 작성 메서드
	private void insertBoard() {
	
		System.out.println();
		System.out.println("게시글 제목을 입력하세요");
		System.out.println("제목 >> ");
		String boardTitle = scan.next();
			
		
		System.out.println("작성자 명 >> ");
		String boardWriter = scan.next();
		
		scan.nextLine(); //입력버퍼 바꾸기
		
		System.out.println("내용 >> ");
		String boardContent = scan.nextLine();
		
		try {
			//conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "insert into jdbc_board "
						+ "(board_no, board_title, board_writer, board_date, board_content) "
						+ " values (board_seq.nextval,?,?,sysdate,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
			
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글이 등록되었습니다.");
			}else {
				System.out.println("게시글 등록 실패!");
			}
		}catch(SQLException e) {
			System.out.println("게시글 등록 실패!");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	/**
	 * 자원반납
	 */
	private void disConnect() {
		if(rs!=null) try {rs.close();}catch(SQLException e) {}
		if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
		if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
		if(conn!=null) try {conn.close();}catch(SQLException e) {}
	}
	
	/**
	 * 게시글 번호를 이용하여 게시글이 있는지 알려주는 메서드
	 * @param boardNo
	 * @return
	 */
	private boolean getBoard(String boardNo) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from jdbc_board " + "where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
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

	
	public static void main(String[] args) {
		Board boardObj = new Board();
		boardObj.start();
	}

}
