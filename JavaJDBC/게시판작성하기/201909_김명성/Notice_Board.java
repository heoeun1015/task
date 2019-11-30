package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

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
public class Notice_Board {
	private String num = null;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt, pstmt2;
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
		System.out.println("  4. 게시글 검색");
		System.out.println("  5. 전체 목록 출력");
		System.out.println("  6. 작업 끝.");
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
					writePost();
					break;
				case 2 :  // 게시글 수정
					editPost();
					break;
				case 3 :  // 게시글 삭제
					deletePost();
					break;
				case 4 :  // 게시글 검색
					searchPost();
					break;
				case 5 :  // 전체 자료 출력
					displayPost();
					break;
				case 6 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}
	
	/**
	 * 게시글 삭제
	 */
	private void deletePost() {
		displayPost();
		System.out.print("수정할 게시글 번호를 입력하세요 >> ");
		scan.nextLine();
		num = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("선택한 게시글이 삭제되었습니다!!!");
			}else {
				System.out.println("삭제 실패하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	/**
	 * 게시글 수정
	 */
	private void editPost() {
		displayPost();
		System.out.print("수정할 게시글 번호를 입력하세요 >> ");
		scan.nextLine();
		num = scan.nextLine();
		System.out.println();
		System.out.println("1. 제   목 \t 2.내   용");
		System.out.print("수정할 목록을 선택해 주세요 >> ");
		String col = scan.nextLine();
		
		System.out.println();

		System.out.println("col : " + col);
		System.out.println("수정할 내용을 입력해 주세요");
		String content = scan.nextLine();
		
		try {
			String sql = "";
			conn = DBUtil.getConnection();
			if(col.equals("1")) {
				sql = "update jdbc_board set board_title = ? , board_date = to_char(sysdate, 'YY/MM/DD') where board_no = ? "; //
			}else if(col.equals("2")) {
				sql = "update jdbc_board set board_content = ? , board_date = to_char(sysdate, 'YY/MM/DD') where board_no = ? "; //
			}
			
			pstmt = conn.prepareStatement(sql);
			
			//pstmt.setString(1, col); // 수정할 컬럼 //물어보기
			
			pstmt.setString(1, content); // 수정 할 내용
			
			pstmt.setString(2, num); // 수정 할 게시글 번호
			
			int cnt = pstmt.executeUpdate(); // 성공하면 1, 아니면 0 리턴
			System.out.println("cnt : " + cnt);
			if(cnt > 0) {
				System.out.println("수정이 완료되었습니다!!!");
			}else {
				System.out.println("수정 작업간 오류가 발생하였습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	
	/**
	 * 게시글 검색
	 */
	private void searchPost() {
		
		displayPost();
		scan.nextLine();  // 버퍼를 날려버리기 위해 사용
		//scan.next()는 space나 enter를 구분자로 생각하여 입력버퍼가 남아있음
		// 그래서 next() 후에는 nextLine()으로 입력버퍼를 날려주어야 한다!!!!	
		System.out.println();
		System.out.print("검색할 게시글의 번호를 입력하세요 >>");
		num = scan.nextLine();
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("번   호\t제   목\t작성자\t작성날짜\t\t내   용");
		System.out.println("--------------------------------------------------");;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select board_no, board_title, board_writer, to_char(board_date, 'YY/MM/DD') ,"
						  + "board_content from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			rs = pstmt.executeQuery();  // 결과 값이 있으면 true, 없으면 false반환
			if(!rs.next()) {
				System.out.println("선택한 자료가 없습니다.");
			}
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				num = rs.getString(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String date = rs.getString(4);
				String content = rs.getString(5);
				
				
				System.out.println(num + "\t" + title + "\t" + writer + "\t" + date + "\t" + content);
			}
			System.out.println("--------------------------------------------------");		
			System.out.println("선택 자료 출력 완료!!");

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}

	/**
	 * 게시글 전체 자료 출력
	 */
	private void displayPost() {
		System.out.println();
		System.out.println("--------------------------------------------------");
		System.out.println("번   호\t제   목\t작성자\t작성날짜\t\t내   용");
		System.out.println("--------------------------------------------------");		
		try {
			conn = DBUtil.getConnection();
			String sql = "select board_no, board_title, board_writer, to_char(board_date, 'YY/MM/DD') ,board_content from jdbc_board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String num = rs.getString(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String date = rs.getString(4);
				String content = rs.getString(5);
				
				System.out.println(num + "\t" + title + "\t" + writer + "\t" + date + "\t" + content);
			}
			System.out.println("--------------------------------------------------");		
			System.out.println("전체 자료 출력 완료!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}

	/**
	 * 게시글 작성하는 메서드
	 */
	private void writePost() {
		System.out.print("제목 입력 >> ");
		String title = scan.next();
		
		System.out.print("작성자 입력 >> ");
		String writer = scan.next();
		
		/*System.out.print("작성날짜 입력 >> ");
		String date = scan.next();*/
		
		System.out.print("내용 입력 >> ");
		String content = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content)"
					+ " values(board_seq.nextval,?,?,sysdate,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate(); // 쿼리문 성공하면 1 실패하면 0 반환
			System.out.println(cnt);
			if(cnt > 0) {
				System.out.println("게시글 입력 완료!!!");
			}else {
				System.out.println("게시글 입력 실패...");
			}
			
			
		} catch (SQLException e) {
			System.out.println("게시글 입력 실패2");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	
	/**
	 * 예외처리하는 메서드
	 */
	private void disConnect() {
		if(pstmt !=null) try {rs.close();}catch(SQLException e) {}
		if(rs!=null) try {rs.close();}catch(SQLException e) {}
		if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
		if(conn!=null) try {conn.close();}catch(SQLException e) {}

	}

	public static void main(String[] args) {
		Notice_Board memObj = new Notice_Board();
		memObj.start();
	}

}






