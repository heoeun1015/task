package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
public class JDBC_Board {
	
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
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 글 검색");
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
			scan.nextLine();
			switch(choice){
				case 1 :  // 새글 작성
					insertBoard();
					break;
				case 2 :  // 글 삭제
					deleteBoard();
					break;
				case 3 :  // 글 수정
					updateBoard();
					break;
				case 4 :  // 전체 목록 출력
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
	
	/**
	 * 회원정보를 검색하는 메서드
	 */
	private void searchBoard() {
		System.out.print("검색할 게시판 제목이나 내용을 입력하세요. >> ");
		String search = scan.nextLine();
		
		try {
			conn = DBUtil3.getConenction();
			
			String sql = "SELECT * FROM jdbc_board WHERE board_title LIKE '%' || ? || '%' OR board_content LIKE '%' || ? || '%'";

			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			
			rs = pstmt.executeQuery();

			System.out.println("-----------------------------");
			System.out.println(" 번호\t제목\t작성자\t작성날짜\t내용");
			System.out.println("-----------------------------");
			
			while(rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				

				System.out.println(boardNo + "\t" 
									+ boardTitle + "\t"
									+ boardWriter + "\t"
									+ boardDate + "\t"
									+ boardContent
									);
			}
			System.out.println("-----------------------------");
			System.out.println("출력작업 끝");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/**
	 * 회원정보를 삭제하는 메서드(입력받은 회원ID를 이용하여 삭제한다.)
	 */
	private void deleteBoard() {
		System.out.println();
		System.out.print("삭제할 게시판 번호를 입력하세요. >> ");
		String boardNo = scan.next();
		
		try {
			conn = DBUtil3.getConenction();
			String sql = "delete from jdbc_board where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(boardNo + "번 글 삭제 성공");
			} else {
				System.out.println(boardNo + "번 글 삭제 실패!!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	/**
	 * 회원정보를 수정하는 메서드
	 */
	private void updateBoard() {
		System.out.println();
		String boardNo = "";
		boolean chk = true; 
		
		do {
			System.out.println("수정할 게시판 번호를 입력하세요 >> ");
			boardNo = scan.next();
			
			chk = getBoard(boardNo); 
			if(chk == false) { 
				System.out.println(boardNo + "번 글은 없는 게시글입니다.");
				System.out.println("수정할 게시글이 없으니 다시 입력하세요.");
			}
			
		} while(chk == false);
		
		// true면 빠져나옴. 있는 데이터 수정
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 게시글 제목>> ");
		String boardTitle = scan.next();
		
		System.out.print("새로운 게시글 내용 >> ");
		String boardContent = scan.next();
		scan.nextLine();
		
		try {
			conn = DBUtil3.getConenction();
			
			String sql = "update jdbc_board "
						+ "set board_title = ? "
						+ "	 , board_content = ? "
						+ "where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setString(3, boardNo);
			
			int cnt = pstmt.executeUpdate(); // 성공하면 성공횟수 반환, 실패하면 0 반환
			
			if(cnt > 0) {
				System.out.println(boardTitle + "번 게시글을 수정했습니다.");
			} else {
				System.out.println(boardTitle + "번 게시글 수정 실패!!!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/**
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayBoardAll() {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println(" 번호\t제목\t작성자\t작성날짜\t내용");
		System.out.println("-----------------------------");
		
		try {
			conn = DBUtil3.getConenction();
			
			String sql = "select * from jdbc_board order by board_no";
			
			stmt= conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");
				
				System.out.println(boardNo + "\t" 
									+ boardTitle + "\t"
									+ boardWriter + "\t"
									+ boardDate + "\t"
									+ boardContent
									);
			}
			System.out.println("-----------------------------");
			System.out.println("출력작업 끝");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/**
	 * 새 게시글을 추가하는 메서드
	 */
	private void insertBoard() {
		boolean chk = false;
		
		System.out.print("제목 >> ");
		String boardTitle = scan.nextLine();
		
		System.out.print("작성자 >> ");
		String boardWriter = scan.nextLine();
		
		System.out.print("내용 >> ");
		String boardContent = scan.nextLine();
		
		try {
			conn = DBUtil3.getConenction();
			
			String sql = "insert into jdbc_board "
						+ "(board_no, board_title, board_writer, board_date, board_content) "
						+ " values (board_seq.nextval, ?, ?, sysdate, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
		
			int cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}


	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	/**
	 * 회원ID를 이용하여 회원이 있는지 알려주는 메서드
	 */
	private boolean getBoard(String boardNo) {
		boolean chk = false;
		
		try {
			conn = DBUtil3.getConenction();
			String sql = "select count(*) cnt from jdbc_board "
						+ "where board_no = ? ";
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
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return chk;
	}

	public static void main(String[] args) {
		JDBC_Board board = new JDBC_Board();
		board.start();
	}

}
