package kr.or.ddit.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {

	private SqlMapClient smc;
	
	public BoardDaoImpl() {
		Reader rd;
		
		try {
			//1-1. xml문서 읽어오기
			Charset charset = Charset.forName("UTF-8");//설정파일인코딩
			Resources.setCharset(charset);
			rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			//1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		} catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<BoardVO> displayBoard() {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = smc.queryForList("board.displayBoard");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardList;
	}

	@Override
	public int write(BoardVO bv) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("board.write", bv);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int update(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = smc.update("board.update", bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int delete(int num) {
		int cnt = 0;
		try {
			cnt = smc.delete("board.delete", num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public BoardVO search(int num) {
		BoardVO bv = null;
		try {
			bv = (BoardVO)smc.queryForObject("board.search", num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bv;
	}

	@Override
	public boolean getBoard(int num) {
		boolean chk = false;
		int cnt = 0;
		try {
			cnt = (int)smc.queryForObject("board.getBoard", num);
			if(cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}

}
