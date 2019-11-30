import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class board_Dao {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	Scanner sc = new Scanner(System.in);
	
	public ArrayList<board_DTO> all_select() {
		String sql = "SELECT * FROM jdbc_board"
				+ " order by board_no";
		ArrayList<board_DTO> reserve = new ArrayList<board_DTO>();
		try {			
			con = DBUtil.getConnection();			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				int board_no = rs.getInt("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				board_DTO temp = new board_DTO(board_no, board_title, board_writer, board_date, board_content);
				reserve.add(temp);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return reserve;
	}
	
	public void board_insert() {
		
		con = DBUtil.getConnection();
		try {
			String sql = "insert into jdbc_board values(BOARD_SEQ.nextval, ?, ?, to_char(sysdate,'YYYY-MM-DD'), ?)";
			pstmt = con.prepareStatement(sql);			
			System.out.println("제목을 입력하세요. ");
			String board_title = sc.nextLine();
			System.out.println("작성자를 입력하세요. ");
			String board_writer = sc.nextLine();
			System.out.println("작성내용을 입력하세요. ");
			String board_content = sc.nextLine();	
			
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_writer);
			pstmt.setString(3, board_content);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0) {
				System.out.println(board_writer + "님의 등록이 완료되었습니다.");
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	private void disConnect() {
		//  사용했던 자원 반납
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}		
		if(rs!=null)try{ rs.close(); }catch(SQLException e){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException e){}
		if(con!=null)try{ con.close(); }catch(SQLException e){}		
	}

	public void board_update() {			
		try {
			con = DBUtil.getConnection();
			
//			String change_num;
			int change_num;
			int cnt = 0;
			do {
				System.out.println("변경할 번호를 입력하세요");
//				change_num = Integer.parseInt(sc.nextLine());
				change_num = sc.nextInt();
				
				String sql_check = "select count(*) cnt from jdbc_board "
						+ "where board_no = ?";
				pstmt = con.prepareStatement(sql_check);
				pstmt.setInt(1, change_num);
				rs = pstmt.executeQuery();
				
				cnt = 0;				
				if(rs.next()) {
					cnt = rs.getInt("cnt");
				}
				if(cnt == 0) {
					System.out.println("해당하는 번호가 없습니다."); 
				}				
			} while(cnt == 0);
			
			String sql = "update jdbc_board set board_title=?, board_content = ? where board_no = ?";
			pstmt = con.prepareStatement(sql);
			
			System.out.println("게시판 제목을 입력해주세요");			
			String board_title = sc.next();				
			System.out.println("내용을 입력해주세요");
			String board_content = sc.next();

			pstmt.setString(1, board_title);
			pstmt.setString(2, board_content);
			pstmt.setInt(3, change_num);
			
			int r = pstmt.executeUpdate();
			
			if(r > 0 ) {
				System.out.println("수정 완료"); 				
			} else {
				System.out.println("수정 실패");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		finally {
			disConnect();
		}
	}

	public ArrayList<board_DTO> board_search() {
		ArrayList<board_DTO> reserve = new ArrayList<board_DTO>();
		try {
		con = DBUtil.getConnection();
		int board_num;
		int cnt = 0;
		do {
			System.out.println("검색할 번호를 입력하세요");
//			board_num = Integer.parseInt(sc.nextLine());
			board_num = sc.nextInt();
			String sql_check = "select count(*) cnt from jdbc_board "
					+ "where board_no = ?";
			pstmt = con.prepareStatement(sql_check);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			
			cnt = 0;				
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}											
			
			if(cnt == 0) {
				System.out.println("존재하지 않는 번호입니다.");
			}
			//개수가 있으면 빠져나오도록 
		} while(cnt == 0);
		String sql = "SELECT * FROM jdbc_board "
				+ "WHERE board_no = ?";
//		Connection con2 = DBUtil.getConnection();
		pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, board_num);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {				
			int board_no = rs.getInt("board_no");
			String board_title = rs.getString("board_title");
			String board_writer = rs.getString("board_writer");
			String board_date = rs.getString("board_date");
			String board_content = rs.getString("board_content");
			board_DTO temp = new board_DTO(board_no, board_title, board_writer, board_date, board_content);
			reserve.add(temp);
		}
		
	} catch(SQLException e) {
		e.printStackTrace();
	} finally {
		disConnect();
	}
		return reserve;
	
	}

	public void board_delete() {
		con = DBUtil.getConnection();
		String sql = "delete from jdbc_board where board_no = ?";	

		int board_num, cnt;
		do {			
			System.out.println("삭제할 번호를 입력해주세요");		
			board_num = sc.nextInt();
//			board_num = Integer.parseInt(sc.nextLine());	
			cnt = board_numCheck(board_num);

			//cnt가 0보다 크면 그 번호는 이미 존재하는 것
			if(cnt > 0) {
				System.out.println("해당 번호는 존재합니다.");
			} else {
				System.out.println("존재하지 않는 번호입니다.");
			}
		}while(cnt == 0);
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			int r = pstmt.executeUpdate();
			
			System.out.println(board_num + "번 삭제완료"); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	
	// cnt가 0보다 크면 그 번호는 이미 존재하는 것
	public int board_numCheck(int board_num) {
		String sql_check = "select count(*) cnt from jdbc_board "
				+ "where board_no = ?";
		int cnt = 0;
		try {
			pstmt = con.prepareStatement(sql_check);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();	
						
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}													
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}									
		return cnt;		
	}
}
