package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.dao.IBoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO;

public class IBoardServiceImpl implements IBoardService {

	private IBoardDao boardDao;
//	private IBoardServiceImpl boardDao;
	public IBoardServiceImpl() {
		// TODO Auto-generated constructor stub
		boardDao = new IBoardDaoImpl(); 
	}
			
	@Override
	public int insertBoard(BoardVO VO) {
		// TODO Auto-generated method stub
		return boardDao.insertBoard(VO);
	}
	
	@Override
	public List<BoardVO> getsearchBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDao.getsearchBoard(vo);
	}
	
	@Override
	public List<BoardVO> getAllBoardList() {
		// TODO Auto-generated method stub
		return boardDao.getAllBoardList();
	}
	
	@Override
	public int updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return boardDao.updateBoard(vo);
	}
	
	@Override
	public int deleteBoard(int board_no) {
		// TODO Auto-generated method stub
		return boardDao.deleteBoard(board_no);
	}
	
	@Override
	public boolean getBoard(int board_no) {
		// TODO Auto-generated method stub
		return boardDao.getBoard(board_no);
	}
}
	

	
