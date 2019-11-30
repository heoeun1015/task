package kr.or.ddit.service;

import java.util.ArrayList;

import kr.or.ddit.vo.BoardVO;

public interface IBoardService {
	/**
	 * DB의 jdbc_board의 전체 결과를 리스트에 담는 메서드
	 * @return BoardVO타입을 담은 리스트
	 */
	public ArrayList<BoardVO> displayBoard();
	
	/**
	 * BoardVO에 담겨진 자료를 DB에 넣는 메서드
	 * @param bv
	 * @return 작업성공 : 1, 실패 : 0
	 */
	public int write(BoardVO bv);
	
	/**
	 * 하나의 BoardVO 자료를 이용해 DB를 업데이트하는 메서드
	 * @param bv
	 * @return 작업성공 : 1, 실패 : 0
	 */
	public int update(BoardVO bv);
	
	/**
	 * 글번호 매개변수로 받아서 그 글을 삭제하는 메서드
	 * @param num
	 * @return 작업성공 : 1, 실패 : 0 
	 */
	public int delete(int num);
	
	/**
	 * 글번호 매개변수로 받아서 그 글의 내용을 보는 메서드
	 * @param num
	 * @return BoardVO타입을 담은 리스트
	 */
	public ArrayList<BoardVO> search(int num);
	
	/**
	 * 주어진 글 번호가 존재하는지 확인하는 메서드
	 * @param num
	 * @return 해당 글이 있으면 true, 없으면 false 반환
	 */
	public boolean getBoard(int num);

}
