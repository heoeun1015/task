package kr.or.ddit.basic;
/*
	위의 테이블을 작성하고 게시판을 관리하는
	다음 기능들을 구현하시오.
	
	기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
	 
	---------------------------------------------------------
	
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class T08h_게시판작성하기 {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	

	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글작성");
		System.out.println("  2. 삭제");
		System.out.println("  3. 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 검색");
		System.out.println("  0. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	
	//프로그램 시작메서드
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 새글작성
					insertBoard();
					break;
				case 2 :  // 삭제
					deleteBoard();
					break;
				case 3 :  // 수정
					updateBoard();
					break;
				case 4 :  // 전체 자료 출력
					displayBoardAll();
					break;
				case 5 :  // 검색
					searchBoard();
					break;
				case 0 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=0);
	}
	
	
	// 새글작성
	private void insertBoard() {
		boolean chk = false;

		System.out.print("제목>> ");
		String boardTitle = scan.next();
		System.out.print("작성자>> ");
		String boardWriter = scan.next();

		scan.nextLine(); // 입력버퍼 비우기

		System.out.print("내용>> ");
		String boardContent = scan.nextLine();

		try {
			conn = DBUtil.getConnection();

			String sql = "INSERT INTO jdbc_board (board_no, board_title, board_writer, board_date, board_content) VALUES(board_seq.nextval,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("게시글 추가 작업 성공");
			} else {
				System.out.println("게시글 추가 작업 실패!");
			}

		} catch (SQLException e) {
			System.out.println(boardTitle + "게시글 추가 작업 실패!");
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}
	
	
	// 삭제
	private void deleteBoard() {
		String boardNo = "";
		boolean chk = true;

		do {
			System.out.println();
			System.out.print("삭제할 번호를 입력하세요>>");
			boardNo = scan.next();

			chk = getBoard(boardNo);
			if (!chk) {
				System.out.println(boardNo + "번 글은 없은 게시글입니다.");
				System.out.println("삭제할 자료가 없으니 다시 입력하세요.");
			}

		} while (!chk);
		
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println(boardNo + "번 글 삭제 성공..");
			} else {
				System.out.println(boardNo + "번 글 삭제 실패!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	
	// 수정
	private void updateBoard() {
		String boardNo = "";
		boolean chk = true;

		do {
			System.out.println();
			System.out.print("수정할 게시글의 번호를 입력하세요>>");
			boardNo = scan.next();

			chk = getBoard(boardNo);
			if (!chk) {
				System.out.println(boardNo + "번 글은 없은 게시글입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}

		} while (!chk);

		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("제목>> ");
		String boardTitle = scan.next();
		System.out.print("작성자>> ");
		String boardWriter = scan.next();
		scan.nextLine(); // 입력버퍼 비우기
		System.out.print("내용>> ");
		String boardContent = scan.nextLine();

		try {
			conn = DBUtil.getConnection();

			String sql = "UPDATE jdbc_board SET board_title = ?, board_writer = ?, board_date = sysdate, board_content = ? WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);
			pstmt.setString(4, boardNo);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(boardNo + "번 게시글 정보를 수정했습니다.");
			} else {
				System.out.println(boardNo + "번 게시글의 정보 수정 실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}


	// 전체 자료 출력
	private void displayBoardAll() {
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println(" 번    호\t제    목\t작 성 자\t작성날짜\t\t\t내    용");
		System.out.println("---------------------------------------------------------");

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT * FROM jdbc_board";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");

				System.out.println(
						boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardDate + "\t" + boardContent);
			}
			System.out.println("---------------------------------------------------------");
			System.out.println("출력작업 끝...");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	
	// 검색
	private void searchBoard() {
		System.out.println();

		String boardNo = "";
		boolean chk = true;

		do {
			System.out.print("검색할 게시글의 번호를 입력하세요>>");
			boardNo = scan.next();

			chk = getBoard(boardNo);
			if (chk == false) {
				System.out.println(boardNo + "번 글은 없은 게시글입니다.");
				System.out.println("검색할 자료가 없으니 다시 입력하세요.");
			}

		} while (chk == false);

		System.out.println("---------------------------------------------------------");
		System.out.println(" 번    호\t제    목\t작 성 자\t작성날짜\t\t\t내    용");
		System.out.println("---------------------------------------------------------");
		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT * FROM jdbc_board WHERE board_no = " + boardNo;

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");
				String boardContent = rs.getString("board_content");

				System.out.println(
						boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardDate + "\t" + boardContent);
				System.out.println("---------------------------------------------------------");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}
	
	
	//게시글이 있는지 확인하는 메서드
	private boolean getBoard(String boardNo) {
		boolean chk = false;

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

			if (cnt > 0) {
				chk = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}

		return chk;
	}
	
	

	
	// 자원반납
	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException e){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException e){}
		if(conn!=null)try{ conn.close(); }catch(SQLException e){}
	}
	
	
	public static void main(String[] args) {
		T08h_게시판작성하기 boardObj = new T08h_게시판작성하기();
		boardObj.start();
	}
	
	
}
