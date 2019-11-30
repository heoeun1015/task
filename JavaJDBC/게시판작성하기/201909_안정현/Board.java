package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class Board {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Scanner scan;

	Board() {
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new Board().display();
	}

	public void display() {
		int n;
		do {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.전체목록 출력  2.새글작성 3.수정 4.삭제 5.검색 6.종료");
			System.out.println("*******************************************");
			System.out.println("메뉴선택 =>");
			n = scan.nextInt();
			switch (n) {
			case 1:
				displayAll();
				break;
			case 2:
				newWrite();
				break;
			case 3:
				modifyWrite();
				break;
			case 4:
				deleteWrite();
				break;
			case 5:
				search();
				break;
			case 6:
				System.out.println("종료되었습니다.");
				return;
			default:
				System.out.println("다시입력해주세요");
			}
		} while (n != 6);
	}

	/*
	 * 글 수정 메서드
	 */
	private void modifyWrite() {
		System.out.println();
		System.out.println("수정할 게시물 번호를 입력해주세요");
		int boardno = scan.nextInt();
		System.out.println("제목을 입력해 주세요");
		scan.nextLine();
		String title = scan.nextLine();
		System.out.println("작성자를 입력해 주세요");
		String writer = scan.nextLine();
		System.out.println("내용을 입력해 주세요");
		String content = scan.nextLine();
		System.out.println("-----------------------------------------------");
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE jdbc_board SET "
					     + "board_title = ?, "
					     + "board_writer = ?, "
					     + "board_content = ? "
					     + "WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			pstmt.setInt(4, boardno);
			
			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("글 수정 성공");
			} else {
				System.out.println("글 수정 실패");
			}

		} catch (SQLException e) {
			System.out.println("글 수정 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/*
	 * 글 검색 메서드
	 */
	private void search() {
		System.out.println();
		System.out.println("검색할 글 번호를 입력해주세요");
		int title = scan.nextInt();

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, title);

			rs = pstmt.executeQuery();

			System.out.println("-----------------------------------------------");

			while (rs.next()) {
				System.out.println("번호 : " + rs.getInt("board_no"));
				System.out.println("제목 : " + rs.getString("board_title"));
				System.out.println("작성자 : " + rs.getString("board_writer"));
				System.out.println("작성일자 : " + rs.getDate("board_date"));
				System.out.println("내용 : " + rs.getClob("board_content"));
			}

			System.out.println("-----------------------------------------------");

		} catch (SQLException e) {
			System.out.println("검색 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/*
	 * 글삭제 메서드
	 */
	private void deleteWrite() {
		System.out.println();
		System.out.println("삭제할 글 번호를 입력해주세요");
		System.out.println("-----------------------------------------------");
		int title = scan.nextInt();
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, title);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("글 삭제 성공");
			} else {
				System.out.println("글 삭제 실패");
			}
		} catch (SQLException e) {
			System.out.println("글 삭제 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void newWrite() {
		System.out.println();
		System.out.println("제목을 입력해 주세요");
		String title = scan.next();
		System.out.println("작성자를 입력해 주세요");
		String writer = scan.next();
		System.out.println("내용을 입력해 주세요");
		String content = scan.next();
		scan.nextLine();
		System.out.println("-----------------------------------------------");
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO jdbc_board (board_no, board_title, board_writer, "
					+ "board_date, board_content) VALUES (board_seq.nextVal, ?, ?, sysdate, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("글 작성 성공");
			} else {
				System.out.println("글 작성 실패");
			}

		} catch (SQLException e) {
			System.out.println("글 작성 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void displayAll() {
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성일자 \t내용");
		System.out.println("-------------------------------------");

		try {
			conn = DBUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM jdbc_board";
			rs = stmt.executeQuery(sql);
			System.out.println("-----------------------------------------------");
			while (rs.next()) {
				System.out.println("번호 : " + rs.getInt("board_no"));
				System.out.println("제목 : " + rs.getString("board_title"));
				System.out.println("작성자 : " + rs.getString("board_writer"));
				System.out.println("작성일자 : " + rs.getDate("board_date"));
				System.out.println("내용 : " + rs.getClob("board_content"));
			}
			System.out.println("-----------------------------------------------");

		} catch (SQLException e) {
			System.out.println("출력 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void disConnect() {
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
	}

}
