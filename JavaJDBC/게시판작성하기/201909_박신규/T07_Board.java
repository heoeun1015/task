package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

/*위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 
삭제, 검색 */


public class T07_Board {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner sc;
	ArrayList<Integer> al = new ArrayList<Integer>();
	public T07_Board() {
		sc = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new T07_Board().start();
	}

	public void start() {

		while(true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.새글 작성  2.수정 3.삭제  4.게시판상태 5.검색 6.종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");
			int choice = Integer.parseInt(sc.nextLine());
			if ( choice == 1 ) {
				insertBoard();
			} else if ( choice == 2 ) {
				updateBoard();
			} else if ( choice == 3 ) {
				deleteBoard();
			}else if ( choice == 4 ) {
				statusBoard();
			}else if ( choice == 5 ) {
				searchBoard();
			}else if ( choice == 6 ) {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}
		}
	}
	
	private boolean getMember(int memId) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from jdbc_board where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memId);
			
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
	
	public void updateBoard() {
		System.out.println();
		int boardno = 0;
		boolean chk = true;
		
		do {
			System.out.print("수정할 게시판 번호를 입력하세요 >> ");
			boardno = sc.nextInt();
			
			chk = getMember(boardno);
			if(chk == false) {
				System.out.println(boardno + "회원은 없는 회원입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}
		} while(chk == false);
		sc.nextLine();
		
		try {
			
			System.out.println("수정할 내용을 입력하세요.");
			System.out.print("새로운 제목 :");
			String memName = sc.nextLine();
			
			System.out.print("새로운 내용 : ");
			String memTel = sc.nextLine();
			
			//sc.nextLine();
			
			conn = DBUtil.getConnection();
			
			String sql = "update jdbc_board set board_title = ?, board_content = ? where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setInt(3, boardno);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("회원의 정보를 수정했습니다.");
			}else {
				System.out.println("회원의 정보 수정 실패.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	//검색 아직 못함.월요일날 할것
	public void searchBoard() {
		System.out.println();
		int boardno;
		boolean chk = true;
		
		do {
			System.out.print("검색할 게시판 번호를 입력하세요 >> ");
			boardno = Integer.parseInt(sc.nextLine());
			
			chk = getMember(boardno);
			if(chk == false) {
				System.out.println("없는 회원입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}
		} while(chk == false);

		try {
			conn = DBUtil.getConnection(); //접속정보
			stmt = conn.createStatement();
			String sql = "select * from jdbc_board where board_no=" + boardno; //2번문제
			
			rs = stmt.executeQuery(sql); //SQL문이 select일 경우에는
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("============ 쿼리문 실행 결과 ============");
			
			while(rs.next()) {
				System.out.println("번      호 : " + rs.getInt("board_no"));
				System.out.println("제      목 : " + rs.getString("board_title"));
				System.out.println("작 성  자 : " + rs.getString("board_writer"));
				System.out.println("작성일자 : " + rs.getString("board_date"));
				System.out.println("내      용 : " + rs.getString("board_content"));
				System.out.println("-----------------------------------");
			}
			System.out.println("출력 끝...");
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			disConnect();
		}
	
	}

	public void insertBoard() {
		try {
			int room = 0;
			conn = DBUtil.getConnection(); //접속정보

			stmt = conn.createStatement();
//			String sql = "select * from jdbc_board"; //실행할 SQL문 최대값을 구하기 위해 반드시 order by를 입력한다.
//			rs = stmt.executeQuery(sql); //SQL문이 select일 경우.
			
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("제목을 입력하세요 : ");
			String title = sc.nextLine();
			
			System.out.print("작성자를 입력하세요 : ");
			String writer = sc.nextLine();
			
			System.out.print("내용을 입력하세요 : ");
			String contents = sc.nextLine();
			
			conn = DBUtil.getConnection();
			
			//String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) values (seq_board.nextval,?,?,sysdate,?)";
			String sql = "insert into jdbc_board values (seq_board.nextval,?,?,sysdate,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, contents);
			
			int cnt = pstmt.executeUpdate();

			if(cnt > 0) {
				System.out.println(room + "번 방이 예약되었습니다.");
			} else {
				System.out.println(room + "번 방 예약을 실패하였습니다.");
			}
		} catch(SQLException e) {
			System.out.println("회원 추가 작업 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	public void deleteBoard() {
		System.out.println();
		System.out.print("삭제할 게시물 번호를 입력하세요. ");
		int num = Integer.parseInt(sc.nextLine());
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0){
				System.out.println(num + "회원 삭제 성공");
			} else {
				System.out.println(num + "회원 삭제 실패");
			}
		} catch(SQLException e) {
			System.out.println(num + "회원 삭제 실패");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	public void statusBoard() {
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("번호\t제목\t\t작성자\t날짜\t\t\t내용");
			while(rs.next()) {
				int num = rs.getInt("board_no");
				String title = rs.getString("board_title");
				String writer = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String content = rs.getString("board_content");
				
				System.out.println(num + "\t" + title + "\t" + writer + "\t" + date + "\t" + content);
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void disConnect() {
		// 종료(사용했던 자원을 모두 반납한다.)
		//scan.close();
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(conn != null) try {conn.close();} catch(SQLException e) {}
		if(rs != null) try {rs.close();} catch(SQLException e) {}
	}
}

//AES128