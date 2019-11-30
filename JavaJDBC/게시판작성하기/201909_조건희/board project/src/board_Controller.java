import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil2;

public class board_Controller {
	
	public static void main(String[] args) {
		Connection con;
		PreparedStatement pstmt;
		ResultSet rs;
		board_Dao board_Dao = new board_Dao();
		while(true) {			
			Scanner sc = new Scanner(System.in);
			System.out.println("-------------------------- ");
			System.out.println(" ~~~~~~ 게시판 프로그램  ~~~~~~~ ");
			System.out.println("-------------------------- ");
			
			con = DBUtil2.getConnection();
			String sql = "SELECT board_no, board_title, board_writer, board_date"
					+ " FROM jdbc_board order by board_no";			
			try {
				pstmt = con.prepareStatement(sql);
				rs  = pstmt.executeQuery();
				
				while(rs.next()) {
					int board_no = rs.getInt("board_no");
					String board_title = rs.getString("board_title");
					String board_writer = rs.getString("board_writer");
					String board_date = rs.getString("board_date");
					
					System.out.println("board_no\tboard_title\t\tboard_writer\t\tboard_date");
					System.out.print(board_no + "\t\t");
					System.out.print(board_title + "\t\t\t");
					System.out.print(board_writer + "\t\t");
					System.out.print(board_date + "\t\t");
					System.out.println();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
			System.out.println("1. 전체 목록 출력");
			System.out.println("2. 새 글 작성");
			System.out.println("3. 글 수정");
			System.out.println("4. 글 삭제");
			System.out.println("5. 글 검색");

			String input = sc.nextLine();
			switch(input) {
				case "1" : 
				ArrayList<board_DTO> board = board_Dao.all_select();
				for(int i = 0; i < board.size(); i++) {
					board_DTO temp = board.get(i);
					int board_no = temp.board_no;
					String board_title = temp.board_title;
					String board_writer = temp.board_writer;
					String board_date = temp.board_date;
					String board_content = temp.board_content;
					
					System.out.println("board_no\tboard_title\t\tboard_writer\t\tboard_date\t\tboard_content");
					System.out.print(board_no + "\t\t");
					System.out.print(board_title + "\t\t\t");
					System.out.print(board_writer + "\t\t");
					System.out.print(board_date + "\t\t");
					System.out.println(board_content + "\t\t");					
				}		
				break;
				case "2" :
					board_Dao.board_insert();
					break;
				case "3" :
					board_Dao.board_update();
					break;
				case "4" :
					board_Dao.board_delete();
					break;
				case "5" :
				ArrayList<board_DTO> board_search = board_Dao.board_search();
				for(int i = 0; i < board_search.size(); i++) {
					board_DTO temp = board_search.get(i);
					int board_no = temp.board_no;
					String board_title = temp.board_title;
					String board_writer = temp.board_writer;
					String board_date = temp.board_date;
					String board_content = temp.board_content;
					
					System.out.println("board_no\tboard_title\t\tboard_writer\t\tboard_date\t\tboard_content");
					System.out.print(board_no + "\t\t");
					System.out.print(board_title + "\t\t\t");
					System.out.print(board_writer + "\t\t");
					System.out.print(board_date + "\t\t");
					System.out.println(board_content + "\t\t");					
				}		
				break;
			}
		}
	}
	
}
