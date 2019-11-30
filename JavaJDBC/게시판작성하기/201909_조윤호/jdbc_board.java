package kr.or.ddit.basic;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;


public class jdbc_board {
	
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
		System.out.println(" === 작 업 선 택 ===");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 새글작성");
		System.out.println("  3. 수정");
		System.out.println("  4. 삭제");
		System.out.println("  5. 검색");
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
				case 1 :  // 전체 목록 출력
					displayboardAll();
					break;
				case 2 :  // 새글 작성
					insertboard();
					break;
				case 3 :  // 수정
					updateboard();
					break;
				case 4 :  // 삭제
					deleteboard();
					break;
				case 5 :  // 검색
					selectboard();
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	

	
	private void selectboard() {
		System.out.println();
		String no = "";
		boolean chk = true;
		
		do {
			System.out.println("검색할 게시판번호을 입력하세요 >> ");
			no = scan.next();
			
			chk = getMember(no);
			if(chk == false) {
				System.out.println(no + "은 없는 게시글입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}
		}while(chk == false);
		
		System.out.println();
		System.out.println("===================================================");
		System.out.println(" 번호\t제목\t작성자\t\t작성날짜\t\t내용");
		System.out.println("===================================================");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_no = " + no;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				no = rs.getString("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				
				System.out.println(no + "\t" 
								   + title + "\t" 
								   + writer + "\t"
								   + date + "\t" 
								   + content);
			}
			System.out.println("===================================================");
			System.out.println("출력 작업 끝!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		
	}

	private void deleteboard() {
		System.out.println();
		System.out.println("삭제할 게시판 번호을 입력하세요.");
		String no = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(no + "게시판 삭제 성공...");
			}else {
				System.out.println(no + "게시판 삭제 실패!!!");
			}
		}catch(SQLException e) {
			System.out.println(no + "게시판 삭제 실패!!!");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	
	
	// 게시판을 수정하는 메서드
	
	private void updateboard() {
		System.out.println();
		String no = "";
		boolean chk = true;
		
		do {
			System.out.println("수정할 게시판제목을 입력하세요 >> ");
			no = scan.next();
			
			chk = getMember(no);
			if(chk == false) {
				System.out.println(no + "은 없는 게시글입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}
			
			
		}while(chk == false);
		
		System.out.println("수정할 제목을 입력하세요.");
		System.out.println("새로운 제목 >> ");
		String title1 = scan.next();
		
		System.out.println("새로운 작성자 이름 >> ");
		String name = scan.next();
		
		scan.nextLine();
		System.out.println("새로운 내용 >> ");
		String content = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update jdbc_board "
					   + "set board_title = ?"
					   + " ,board_writer = ?"
					   + " ,board_content = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title1);
			pstmt.setString(2, name);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(title1 + " 게시판 정보를 수정했습니다.");
			}else {
				System.out.println(title1 + " 게시판 정보 수정 실패!");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	
	
	
	
	// 전체 회원을 출력하는 메서드
	
	private void displayboardAll() {
		System.out.println();
		System.out.println("===================================================");
		System.out.println(" 번호\t제목\t작성자\t\t작성날짜\t\t내용");
		System.out.println("===================================================");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String no = rs.getString("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				
				System.out.println(no + "\t" 
								   + title + "\t" 
								   + writer + "\t"
								   + date + "\t" 
								   + content);
			}
			System.out.println("===================================================");
			System.out.println("출력 작업 끝!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}

	
	
	
	// 회원을 추가하기 위한 메서드
	
	private void insertboard() {
		boolean chk = false;
		
			System.out.println();
			System.out.println("제목 >> ");
			String title = scan.next();
			
			System.out.println("작성자 >> ");
			String name = scan.next();
			
			scan.nextLine();	//입력버퍼 비우기
			
			System.out.println("내용 >> ");
			String content = scan.nextLine();
			
			try {
				//conn = DBUtil.getConnection();
				conn = DBUtil2.getConnection();
				String sql = "insert into jdbc_board" 
						   + "(board_no,board_title, board_writer, board_date, board_content)"
						   + "values (board_seq.nextval,?,?,sysdate,?)";
							
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,title);
				pstmt.setString(2,name);
				pstmt.setString(3,content);
				
				
				int cnt = pstmt.executeUpdate();
				if(cnt > 0) {
					System.out.println("게시판 추가 작업 성공");
				}else {
					System.out.println("게시판 추가 작업 실패!!!");
				}
				
			}catch(SQLException e) {
				System.out.println("게시판 추가 작업 실패!!!");
				e.printStackTrace();
			}finally {
				disConnect();
			}
			
		
	}

	private void disConnect() {
			//  사용했던 자원 반납
			if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
			if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
			if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	
	//게시판 검색
	private boolean getMember(String no) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from jdbc_board "
					   + "where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			
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
		jdbc_board memObj = new jdbc_board();
		memObj.start();
	}

}


