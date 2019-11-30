package kr.or.ddit;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.service.IBoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;

/*
	
*/
public class boardMain {
	
	// 인터페이스인 IMemberService를 참조하는
	// service 객체 변수를 선언한다
	private IBoardService service;
	
	private Scanner scan = new Scanner(System.in); 
	
	public boardMain() {
		service = new IBoardServiceImpl();		
	}
	// dao가 하는 역할 삭제하기
	// main은 service만 알면 service가 알아서한다.
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 글 작성");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 글 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateBoard();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
		
	}
	
	//회원정보를 삭제하는 메서드(입력받은 회원ID를 이용하여 삭제)
	private void deleteMember() {
		System.out.println();
		System.out.print("삭제할 게시물 번호를 입력하세요.>>");
		int board_no = scan.nextInt();
		
		int cnt = service.deleteBoard(board_no);
		
		if(cnt > 0) {
			System.out.println(board_no + "번 게시물을 삭제했습니다.");
		}else {
			System.out.println(board_no + "번 게시물 삭제 실패");
		}

	}
	
	//회원정보를 수정하는 메서드
	private void updateBoard() {
		System.out.println();
		int board_no;
		boolean chk = true;//true면 이미 회원이 있고 false면 없음
		
		do {
			System.out.print("수정할 게시판 번호를 입력하세요 >> ");
			board_no = scan.nextInt();
			
			chk = service.getBoard(board_no);
			if(chk == false) {
				System.out.println(board_no + "번호는 없는 번호입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}
		}while(chk == false);
//		scan.nextLine(); //입력버퍼 비우기
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 제목 >> ");
		String board_title = scan.next();
		
		System.out.print("새로운 작성자  >> " );
		String board_writer = scan.next();
		
		scan.nextLine();
		
		System.out.print("새로운 내용 >> ");
		String board_content = scan.nextLine();
		
		BoardVO vo = new BoardVO();
		vo.setBoard_title(board_title);
		vo.setBoard_writer(board_writer);
		vo.setBoard_content(board_content);;
		vo.setBoard_no(board_no);
		
		int cnt = service.updateBoard(vo);
		
		if(cnt > 0) {
			System.out.println(board_no + "번 게시물을 수정했습니다.");
		}else {
			System.out.println(board_no + "번 게시물 수정 실패!");
		}
		
	}

	//전체 회원을 출력하는 메서드
	private void displayMemberAll() {
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println(" 게시판 번호\t제목\t작성자\t날짜\t내용");
		System.out.println("-----------------------------");
		
		
		List<BoardVO> board_List = service.getAllBoardList();
		
		for(BoardVO vo : board_List) {
			System.out.println(vo.getBoard_no() + "\t" + vo.getBoard_title() + "\t" + 
			vo.getBoard_writer() + "\t" + "\t" + vo.getBoard_date() + "\t" + vo.getBoard_content() );
		}
		
		System.out.println("-----------------------------");
		System.out.println("출력작업 끝");
		
	}

	//회원을 추가하는 메서드
	private void insertMember() {
		boolean chk= false;
		
		int board_no;
		
		do {
			System.out.println();
			System.out.println("추가할 게시물을 입력하세요.");
			System.out.print("게시물 번호 >>");
			board_no = scan.nextInt();
			// chk = getMember(memId); 서비스에게 요청해야함
			chk = service.getBoard(board_no);
			if(chk) {
				System.out.println("번호가 " + board_no + "인 게시물은 이미 존재합니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(chk);
		
		System.out.print("제목 >>");
		String board_title = scan.next();

		System.out.print("작성자 >>");
		String board_writer = scan.next();
		
		scan.nextLine(); //입력버퍼 비우기
		
		System.out.print("날짜 >>");
		String board_date = scan.nextLine();
		
		System.out.print("내용 >>");
		String board_content = scan.nextLine();
		
		BoardVO vo = new BoardVO();
		vo.setBoard_no(board_no);
		vo.setBoard_title(board_title);
		vo.setBoard_writer(board_writer);
		vo.setBoard_date(board_date);
		vo.setBoard_content(board_content);
		
		int cnt = service.insertBoard(vo);
			
		if(cnt>0){
			System.out.println(board_no + "번 게시물 추가 성공");
		}else {
			System.out.println(board_no + "번 게시물 추가 작업 실패");
		}


	}

	public static void main(String[] args) {
		boardMain memObj = new boardMain();
		memObj.start();
	}

}






