package kr.or.ddit.basic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

/*위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 
삭제, 검색 
 
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


public class boardhw {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner s = new Scanner(System.in);
	
	//글작성
	public void write(){
		System.out.print("제목 입력 : ");
		String title = s.next();
		System.out.print("작성자 : ");
		String writer = s.next();
		s.nextLine();
		System.out.println("내용을 입력하세요=>");
		String content = s.nextLine();

		try{
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board values (board_seq.nextval, ?, ?, sysdate, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
		
			int cnt = pstmt.executeUpdate();
			if(cnt>0){
				System.out.println("글 작성 성공");
			}else {
				System.out.println("글 작성 실패");
			}
			
		}catch(SQLException e){
			System.out.println("글 작성 실패");
			e.printStackTrace();
		}finally{
			disConnect();
		}
		
			}
	//수정
	public void modify(){
		System.out.println();
		String writer;
		boolean chk = true;//true면 있고 false면 없음
		
		do {
			System.out.print("작성자를 입력하세요 >> ");
			writer = s.next();
			
			chk = getWriter(writer);
			if(chk == false) {
				System.out.println("없는 회원입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}
		}while(chk == false);
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 제목 >> " );
		String title = s.next();
		
		System.out.print("새로운 내용 >> ");
		String content = s.next();
		s.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_title = ?   , board_content = ? where board_writer = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);

			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(writer + "님의 게시글을 수정했습니다.");
			}else {
				System.out.println(writer + "님의 게시글을 수정실패");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}	
	//삭제
	public void delete(){
		viewAll();
		System.out.print("삭제할 글 번호 입력 :");
		int boardno = s.nextInt();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardno);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		}catch(SQLException e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		}finally {
			disConnect();
			
		}
	}	
	//검색
	public void search(){
		System.out.println();
		System.out.println("작성자를 입력하세요=>");
		String writer= s.next();
		
		try{
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_writer = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			rs = pstmt.executeQuery();
			
			System.out.println("--------------------------작성글 ----------------------------");
			System.out.println(" 글번호\t제목\t작성자\t내용\t작성일");
			while(rs.next()) {
				String boardno = rs.getString("board_no");
				String title = rs.getString("board_title");
				String content = rs.getString("board_content");
				writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				
				System.out.println(boardno + "\t" + title + "\t" + writer + "\t" + content + "\t" + date);
			}
			System.out.println("-----------------------------------------------------------");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			disConnect();
		}
		
		
	}	
	//전체출력
	public void viewAll(){
		System.out.println();
		System.out.println("----------------------------------------------------");
		System.out.println(" 글번호\t제목\t작성자\t내용\t작성일");
		System.out.println("----------------------------------------------------");
		try{
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board order by board_no";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String boardno = rs.getString("board_no");
				String title = rs.getString("board_title");
				String content = rs.getString("board_content");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				
				System.out.println(boardno + "\t" + title + "\t" + writer + "\t" + content + "\t" + date);
			}
			System.out.println("-----------------------------");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			disConnect();
		}
	}	
	
	
	//작성자 조회
	public boolean getWriter(String writer){
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board where board_writer = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String boardno = rs.getString("board_no");
				String title = rs.getString("board_title");
				writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				chk = true;
				System.out.println(boardno + "\t" + title + "\t" + writer + "\t" + date + "\t" + content);
			}
			System.out.println("-----------------------------");
			
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return chk;
	}
	//자원반납
	public void disConnect(){
		if(conn != null)try{conn.close();}catch(SQLException e){}
		if(stmt != null)try{stmt.close();}catch(SQLException e){}
		if(pstmt != null)try{pstmt.close();}catch(SQLException e){}
		if(rs != null)try{rs.close();}catch(SQLException e){}
	}

	
	//메뉴출력
	public void displayMenu(){
		System.out.println("---------------------------");
		System.out.println("메뉴를 선택하세요");
		System.out.println("1. 새글 작성");
		System.out.println("2. 작성글 수정");
		System.out.println("3. 게시글 삭제");
		System.out.println("4. 게시글 검색");
		System.out.println("5. 게시글 전체 목록 출력");
		System.out.println("6. 종료");
		System.out.println("---------------------------");
		System.out.print("번호 입력>>");
		
	}
	
	//메뉴
	public void start(){
		int num; 
		do{
			displayMenu();
			num = s.nextInt();
			switch(num){
			case 1 : write(); break;
			case 2 : modify(); break;
			case 3 : delete(); break;
			case 4 : search(); break;
			case 5 : viewAll(); break;
			case 6 : System.out.println("작업을 마칩니다."); break;
			default : System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}while(num != 6);
	}
	
	public static void main(String[] args) {
		 boardhw bh= new boardhw();
		 bh.start();
	}

}
