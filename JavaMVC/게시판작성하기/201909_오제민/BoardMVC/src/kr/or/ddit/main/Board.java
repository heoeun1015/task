package kr.or.ddit.main;

import java.util.ArrayList;
import java.util.Scanner;

import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

public class Board {
	
	private IBoardService boardService;
	
	public Board() {
		boardService = new BoardServiceImpl();
	}
	
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new Board().start();
	}

	private void start() {
		int choice;
		do{
			System.out.println("===================== 게 시 판 ======================");
			System.out.println(" 글번호\t제목\t\t작성자\t작성날짜");
			displayboard(); //게시글 목록
			System.out.println("---------------------------------------------------");
			System.out.println("1.새 글 작성 | 2.게시글 수정 | 3.게시글 삭제 | 4.검색 | 5.나가기");
			System.out.println("---------------------------------------------------");
			System.out.print("원하는 작업 선택 >> ");
			choice = Integer.parseInt(scan.nextLine()); // 메뉴번호 입력받기
			switch(choice){
				
				case 1 :  // 새글 작성
					write();
					break;
				case 2 :  // 게시글 수정
					update();
					break;
				case 3 :  // 게시글 삭제
					delete();
					break;
				case 4 :  // 게시글 내용 검색
					search();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice != 5);
		
	}
	

	// 게시글 목록
	private void displayboard() {
		
		ArrayList<BoardVO> boardList = boardService.displayBoard();
		for(BoardVO bv : boardList) {
			System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() + "\t\t"
						+ bv.getBoard_writer() + "\t" + bv.getBoard_date());
		}
	}
	
	//1. 새글 작성
	private void write() {
		System.out.println();
		System.out.println("새 글을 작성합니다.");
		System.out.print("제목  > ");
		String title = scan.nextLine();
		
		System.out.print("작성자  > ");
		String writer = scan.nextLine();
		
		System.out.println("내용  > ");
		String content = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(content);
		
		int cnt = boardService.write(bv);
		
		if(cnt > 0) {
			System.out.println("작성 완료!");
		}else {System.out.println("작성 실패!!");}
	}
	
	//2. 게시글 수정
	private void update() {
		boolean chk = true;
		
		System.out.println();
		System.out.println("게시글을 수정합니다.");
		System.out.print("수정할 게시글 번호 > ");
		int num = Integer.parseInt(scan.nextLine());
		
		chk = boardService.getBoard(num);
		if(!chk) {
			System.out.println("해당 게시글이 없습니다."); return;
		}
		
		System.out.print("제목을 수정해주세요. > ");
		String title = scan.nextLine();
		System.out.println("내용을 수정해주세요.");
		String content = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_no(num);
		bv.setBoard_title(title);
		bv.setBoard_content(content);
		
		int cnt = boardService.update(bv);
		if(cnt > 0) {
			System.out.println("수정 완료!!!");
		}
		
	}

	//3. 게시글 삭제
	private void delete() {
		boolean chk = true;
		
		System.out.println();
		System.out.println("게시글을 삭제합니다.");
		System.out.print("삭제할 게시글 번호 > ");
		int num = Integer.parseInt(scan.nextLine());
		chk = boardService.getBoard(num);
		if(!chk) {
			System.out.println("해당 게시글이 없습니다."); return;
		}

		int cnt = boardService.delete(num);
		if(cnt > 0) {
			System.out.println("삭제 완료!!!");
		}
	}
	
	//4. 게시글 내용 검색
	private void search() {
		boolean chk = true;
		
		System.out.println();
		System.out.println("게시글을 확인합니다.");
		System.out.print("게시글 번호를 선택해주세요. > ");
		int num = Integer.parseInt(scan.nextLine());
		
		chk = boardService.getBoard(num);
		if(!chk) {
			System.out.println("해당 게시글이 없습니다."); return;
		}
		ArrayList<BoardVO> boardList = boardService.search(num);
		for(BoardVO bv : boardList) {
			System.out.println("===================================================");
			System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() + "\t\t" 
					+ bv.getBoard_writer() + "\t" + bv.getBoard_date());
			System.out.println("내용------------------------------------------------");
			System.out.println(bv.getBoard_content());
			System.out.println("===================================================");
			System.out.println("뒤로가기 (enter)");
			scan.nextLine();
			
		}
		
		
	}
	
	
}
