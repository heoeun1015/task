package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
public class T14h_Hotel {
	private Scanner scan;
	private Map<String, Hotel> roomNumMap;		//변수선언

	T14h_Hotel() {
		scan = new Scanner(System.in);
		roomNumMap = new HashMap<>();			//생성자에서 초기화	
	}
	
	// 프로그램을 시작하는 메서드
		public void HotelStart(){
			System.out.println("**************************");
			System.out.println("호텔 문을 열었습니다.");
			System.out.println("**************************\n");
			
		while(true){
				
				displayMenu();  // 메뉴 출력
				
				int menuNum = scan.nextInt();   // 메뉴 번호 입력
				
				switch(menuNum){
					case 1 : checkin();			// 체크인
						break;	
					case 2 : checkout();		// 체크아웃
						break;
					case 3 : room();			// 객실상태
						break;
					case 4 :
						System.out.println();
						System.out.println("**************************");
						System.out.println("호텔 문을 닫았습니다.");
						System.out.println("**************************");						
						return;
					default :
						System.out.println("잘못 입력했습니다. 다시입력하세요.");
				} // switch문
			} // while문
		}
		
		// 메뉴를 출력하는 메서드
		public void displayMenu(){
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?.");
			System.out.print("1.체크인  ");
			System.out.print("2.체크아웃  ");
			System.out.print("3.객실상태  ");
			System.out.println("4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");		
		}
		
		//체크인을 하는 메서드
		//(이미 체크인된 방에는 체크인 할 수 없다.)
		private void checkin() {
			System.out.println();
			System.out.println("어느 방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			String roomNum = scan.next();
			
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 => ");
			String name = scan.next();		//공백 기준
			
			//이미 체크인된 방인지 검사. 이미 있으면 안됨
			//get()메서드 값을 가져올 때 가져올 자료가 없으면 null을 반환함. 이미 등록되어있으면 null이 아님
			if(roomNumMap.get(roomNum) != null) {
				System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
				return;
			}
			
			Hotel h = new Hotel(roomNum, name);
			roomNumMap.put(roomNum, h);
			
			System.out.println(name+"님 "+roomNum + "방에 체크인 되었습니다");
		}
		
		
		//체크아웃을 하는 메서드
		private void checkout() {
			System.out.println();
			System.out.println("어느 방에 체크아웃 하시겠습니까?");
			System.out.print("방번호 입력 => ");
			String roomNum = scan.next();
			
			//remove(key) => 삭제 성공하면 삭제된 value값을 반환하고 실패하면 null을 반환
			if(roomNumMap.remove(roomNum)==null) {	//삭제실패
				System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			}else {
				System.out.println(roomNum + "방이 체크아웃 되었습니다.");
			}
		}
		
		//객실상태를 확인하는 메서드
		private void room() {
			/*
			//Iterator를 이용한 방법
			Set<String> keySet = roomNumMap.keySet();	
			if(keySet.size() == 0) {
				System.out.println("방번호와 투숙객 정보가 없습니다.");
			}else {
				Iterator<String> it = keySet.iterator();
				while(it.hasNext()) {
					String roomNum = it.next();
					Hotel h = roomNumMap.get(roomNum);
					System.out.println("방번호 : "+h.getRoomNum() + ", 투숙객 : "+h.getName());
				}
			}*/
			
			
			//향상된for문 이용방법
			Set<String> keySet = roomNumMap.keySet();
			if(keySet.size() == 0) {
				System.out.println("방번호와 투숙객 정보가 없습니다.");
			}else {
				Iterator<String> it = keySet.iterator();
				for(String roomNum : keySet) {
					Hotel h = roomNumMap.get(roomNum);
					System.out.println("방번호 : "+h.getRoomNum() + ", 투숙객 : "+h.getName());
				}
			}	
		}
		
		
	public static void main(String[] args) {
		new T14h_Hotel().HotelStart();
	}
}

//호텔정보를 저장할 수 있는 VO클래스
class Hotel{
	private String roomNum;			//방번호
	private String name;			//이름
	
	public Hotel(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}