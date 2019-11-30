package kr.or.ddit.basic;
/*
	 문제)
	
	호텔 운영을 관리하는 프로그램 작성.(Map이용)
	 - 키값은 방번호 
	 
	실행 예시)
	
	**************************
	호텔 문을 열었습니다.
	**************************
	
	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 1 <-- 입력
	
	어느방에 체크인 하시겠습니까?
	방번호 입력 => 101 <-- 입력
	
	누구를 체크인 하시겠습니까?
	이름 입력 => 홍길동 <-- 입력
	체크인 되었습니다.
	
	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 1 <-- 입력
	
	어느방에 체크인 하시겠습니까?
	방번호 입력 => 102 <-- 입력
	
	누구를 체크인 하시겠습니까?
	이름 입력 => 성춘향 <-- 입력
	체크인 되었습니다
	
	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 3 <-- 입력
	
	방번호 : 102, 투숙객 : 성춘향
	방번호 : 101, 투숙객 : 홍길동
	
	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 2 <-- 입력
	
	어느방을 체크아웃 하시겠습니까?
	방번호 입력 => 101 <-- 입력
	체크아웃 되었습니다.
	
	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 1 <-- 입력
	
	어느방에 체크인 하시겠습니까?
	방번호 입력 => 102 <-- 입력
	
	누구를 체크인 하시겠습니까?
	이름 입력 => 허준 <-- 입력
	102방에는 이미 사람이 있습니다.
	
	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 2 <-- 입력
	
	어느방을 체크아웃 하시겠습니까?
	방번호 입력 => 101 <-- 입력
	101방에는 체크인한 사람이 없습니다.
	
	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 3 <-- 입력
	
	방번호 : 102, 투숙객 : 성춘향
	
	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 4 <-- 입력
	
	**************************
	호텔 문을 닫았습니다.
	**************************
*/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T14h_Hotel {
	private Scanner scan;
	private Map<String, Room> roomMap;
	
	public T14h_Hotel() {
		scan = new Scanner(System.in);
		roomMap = new HashMap<>();
	}
	
	// 프로그램을 시작하는 메서드
	public void start(){
		System.out.println("**************************"); 
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");

		
		while(true){
			System.out.println();
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			System.out.println();
			
			switch(menuNum){
				case 1 : checkIn();		// 체크인
					break;
				case 2 : checkOut();	// 체크아웃
					break;
				case 3 : roomState();	// 객실상태
					break;
				case 4 : 				// 업무종료
					System.out.println("**************************"); 
					System.out.println("호텔 문을 닫았습니다.");
					System.out.println("**************************");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}

	private void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String num = scan.next();
		
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();
		
		if(roomMap.get(num) != null) {
			System.out.println(num + "번 방은 이미 사람이 있습니다.");
			return;
		}

		roomMap.put(num, new Room(num, name));
		System.out.println(num + "방 체크인 완료...");
	}
	
	private void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String num = scan.next();
		
		if(roomMap.remove(num) == null) {
			System.out.println(num + "번 방에는 체크인 한 사람이 없습니다.");
		}else {
			System.out.println(num + "번 방이 체크아웃 되었습니다.");
		}
	}
	
	private void roomState() {
//		메뉴선택 => 3 <-- 입력
//		방번호 : 102, 투숙객 : 성춘향
//		방번호 : 101, 투숙객 : 홍길동
		
		Set<String> keySet = roomMap.keySet();
		if(keySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				String num = it.next();
				Room r = roomMap.get(num);
				System.out.println("방번호 : " + r.getNum() + ", 투숙객 : " + r.getName());
			}
		}
	}

	public static void main(String[] args) {
		new T14h_Hotel().start();
	}
}

class Room{
	private String num;		//방번호
	private String name;	//이름
	
	public Room(String num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
