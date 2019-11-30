package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.util.DBUtil;

public class Nboard {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	private static final Logger sqlLogger = Logger.getLogger("log4jexam.sql.query");
	private static final Logger paramLogger = Logger.getLogger("log4jexam.sql.Parameter");
	private static final Logger resultLogger = Logger.getLogger(Nboard.class);
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새 글 작성");
		System.out.println("  2. 게시글 삭제");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 전체 게시글 출력");
		System.out.println("  5. 게시글 제목 검색");
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
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 새 글 작성
					newPost();
					break;
				case 2 :  // 게시글 삭제
					deletePost();
					break;
				case 3 :  // 게시글 수정
					updatePost();
					break;
				case 4 :  // 전체 게시글 출력
					displayPostAll();
					break;
				case 5 :  // 게시글 제목 검색
					searchPost();
					break;
				case 6 :
					System.out.println("프로그램을 종료합니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요");
			}
		}while(choice!=6);
	}
	/**
	 * 새 게시글 작성
	 */
	private void newPost() {
		System.out.print("제목 >> ");
		String pTitle = scan.next();
		
		System.out.print("작성자 >> ");
		String pWriter = scan.next();
		
		System.out.println("내용 >> ");
		String pContents = scan.next();
		
		scan.nextLine(); 	// 입력버퍼 비우기
		
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO jdbc_board (board_no, board_title, board_writer, board_date, board_content)"
					+ " values (board_seq.nextVal, ?, ?, SYSDATE, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pTitle);
			pstmt.setString(2, pWriter);
			pstmt.setString(3, pContents);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글 작성이 완료되었습니다.");
			} else {
				System.out.println("게시글 작성에 실패하였습니다.");
			}
		} catch (SQLException e) {
			System.out.println("게시글 작성에 실패하였습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	/**
	 * 게시글 검색 메서드
	 */
	private void searchPost() {
		System.out.print("제목 >> ");
		String searchTit = scan.next().trim();
		
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		System.out.println(" 번호\t제 목\t작성자\t작성날짜\t\t\t내용");
		System.out.println("-------------------------------------------------------------------");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM jdbc_board WHERE board_title Like '%" + searchTit + "%' ORDER BY board_no";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int pNum = rs.getInt("board_no");
				String pTitle = rs.getString("board_title");
				String pWriter = rs.getString("board_writer");
				String pDate = rs.getString("board_date");
				String pContent = rs.getString("board_content");
				
				System.out.println(pNum + "\t" + pTitle + "\t" + pWriter + "\t" + pDate + "\t" + pContent);
			}
			System.out.println("--------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/**
	 * 게시글 삭제 메서드 
	 */
	private void deletePost() {
		displayPostAll();
		System.out.println();
		int pNum;
		while(true) {
			System.out.print("삭제할 게시글 번호를 선택하세요.");
			pNum = Integer.parseInt(scan.next());
			System.out.println(pNum);
			if(getPost(pNum)) {
				break;
			}
			
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
		
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println("게시글 삭제 성공");
			} else {
				System.out.println("게시글 삭제 실패");
			}
		} catch (SQLException e) {
			System.out.println("게시글 삭제 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void updatePost() {
		System.out.println();
		int pNum;
		
		while(true) {
			System.out.print("수정할 게시글 번호를 선택하세요.");
			pNum = Integer.parseInt(scan.next());
			
			if(getPost(pNum)) {
				break;
			}
			
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
		}
		
		System.out.print("제목 >> ");
		String pTitle = scan.next();
		
		scan.nextLine();
		System.out.println("내용 >> ");
		String pContents = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "UPDATE jdbc_board SET board_title = ?, board_date = SYSDATE, board_content = ? WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pTitle);
			pstmt.setString(2, pContents);
			pstmt.setInt(3, pNum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("게시글의 정보를 수정했습니다.");
			} else {
				System.out.println("게시글 정보 수정 실패");
			}
		} catch (SQLException e) {
			System.out.println("게시글 정보 수정 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/**
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayPostAll() {
		System.out.println();
		System.out.println("-------------------------------------------------------------------");
		System.out.println(" 번호\t제 목\t작성자\t작성날짜\t\t\t내용");
		System.out.println("-------------------------------------------------------------------");
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM jdbc_board ORDER BY board_no";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int pNum = rs.getInt("board_no");
				String pTitle = rs.getString("board_title");
				String pWriter = rs.getString("board_writer");
				String pDate = rs.getString("board_date");
				String pContent = rs.getString("board_content");
				
				System.out.println(pNum + "\t" + pTitle + "\t" + pWriter + "\t" + pDate + "\t" + pContent);
			}
			System.out.println("--------------------------------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void disConnect() {
		if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
		if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
		if(conn != null) try { conn.close(); } catch(SQLException e) {}
		if(rs != null) try { rs.close(); } catch(SQLException e) {}
	}

	/**
	 * 게시글 번호를 이용하여 게시글이 존재하는지 확인하는 메서드
	 */
	private boolean getPost(int bNum) {
		boolean chk = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) cnt from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return chk;
	}

	public static void main(String[] args) {
		Nboard postObj = new Nboard();
		postObj.start();
	}

}






