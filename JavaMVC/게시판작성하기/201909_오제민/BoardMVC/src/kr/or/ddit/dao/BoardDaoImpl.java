package kr.or.ddit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.ddit.util.DBUtil3;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private void disConnect() {//  사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException e){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException e){}
		if(conn!=null)try{ conn.close(); }catch(SQLException e){}
	}
	
	@Override
	public ArrayList<BoardVO> displayBoard() {
		ArrayList<BoardVO> boardList = new ArrayList<>();
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM jdbc_board ORDER BY board_no DESC";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoard_no(rs.getInt("board_no"));
				bv.setBoard_title(rs.getString("board_title"));
				bv.setBoard_writer(rs.getString("board_writer"));
				bv.setBoard_date(rs.getString("board_date"));
				
				boardList.add(bv);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return boardList;
	}

	@Override
	public int write(BoardVO bv) {
		int cnt = 0;
		try {			
			conn = DBUtil3.getConnection();
			String sql = "INSERT INTO jdbc_board VALUES (board_seq.nextval, ?, ?, SYSDATE, ?)";
			
			pstmt = conn.prepareStatement(sql);
								
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int update(BoardVO bv) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "UPDATE jdbc_board SET board_title = ?, board_content = ? WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_content());
			pstmt.setInt(3, bv.getBoard_no());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int delete(int num) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "DELETE jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public ArrayList<BoardVO> search(int num) {
		ArrayList<BoardVO> boardList = new ArrayList<>();
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT * FROM jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoard_no(rs.getInt("board_no"));
				bv.setBoard_title(rs.getString("board_title"));
				bv.setBoard_writer(rs.getString("board_writer"));
				bv.setBoard_date(rs.getString("board_date"));
				bv.setBoard_content(rs.getString("board_content"));
				
				boardList.add(bv);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return boardList;
	}

	@Override
	public boolean getBoard(int num) {
		boolean chk = false;
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
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
		}finally {
			disConnect();
		}
		return chk;
	}

}
