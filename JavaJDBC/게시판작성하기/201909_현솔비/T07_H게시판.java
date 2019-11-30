package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

/*
 위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal
 */
public class T07_H게시판 {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	
	//메뉴 출력 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 목록 출력");
		System.out.println("  2. 새글 작성");
		System.out.println("  3. 수정");
		System.out.println("  4. 삭제");
		System.out.println("  5. 검색");
		System.out.println("  0. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 전체 목록 출력
					displayMemberAll();
					break;
				case 2 :  // 새글 작성
					writeMember();
					break;
				case 3 :  // 수정
					updateMember();
					break;
				case 4 :  // 삭제
					deleteMember();
					break;
				case 5 :  // 검색
					searchMember(null);
					break;
				case 0 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	//글을 검색하는 메서드
	private void searchMember(String memTitle) {
		System.out.println();
		memTitle = "";
		boolean chk = true;
		
		do {
			System.out.print("검색할 글의 제목을 입력하세요> ");
			memTitle= scan.next();
			
			chk = getTitle(memTitle);
			
			if(chk == false) {
				System.out.println("해당 제목의 게시글이 없습니다.");
				
			}
			
		}while(chk==false);
		
		
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println(" 번  호\t제  목\t작성자\t작성날짜\t\t\t내  용");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board WHERE BOARD_TITLE = '" + memTitle + "'";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				String memNo = rs.getString("BOARD_NO");
				memTitle = rs.getString("BOARD_TITLE");
				String memWriter = rs.getString("BOARD_WRITER");
				String memDate = rs.getString("BOARD_DATE");
				String memContent = rs.getString("BOARD_DATE");
				
				System.out.println(memNo + "\t" + memTitle + "\t" + memWriter +"\t"+ memDate+"\t"+ memContent);
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	
	//글을 삭제하는 메서드
	private void deleteMember() {
		System.out.println();
		System.out.print("삭제할 글번호 >>");
		String memNo = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE BOARD_NO=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memNo);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(memNo + "번 게시글 삭제 성공...");
			}else {
				System.out.println(memNo + "번 게시글 삭제 실패~!");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}

	//글을 수정하는 메서드
	private void updateMember() {
		System.out.println();
		String memNo = "";
		boolean chk = true;
		
		do {
			System.out.print("수정 할 게시글의 번호를 입력하세요> ");
			memNo= scan.next();
			
			chk = getNum(memNo);
			if(chk == false) {
				
				System.out.println("수정할 게시글이 없습니다. 다시 입력하세요.");
			}
			
		}while(chk==false);
		
		
		System.out.print("작성자를 입력하세요 >> ");
		String memWriter = scan.next();
		
		
		System.out.print("제목을 입력하세요 >> ");
		String memTitle = scan.next();
		
		
		scan.nextLine();
		System.out.print("내용을 입력하세요 >> ");
		String memContent = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "UPDATE jdbc_board "
						+ "SET BOARD_TITLE = ? "
						  + ", BOARD_DATE = SYSDATE "
						  + ", BOARD_CONTENT = ? "
						  + ", BOARD_WRITER = ? "
						  + "WHERE BOARD_NO = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memTitle);
			pstmt.setString(2, memContent);
			pstmt.setString(3, memWriter);
			pstmt.setString(4, memNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("글을 수정했습니다.");
			}else {
				System.out.println("수정 실패~!");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	
	
	//글번호 이용하여 글이 있는지 알려주는 메서드
	private boolean getNum(String memNo) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from jdbc_board "
						+ "where BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memNo);
			
			rs = pstmt.executeQuery();		//executeQuery는 resultset을 리턴
			
			int cnt = 0;  
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt>0) {
				chk = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return chk;
	}

	//글제목을 이용하여 글이있는지 확인하는 메서드
	private boolean getTitle(String memTitle) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from jdbc_board "
						+ "where BOARD_TITLE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memTitle);
			
			rs = pstmt.executeQuery();		//executeQuery는 resultset을 리턴
			
			int cnt = 0;  
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt>0) {
				chk = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
		return chk;
	}


	//새 글을 작성하는 메서드
	private void writeMember() {
		
		System.out.print("제목을 입력하세요>> ");
		String memTitle = scan.next();
		
		System.out.print("작성자 이름을 입력하세요>> ");
		String memWriter = scan.next();
		
		scan.nextLine();	//입력버퍼 비우기
		
		System.out.print("내용을 입력하세요>> ");
		String memContent = scan.nextLine();
		
		try {
			
//			conn = DBUtil.getConnection();
//			conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "INSERT INTO jdbc_board"
					+ 	"(BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CONTENT) "
					+ 	" VALUES (board_seq.nextval, ?, ?, SYSDATE , ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memTitle);
			pstmt.setString(2, memWriter);
			pstmt.setString(3, memContent);
		
			int cnt = pstmt.executeUpdate();	//executeUpdate는 int값
			
			if(cnt>0) {
				System.out.println("게시글 추가 작업 성공");
			}else {
				System.out.println("게시글 추가 작업 실패~");
			}
			
		}catch (SQLException e) {
			System.out.println("게시글 추가 작업 실패~");
			e.printStackTrace();
		}finally {	
			disConnect(); 	//자원반납 메서드
		}
		
	}

	//전체 목록을 출력하는 메서드
	private void displayMemberAll() {
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println(" 번  호\t제  목\t작성자\t작성날짜\t\t\t내  용");
		
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memNo = rs.getString("BOARD_NO");
				String memTitle = rs.getString("BOARD_TITLE");
				String memWriter = rs.getString("BOARD_WRITER");
				String memDate = rs.getString("BOARD_DATE");
				String memContent = rs.getString("BOARD_CONTENT");
				
				System.out.println(memNo + "\t" + memTitle + "\t" + memWriter +"\t"+ memDate+"\t"+ memContent);
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	
		private void disConnect() {
			//  사용했던 자원 반납
			if(rs!=null)try{ rs.close(); }catch(SQLException e){}
			if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
			if(stmt!=null)try{ stmt.close(); }catch(SQLException e){}
			if(conn!=null)try{ conn.close(); }catch(SQLException e){}
			
		}

	public static void main(String[] args) {
		T07_H게시판 memObj = new T07_H게시판();
		memObj.start();
	}
}