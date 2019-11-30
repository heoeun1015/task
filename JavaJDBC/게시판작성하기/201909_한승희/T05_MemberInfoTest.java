package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import kr.or.ddit.util.DBUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128)    -- 주소
);

*/
public class T05_MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	private static final Logger sqlLogger = Logger.getLogger("log4jexam.sql.Query");
	private static final Logger paramLogger = Logger.getLogger("log4jexam.sql.Parameter");
	private static final Logger resultLogger = Logger.getLogger(T05_MemberInfoTest.class);
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
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
					updateMember();
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
	
	//회원 정보 삭제 메서드
	private void deleteMember() {
		boolean chk = true;
		
		String memId = "";
		
		do {
			System.out.print("\n삭제할 회원 ID를 입력하세요 >> ");
			memId = scan.next();
			
			if(memId.equals("-1"))
			{
				chk = true;
			}else {
				chk = getMember(memId);				
			}
			if(chk==false)
			{
				System.out.println(memId + "는 존재하지 않는 회원입니다.");
				System.out.println("다시 입력하시거나 종료하고 싶으면 -1을 입력하세요.");
			}
		}while(chk==false);
		if(memId.equals("-1")) {}
		else{
			try {
				conn = DBUtil3.getConnection();
				String sql = "DELETE FROM mymember WHERE mem_id = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memId);
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt > 0) {
					System.out.println(memId + "님의 회원 정보가 삭제되었습니다.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disConnect();
			}
		}
	}

	//회원 정보 업데이트 메서드
	private void updateMember() {
		String memId = "";
		boolean chk = true;
		
		System.out.print("\n");
		
		do {
			System.out.print("수정할 회원 ID를 입력하세요 >> ");
			memId = scan.next();
			
			if(memId.equals("-1"))
			{
				chk = true;
			}else {
				chk = getMember(memId);				
			}
			if(chk==false)
			{
				System.out.println(memId + "는 존재하지 않는 회원입니다.");
				System.out.println("다시 입력하시거나 종료하고 싶으면 -1을 입력하세요.");
			}
		}while(chk==false);
		if(memId.equals("-1")) {}
		else {
			System.out.println("수정할 내용을 입력하세요.");
			System.out.print("새로운 회원 이름 >> ");
			String memName = scan.next();
			
			System.out.print("새로운 회원 전화번호 >> ");
			String memTel = scan.next();
			
			scan.nextLine();
			System.out.print("새로운 회원 주소 >> ");
			String memAddr = scan.nextLine();
			
			try {
				conn = DBUtil3.getConnection();
				
				String sql = "UPDATE mymember SET mem_name = ? ,"
						+ " mem_tel = ? , mem_addr = ? WHERE mem_id = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memName);
				pstmt.setString(2, memTel);
				pstmt.setString(3, memAddr);
				pstmt.setString(4, memId);
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt>0) {
					System.out.println(memId + "님의 회원정보 수정이 완료되었습니다.");
				}else {
					System.out.println("오류로 인하여 " + memId + "님의 회원정보 수정을 실패했습니다.");				
				}
			} catch (SQLException e) {				
				e.printStackTrace();
			} finally {
				disConnect();
			}
		}
	}

	//회원 정보 출력
	private void displayMemberAll() {
		System.out.println("\n===================================");
		System.out.println(" ID\t이름\t전화번호\t주소");
		System.out.println("===================================");
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "SELECT * FROM mymember";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	//회원 정보 추가
	private void insertMember() {
		boolean chk = false;
		
		String memId;
		
		do {
			System.out.println("\n추가할 회원정보를 입력하세요.");
			System.out.print("회원 ID >> ");
			memId = scan.next();
			chk = getMember(memId);
			
			if(chk) {
				System.out.println(memId + "는 이미 존재하는 ID입니다.\n다시 입력하세요.");
			}
		}while(chk);
		
		System.out.print("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화 번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();	//입력버퍼지우기
		
		System.out.print("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) VALUES (?,?,?,?)";
			
			sqlLogger.debug("쿼리 : " + sql);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			paramLogger.debug("파라미터 : " + memId + ", "
					+ memName + ", "
					+ memTel + ", "
					+ memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			resultLogger.debug("결과 : " + cnt);
			
			
			if(cnt>0) {
				System.out.println(memId + "님은 성공적으로 가입되었습니다.");
			}else {
				System.out.println("오류가 발생하여 " + memId + "님이 가입되지 않았습니다.");
			}
		} catch (Exception e) {
			System.out.println("오류가 발생하여 " + memId + "님이 가입되지 않았습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	private void disConnect() {
		if(rs!=null) try {rs.close();} catch (SQLException e) {};
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {};
		if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {};
		if(conn!=null) try {conn.close();} catch (SQLException e) {};
	}

	//회원 ID를 이용하여 중복체크
	private boolean getMember(String memId) {
		boolean chk = false;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "SELECT COUNT(*) cnt FROM mymember WHERE mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1)>=1)
			{
				chk = true;
			}
		} catch (SQLException e) {
			chk = false;
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return chk;
	}

	public static void main(String[] args) {
		T05_MemberInfoTest memObj = new T05_MemberInfoTest();
		memObj.start();
	}

}






