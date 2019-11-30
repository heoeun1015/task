package kt.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kt.or.ddit.util.DBUtil;

/*위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 
			삭제, 검색 
 */
public class boardTest {
	
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 게시글 출력");
		System.out.println("  2. 새글 작성");
		System.out.println("  3. 게시글 수정");
		System.out.println("  4. 게시글 삭제");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 작업 끝  ");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	// 프로그램 시작 메서드
	public void start() {
		int choice;
		do {
			displayMenu(); 
			choice = scan.nextInt();
			switch(choice) {
			case 1 :
				displayBoardAll();
			    break;
			case 2 :
				insertBoard();
				break;
			case 3 :
				updateBoard();
				break;
			case 4 : 
				deleteBoard();
				break;
			case 5 :
				searchBoard();
				break;
			case 6 : 
				System.out.println("작업을 마칩니다.");
				break;
			default : 
				System.out.println("번호를 잘못 입력하셨습니다. 다시 입력하세요");
			}
		}while(choice != 6);
	}
	
	// 게시물를 검색하는 메서드
	private void searchBoard() {
		System.out.println();
		System.out.println("검색할 게시물 제목을 입력하세요 : ");
		scan.nextLine(); // 입력버퍼 없애기
		String searchboard = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "SELECT * FROM jdbc_board WHERE board_title LIKE '%' || ? || '%' ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchboard);
			
			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("------------------------------------------------");
			System.out.println(" 번호\t제목\t작성자\t작성날짜\t내용");
			System.out.println("------------------------------------------------");
			
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
			
		}catch(SQLException e) {
			System.out.println("검색한 자료가 하나도 없습니다.");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	
	// 게시물을 삭제하는 메서드
	private void deleteBoard() {
		System.out.println();
		System.out.println("삭제할 게시판 번호를 입력해 주세요 : ");
		String boardNo = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(boardNo + "번 글 삭제 성공 ");
			}else {
				System.out.println(boardNo + "번 글 삭제 실패");
			}
			
		}catch(SQLException e) {
			System.out.println(boardNo + "회원 삭제 실패");
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}

	// 게시물을 수정하는 메서드
	private void updateBoard() {
		 System.out.println();
		 String boardNo = "";
		 boolean chk = true;
		 
		 do {
			 System.out.println("수정할 게시판 번호를 입력해주세요 : ");
			 boardNo = scan.next();
			 
			 chk = getBoard(boardNo);
			 if(chk == false) {
				 System.out.println(boardNo + "번 글은 없는 게시글입니다.");
				 System.out.println("수정할 게시글이 없으니 다시 입력해주세요");
			 }
		 }while(chk == false);
		 
		// true면 빠져나옴. 있는 데이터 수정
		 System.out.println("수정할 내용을 입력해주세요 : " );
		 System.out.println("새로운 게시물 제목 : ");
		 String boardTitle = scan.next();
		 
		 scan.nextLine();
		 System.out.println("새로운 게시물 내용 : ");
		 String boardContent = scan.next();
		 scan.nextLine();
		 
		 try {
			 conn = DBUtil.getConnection();
			 
			 String sql = "update jdbc_board "
						+ "set board_title = ? "
						+ "	 , board_content = ? "
						+ "where board_no = ? ";
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, boardTitle);
			 pstmt.setString(2, boardContent);
			 pstmt.setString(3, boardNo);
			 		 
			 int cnt = pstmt.executeUpdate();
			 
			 if(cnt > 0) {
				 System.out.println(boardTitle + "번 게시글을 수정했습니다.");
			 }else {
				 System.out.println(boardTitle + "번 게시글 수정 실패");
			 }
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }finally {
			 disConnect();
		 }
	}
	
	// 게시물을 추가하는 메서드
	private void insertBoard() {
	boolean chk = false;
	 
	 System.out.println("제목 : ");
	 String boardTitle = scan.next();
	 
	 System.out.println("작성자 : ");
	 String boardWriter = scan.next();
	
	 scan.nextLine(); // 입력버퍼 비우기
	
	 System.out.println("내용 : ");
	 String boardContent = scan.nextLine();
	 
	 try {
		conn = DBUtil.getConnection();
		
		String sql = "INSERT INTO jdbc_board "
				+ " (board_title, board_writer, board_content, board_no, board_date) "
				+ " values (?, ?, ?, board_seq.nextval, SYSDATE) ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardTitle);
		pstmt.setString(2, boardWriter);
		pstmt.setString(3, boardContent);
		
		int cnt = pstmt.executeUpdate();
		
		if(cnt > 0) {
			System.out.println(boardWriter + "님이 작성하신 게시글이 정상등록 되었습니다.");
		}else {
			System.out.println("게시글 등록이 정상적으로 이루어지지 않았습니다.");
		}
		
	 }catch(SQLException e) {
			System.out.println("게시글 등록이 정상적으로 이루어지지 않았습니다.");
			e.printStackTrace();
		}finally {
			disConnect();
		}
	}
	// 게시물 제목을 이용하여 게시물이 존재하는지 알려주는 메서드
	
	private boolean getBoard(String boardNo) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember "
					+ "where mem_id = ?";
			
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
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return chk;
	}

	// 전체 게시물을 출력하는 메서드
	private void displayBoardAll() {
		 System.out.println();
		 System.out.println("--------------------------------------");
		 System.out.println(" 번호\t제목\t작성자\t작성날짜\t내용");
		 System.out.println("--------------------------------------");
		 
		 try {
			 conn = DBUtil.getConnection();
			 
			 String sql = "select * from jdbc_board order by board_no";
			 
			 stmt = conn.createStatement();
			 
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
			System.out.println("------------------------------------------------");
			System.out.println("출력 작업 끝 ");
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }finally {
			 disConnect();
		 }
	}
	
	// 자원반납
	private void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}
	
	public static void main(String[] args) {
		boardTest boardObj = new boardTest();
		boardObj.start();
	}
}
