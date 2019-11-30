package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

import util.DBUtil3;

public class CRUD {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner s = new Scanner(System.in);

	//메뉴
	public void displayMenu() {
		System.out.println();
		System.out.println("==========================");
		System.out.println("=========메뉴 선택 =========");
		System.out.println("1.전체 목록 출력");
		System.out.println("2.새 글 작성");
		System.out.println("3.글 수정");
		System.out.println("4.글 삭제");
		System.out.println("5.게시판 검색");
		System.out.println("==========================");
	}
	public void start() {
		int choice;
		do {
			displayMenu();
			choice = s.nextInt();
			switch(choice) {
			case 1: // 전체 목록 출력
				displayList();
				break;
			case 2: // 새 글 작성
				write();
				break;
			case 3: // 글 수정
				modify();
				break;
			case 4 : // 글 삭제
				delete();
				break;
			case 5 : // 검색
				search();
				break;
			default :
				System.out.println("번호를 잘못 입력하였습니다. 다시 입력해주세요.");
			}
		}while(choice !=5);
	}
	private void displayList() {
		System.out.println();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				Date board_date = rs.getDate("board_date");
				String content = rs.getString("board_content");
				System.out.println(board_no + "\t" + board_title+ "\t"+board_writer
						+ "\t" + board_date);
				System.out.println(content);
			}
		}catch(SQLException e) {
			System.out.println("검색 실패");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	//검색하는 메소드
	private void search() {
		System.out.println();
		System.out.println("읽을 글 번호를 입력해주세요.");
		int num = s.nextInt();
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				Date board_date = rs.getDate("board_date");
				String content = rs.getString("board_content");
				
				System.out.println(board_no + "\t" + board_title+ "\t"+board_writer
						+ "\t" + board_date);
				System.out.println(content);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	//삭제하는 메소드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 게시글 번호를 입력하세요.");
		int num = s.nextInt();
		try {
			conn= DBUtil3.getConnection();
			String sql = "delete jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(num +"번 글 삭제");
			}else {
				System.out.println("삭제 실패");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("삭제 실패");
		}finally {
			disConnect();
		}
	}
	private void modify() {
		
		boolean flag = true;
		System.out.println("수정할 글 번호를 입력하세요.");
		int num = s.nextInt();
		do {
			flag = getNum(num);
			if(flag == false) {
				System.out.println(num + "은 없는 글입니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(flag == false);
		System.out.println("수정할 내용을 입력하세요.");
		System.out.println("제목>>");
		String title = s.next();
		System.out.println("작성자");
		String writer = s.next();
		s.nextLine();
		System.out.println("내용");
		String content = s.nextLine();
		try {
			
			conn=DBUtil3.getConnection();
			String sql = "update jdbc_board" + " set board_title = ? , board_writer = ? ," 
					+ " board_date = sysdate , board_content = ? where board_no = "+ num;
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			int cnt = pstmt.executeUpdate();
			if(cnt >0) {
				System.out.println("업데이트에 성공했습니다.");
			}else {
				System.out.println("수정 실패");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("수정 실패");
		}finally {
			disConnect();
		}
	}
	
	private void write() {
		// 오늘 날짜
//		SimpleDateFormat fomatter = new SimpleDateFormat("yyyy/MM/DD");
//		String today = fomatter.format(currentTime);
		
		//1. no 2. title 3. writer 4.date 5. content
		try {
			
			System.out.println();
			System.out.println("제목을 입력하세요.");
			String title = s.next();
			System.out.println("작성자를 입력하세요.");
			String writer = s.next();
			s.nextLine();
			System.out.println("내용을 입력하세요.");
			String content = s.nextLine();
			
			conn=DBUtil3.getConnection();
			//테이블에 데이터 넣기
			String sql = "insert into jdbc_board values (board_seq.nextval,?,?,sysdate,?) ";
			// 글 작성 날짜로 세팅
			// 데이터 넣기
				
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,title);
			pstmt.setString(2, writer);
			// 오늘 날짜 가지고 오기.
			pstmt.setString(3, content);
			int cnt = pstmt.executeUpdate();
			if(cnt >0) {
				System.out.println("success");
			}else {
				System.out.println("fail");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("글 작성 실패");
			
		}finally {
			disConnect();
		}
	}
	// 글 유무 확인
	private boolean getNum(int num) {
		boolean flag = false;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select count(*) cnt from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt >0) {
				flag = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return flag;
		
	}
	//연결을 끊는 메소드
	private void disConnect() {
		if(rs!=null) try {rs.close();} catch(SQLException e) {};
		if(stmt!=null) try {stmt.close();} catch(SQLException e) {};
		if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {};
		if(conn!=null) try {conn.close();} catch(SQLException e) {};
	}
	public static void main(String[] args) {
		CRUD crud = new CRUD();
		crud.start();
	}
}
