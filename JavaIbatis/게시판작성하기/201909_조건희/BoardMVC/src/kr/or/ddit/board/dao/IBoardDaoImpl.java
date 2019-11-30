package kr.or.ddit.board.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.DBUtil;

public class IBoardDaoImpl implements IBoardDao{

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 자원 반납
	private void disConnect() {
		if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
		if(stmt != null) try { stmt.close(); } catch(SQLException e) {}
		if(conn != null) try { conn.close(); } catch(SQLException e) {}
		if(rs != null) try { rs.close(); } catch(SQLException e) {}
		
	}
	
	@Override
	public int insertBoard(BoardVO vo) {
		int cnt = 0;
		
		// TODO Auto-generated method stub
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setInt(1, vo.getBoard_no());
			pstmt.setString(2, vo.getBoard_title());
			pstmt.setString(3, vo.getBoard_writer());
			pstmt.setString(4, vo.getBoard_date());
			pstmt.setString(5, vo.getBoard_content());
			
			cnt  = pstmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	
	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> board_list = new ArrayList<BoardVO>(); 
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoard_no(rs.getInt("board_no"));
				vo.setBoard_title(rs.getString("board_title"));
				vo.setBoard_writer(rs.getString("board_writer"));
				vo.setBoard_date(rs.getString("board_date"));
				vo.setBoard_content(rs.getString("board_content"));
				
				board_list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return board_list;
	}

	@Override
	public int updateBoard(BoardVO vo) {
		int cnt = 0;
		System.out.println("test");
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_writer = ?, "
					+ " board_date = sysdate, board_content = ? where board_no = ?  ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBoard_title());
			pstmt.setString(2, vo.getBoard_writer());			
//			pstmt.setString(3, "board_date");
			pstmt.setString(3, vo.getBoard_content());
			pstmt.setInt(4, vo.getBoard_no());
			
			cnt = pstmt.executeUpdate();				
		} catch(SQLException e) {
			e.printStackTrace();			
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from jdbc_board where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
			cnt = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}		
		return cnt;
	}

	@Override
	public boolean getBoard(int board_no) {
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			
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

	@Override
	public List<BoardVO> getsearchBoard(BoardVO VO) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

