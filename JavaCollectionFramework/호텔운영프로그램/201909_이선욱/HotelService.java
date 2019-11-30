package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
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
public class HotelService {
	private Scanner scan;
	private Map<String, Hotel> hotelMap;
	
	public HotelService() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	
	public static void main(String[] args) {
		new HotelService().HotelStart();
	}

	public void HotelStart() {
		System.out.println("***************");
		System.out.println("호텔문을 열었습니다");
		System.out.println("***************");
		
		while(true) {
			displayMenu(); //메뉴 출력
			
			int select = Integer.parseInt(scan.nextLine()); //메뉴 번호 선택
		
			switch(select){
				case 1 : checkIn();		// 체크인
					break;
				case 2 : checkOut();	// 체크아웃
					break;
				case 3 : roomStatus();	// 객실상태
					break;
				case 4 : // 업무종료
					System.out.println("프로그램을 종료합니다...");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}

	public void roomStatus() {
		Set<String> keySet = hotelMap.keySet();
		
		if(keySet.size() == 0) {
			System.out.println("현재 투숙객이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String roomNum = it.next(); //키값
				Hotel ht = hotelMap.get(roomNum);
				System.out.println("방번호 : " + ht.getRoomNum()
									+ ", 투숙객 : " + ht.getName());
			}
		}
	}

	public void checkOut() {
		System.out.println();
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.println("방번호 입력 >> ");
		String checkNum = scan.nextLine();
		
		//remove(checkNum) => 삭제 성공하면 삭제된 value값을 반환하고
		//				   => 실패하면 null을 반환한다.
		if(hotelMap.remove(checkNum) == null) {
			System.out.println(checkNum + "방에는 체크인한 사람이 없습니다");
		}else {
			System.out.println("체크아웃 되었습니다");
		}
	}

	public void checkIn() {
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 => ");
		String checkNum = scan.nextLine();
		
		
		//이미 등록된 방인지 검사
		//get()로 메서드로 값을 가져올 때 가져올 자료가 없으면 null을 반환한다.
		if(hotelMap.get(checkNum) != null) {
			System.out.println(checkNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까?");
		String name = scan.next();
		scan.nextLine();
		
		hotelMap.put(checkNum, new Hotel(checkNum, name));
		System.out.println("체크인 되었습니다");
	}

	public void displayMenu() {
		System.out.println("**********************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인 2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("**********************************");
		System.out.println("메뉴선택 => ");
		
	}

}

//객실 상태를 저장할 수 있는 VO 클래스
class Hotel {
	private String roomNum; //객실번호
	private String name; //예약자
	
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
	
	@Override
	public String toString() {
		return "Hotel[방번호 : " + roomNum 
				+ ", 투숙객 : " + name + "]";
	}
	
}
