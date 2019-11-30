package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil3;

public class Board {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Board().start();
	}

	private void start() {
		int choice;
		do{
			System.out.println("===================== 게 시 판 ======================");
			System.out.println(" 글번호\t제목\t\t작성자\t작성날짜");
			displayboard(); //게시글 목록
			System.out.println("1.새 글 작성 | 2.게시글 수정 | 3.게시글 삭제 | 4.검색 | 5.나가기");
			System.out.println("---------------------------------------------------");
			System.out.print("원하는 작업 선택 >> ");
			choice = Integer.parseInt(scan.nextLine()); // 메뉴번호 입력받기
			switch(choice){
				
				case 1 :  // 새글 작성
					write();
					break;
				case 2 :  // 게시글 수정
					update();
					break;
				case 3 :  // 게시글 삭제
					delete();
					break;
				case 4 :  // 게시글 내용 검색
					search();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice != 5);
		
	}
	

	// 게시글 목록
	private void displayboard() {
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM jdbc_board ORDER BY board_no DESC";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int boardNo = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				
				System.out.println(boardNo + "\t" + title + "\t\t" + writer + "\t" + date);
			}
			System.out.println("---------------------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	//1. 새글 작성
	private void write() {
		System.out.println();
		System.out.println("새 글을 작성합니다.");
		System.out.print("제목  > ");
		String title = scan.nextLine();
		
		System.out.print("작성자  > ");
		String writer = scan.nextLine();
		
		System.out.println("내용  > ");
		String content = scan.nextLine();
		
		conn = DBUtil3.getConnection();
		
		try {			
			String sql = "INSERT INTO jdbc_board VALUES (board_seq.nextval, ?, ?, SYSDATE, ?)";
			
			pstmt = conn.prepareStatement(sql);
								
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("작성 완료!");
			}else {System.out.println("작성 실패!!");}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	//2. 게시글 수정
	private void update() {
		System.out.println();
		System.out.println("게시글을 수정합니다.");
		System.out.print("수정할 게시글 번호 > ");
		int num = Integer.parseInt(scan.nextLine());
		
		conn = DBUtil3.getConnection();
		String sql = "SELECT COUNT(*) FROM jdbc_board WHERE board_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("COUNT(*)");
			}
			if(cnt == 0) {
				System.out.println("해당 게시글이 없습니다."); return;
			}
			System.out.print("제목을 수정해주세요. > ");
			String title = scan.nextLine();
			System.out.println("내용을 수정해주세요.");
			String content = scan.nextLine();
			sql = "UPDATE jdbc_board SET board_title = ?, board_content = ? WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.println("수정 완료!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	//3. 게시글 삭제
	private void delete() {
		System.out.println();
		System.out.println("게시글을 삭제합니다.");
		System.out.print("삭제할 게시글 번호 > ");
		int num = Integer.parseInt(scan.nextLine());
		
		conn = DBUtil3.getConnection();
		String sql = "SELECT COUNT(*) FROM jdbc_board WHERE board_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("COUNT(*)");
			}
			if(cnt == 0) {
				System.out.println("해당 게시글이 없습니다."); return;
			}
			
			sql = "DELETE jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			int count = pstmt.executeUpdate();
			if(count > 0) {
				System.out.println("삭제 완료!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
	
	//4. 게시글 내용 검색
	private void search() {
		System.out.println();
		System.out.println("게시글을 확인합니다.");
		System.out.print("게시글 번호를 선택해주세요. > ");
		int num = Integer.parseInt(scan.nextLine());
		
		conn = DBUtil3.getConnection();
		String sql = "SELECT COUNT(*) FROM jdbc_board WHERE board_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("COUNT(*)");
			}
			if(cnt == 0) {
				System.out.println("해당 게시글이 없습니다."); return;
			}
			
			sql = "SELECT * FROM jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int boardNo = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				
				System.out.println("===================================================");
				System.out.println(boardNo + "\t" + title + "\t\t" + writer + "\t" + date);
				System.out.println("내용------------------------------------------------");
				System.out.println(content);
				System.out.println("===================================================");
			}
			
			System.out.println("뒤로가기 (enter)");
			scan.nextLine();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}
	
	private void disConnect() {//  사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException e){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
		if(conn!=null)try{ conn.close(); }catch(SQLException e){}
		
	}
}
