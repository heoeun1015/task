package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import kr.or.ddit.util.DBUtil2;


public class BoardOperateSystem {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	//메뉴 출력하기
	public void  displayMenu() {
		System.out.println();
		System.out.println("------게시판 운영 시스템------");
		System.out.println();
		System.out.println("  === 게 시 판 관 리 ===");
		System.out.println("  1. 전체 게시글 목록 출력");
		System.out.println("  2. 새 게시글 작성");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 게시글 삭제");
		System.out.println("  5. 검색"); 
		System.out.println("  6. 나가기");
		System.out.println("----------------------");
		System.out.print("원하는 번호를 선택해주세요. >> ");
	}
	
	//프로그램 시작 메서드
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = Integer.parseInt(scan.nextLine()); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 전체 게시글 목록 출력
					listAllBoard();
					break;
				case 2 :  // 새 게시글 등록
					insertNew();
					break;
				case 3 :  // 게시글 수정
					updateBoard();
					break;
				case 4 :  // 게시글 삭제
					deleteBoard();
					break;
				case 5 :  // 검색
					search();
					break;
				case 6 :  // 나가기
					System.out.println("게시판 운영 시스템이 종료되었습니다..");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	//어떤 항목으로 검색할지 묻는 메서드
	private void search() {
		int input;
		
		do{
			System.out.println();
			System.out.println("==어떤 항목으로 검색하시겠습니까?==");
			System.out.println("1. 제목    2. 작성자    3. 내용    4. 홈으로 돌아가기");
			input = Integer.parseInt(scan.nextLine());
			
			switch(input){
				case 1 :  //제목
					titleSearch();
					break;
				case 2 :  //작성자
					writerSearch();
					break;
				case 3 :  //내용
					contentSearch();
					break;
				case 4 :  // 나가기
					System.out.println("홈으로 돌아갑니다");
					start();
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(input !=4);
	}

	//내용으로 검색
	private void contentSearch() {
		System.out.println();
		System.out.println("검색할 내용을 입력하세요.");
		String content = scan.nextLine();
		content = "%" + content + "%";
		
		try {
			conn = DBUtil2.getConnection();
			String sql = "select board_no, board_title, board_writer, TO_CHAR(TO_DATE(board_date, 'YY.MM.DD'), 'YY.MM.DD') board_date, board_content from jdbc_board " 
						+ " where board_content LIKE ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			
			rs = pstmt.executeQuery();
			
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println(" 번호\t제목\t작성자\t작성날짜\t\t내용");
			System.out.println("-----------------------------------------------------------------------------");
			
			if(!rs.next()) {
				System.out.println("검색하신 내용에 해당하는 게시글이 없습니다.");
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("5초 후에 홈으로 돌아갑니다.");
				try {
					Thread.sleep(5000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					start();
				}
			}
	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String boardNum = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNum + "\t" 
									+ boardTitle + "\t" 
									+ boardWriter + "\t"
									+ boardDate + "\t"
									+ boardContent); 
			}
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("더 이상 조회할 게시글이 없습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	//작성자로 검색
	private void writerSearch() {
		System.out.println();
		System.out.println("검색할 작성자 아이디를 입력하세요.");
		String writer = scan.nextLine();
		writer = "%" + writer + "%";
		
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "select board_no, board_title, board_writer, TO_CHAR(TO_DATE(board_date, 'YY.MM.DD'), 'YY.MM.DD') board_date, board_content from jdbc_board " + " where board_writer LIKE ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			rs = pstmt.executeQuery();
			
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println(" 번호\t제목\t작성자\t작성날짜\t\t내용");
			System.out.println("-----------------------------------------------------------------------------");
			
			if(!rs.next()) {
				System.out.println("검색하신 작성자에 해당하는 게시글이 없습니다.");
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("5초 후에 홈으로 돌아갑니다.");
				try {
					Thread.sleep(5000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					start();
				}
			}
	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String boardNum = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNum + "\t" 
									+ boardTitle + "\t" 
									+ boardWriter + "\t"
									+ boardDate + "\t"
									+ boardContent); 
			}
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("더 이상 조회할 게시글이 없습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	//제목으로 검색
	private void titleSearch() {
		System.out.println();
		System.out.println("검색할 제목을 입력하세요.");
		String title = scan.nextLine();
		title = "%" + title + "%";
		
		try {
			conn = DBUtil2.getConnection();
			String sql = "select board_no, board_title, board_writer, TO_CHAR(TO_DATE(board_date, 'YY.MM.DD'), 'YY.MM.DD') board_date, board_content from jdbc_board " + " where board_title LIKE ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println(" 번호\t제목\t작성자\t작성날짜\t\t내용");
			System.out.println("-----------------------------------------------------------------------------");
			
			if(!rs.next()) {
				System.out.println("검색하신 제목에 해당하는 게시글이 없습니다.");
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("5초 후에 홈으로 돌아갑니다.");
				try {
					Thread.sleep(5000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}finally {
					start();
				}
			}
	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String boardNum = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNum + "\t" 
									+ boardTitle + "\t" 
									+ boardWriter + "\t"
									+ boardDate + "\t"
									+ boardContent); 
			}
			
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("더 이상 조회할 게시글이 없습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	//게시글 삭제
	private void deleteBoard() {
		System.out.println();
		String boardWriter = "";
		boolean check = true;
		
		do {
			System.out.println();
			System.out.println("회원님의 아이디를 입력하세요 >> ");
			boardWriter = scan.nextLine();
			
			check = getWriter(boardWriter);
			
			if(check == false) {
				System.out.println(boardWriter + "님이 작성하신 글이 없습니다.");
				System.out.println("홈으로 돌아갑니다");
				start();
			}
		}while(check == false);
	
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "SELECT board_no, board_title, board_writer, TO_CHAR(TO_DATE(board_date, 'YY.MM.DD'), 'YY.MM.DD') board_date, board_content FROM jdbc_board WHERE board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardWriter);
			
			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println(" 번호\t제목\t작성자\t작성날짜\t\t내용");
			System.out.println("-----------------------------------------------------------------------------");
			
			while(rs.next()) {
				String boardNum = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNum + "\t" 
									+ boardTitle + "\t" 
									+ boardWriter + "\t"
									+ boardDate + "\t"
									+ boardContent); 
			}
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("더 이상 조회할 게시글이 없습니다.");
			System.out.println("삭제할 게시글 번호를 입력하세요.");
			String num = scan.nextLine();
			
			sql = "DELETE FROM jdbc_board WHERE board_writer = ? AND board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardWriter);
			pstmt.setString(2, num);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(boardWriter + "님의 게시글을 삭제했습니다.");
			}else {
				System.out.println("게시글 삭제가 정상적으로 이루어지지 않았습니다.");
			}
		}catch(SQLException e) {
			System.out.println("게시글 삭제가 정상적으로 이루어지지 않았습니다.");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	//게시글 수정
	private void updateBoard() {
		System.out.println();
		String boardWriter = "";
		boolean check = true;
		
		do {
			System.out.println("회원님의 아이디를 입력하세요 >> ");
			boardWriter = scan.nextLine();
			
			check = getWriter(boardWriter);
			if(check == false) {
				System.out.println(boardWriter + "님이 작성하신 글이 없습니다.");
				System.out.println("홈으로 돌아갑니다.");
				start();
			}
		}while(check == false);
		
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "SELECT board_no, board_title, board_writer, TO_CHAR(TO_DATE(board_date, 'YY.MM.DD'), 'YY.MM.DD') board_date, board_content FROM jdbc_board WHERE board_writer = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardWriter);
			
			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println(" 번호\t제목\t작성자\t작성날짜\t\t내용");
			System.out.println("-----------------------------------------------------------------------------");
			
			while(rs.next()) {
				String boardNum = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNum + "\t" 
									+ boardTitle + "\t" 
									+ boardWriter + "\t"
									+ boardDate + "\t"
									+ boardContent); 
			}
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("더 이상 조회할 게시글이 없습니다.");
			
			System.out.println("수정할 게시글의 번호를 입력하세요.");
			String num = scan.nextLine();
		
			System.out.println("수정할 내용을 입력하세요.");
			System.out.println("새로운 제목 >> ");
			String boardTitle = scan.nextLine();
			
			System.out.println("새로운 내용 >> ");
			String boardContent = scan.nextLine();
			
			sql = "UPDATE jdbc_board "
						+"SET board_title = ? "
						+ "   , board_date = SYSDATE "
						+ "   , board_content = ? "
						+ "where board_writer = ? "
						+ "AND board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardWriter);
			pstmt.setString(4, num);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(boardWriter + "님의 게시글을 수정했습니다.");
			}else {
				System.out.println("게시글 수정에 실패했습니다");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	//작성자가 있는지 알려주는 메서드
	private boolean getWriter(String boardWriter) {
		boolean check = false;
		
		try {
			conn = DBUtil2.getConnection();
			String sql = "select count(*) cnt from jdbc_board " + " where board_writer = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardWriter);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				check = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return check;
	}

	//새 게시글 등록
	private void insertNew() {
		System.out.println("제목 >> ");
		String boardTitle = scan.nextLine();
		
		System.out.println("작성자 >> ");
		String boardWriter = scan.nextLine();
		
		System.out.println("내용 >> ");
		String boardContent = scan.nextLine();
		
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "INSERT INTO jdbc_board "
						+ " (board_title, board_writer, board_content, board_no, board_date) "
						+ " values (?, ?, ?, board_seq.nextval, SYSDATE) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(boardWriter + "님이 작성하신 게시글이 정상등록 되었습니다.");
			}else {
				System.out.println("게시글 등록이 정상적으로 이루어지지 않았습니다.");
			}
		}catch(SQLException e) {
			System.out.println("게시글 등록이 정상적으로 이루어지지 않았습니다.");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	//전체 게시글 목록 출력
	private void listAllBoard() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(" 번호\t제목\t작성자\t작성날짜\t\t내용");
		System.out.println("-----------------------------------------------------------------------------");
		
		try {
			conn = DBUtil2.getConnection();
			
			String sql = "SELECT board_no, board_title, board_writer, TO_CHAR(TO_DATE(board_date, 'YY.MM.DD'), 'YY.MM.DD') board_date, board_content FROM jdbc_board";
					
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
						
			while(rs.next()) {
				
			
				String boardNum = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNum + "\t" 
									+ boardTitle + "\t" 
									+ boardWriter + "\t"
									+ boardDate + "\t"
									+ boardContent); 
			}
			System.out.println("-----------------------------------------------------------------------------");
			System.out.println("더 이상 조회할 게시글이 없습니다.");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	//자원 반납
	private void disConnect() {
		//사용했던 자원 반납
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
	}

	//메인 메서드
	public static void main(String[] args) {
		BoardOperateSystem bos = new BoardOperateSystem();
		bos.start();
	}
}
