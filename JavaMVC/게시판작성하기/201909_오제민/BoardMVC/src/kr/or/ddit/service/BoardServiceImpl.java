package kr.or.ddit.service;

import java.util.ArrayList;

import kr.or.ddit.dao.BoardDaoImpl;
import kr.or.ddit.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {

	private IBoardDao boardDao;
	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
	@Override
	public ArrayList<BoardVO> displayBoard() {
		return boardDao.displayBoard();
	}

	@Override
	public int write(BoardVO bv) {
		return boardDao.write(bv);
	}

	@Override
	public int update(BoardVO bv) {
		return boardDao.update(bv);
	}

	@Override
	public int delete(int num) {
		return boardDao.delete(num);
	}

	@Override
	public ArrayList<BoardVO> search(int num) {
		return boardDao.search(num);
	}

	@Override
	public boolean getBoard(int num) {
		return boardDao.getBoard(num);
	}

}
