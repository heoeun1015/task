package kr.or.ddit.board.dao;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	
	public int insertBoard(BoardVO vo); // 삽입
	
	public List<BoardVO> getsearchBoard(BoardVO vo); // 검색
	
	public boolean getBoard(int board_no); // 게시물이 존재하는지
	
	public List<BoardVO> getAllBoardList(); //전체출력
	
	public int updateBoard(BoardVO vo);
	
	public int deleteBoard(int board_no);
		
}
