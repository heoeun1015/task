import java.sql.Date;

public class board_DTO {
	int board_no; // 번호(자동증가)
	String board_title; // 제목
	String board_writer; // 작성자
	String board_date; // 작성날짜
	String board_content; // 내용
		
	public board_DTO(int board_no, String board_title, String board_writer, String board_date, String board_content) {
		super();	
		this.board_no = board_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_date = board_date;
		this.board_content = board_content;
	}
	
//	public int getBoard_no() {
//		return board_no;
//	}
//	public void setBoard_no(int board_no) {
//		this.board_no = board_no;
//	}
	
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	
}


