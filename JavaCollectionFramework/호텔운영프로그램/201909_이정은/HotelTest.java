package kt.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import kt.or.ddit.basic.T14_PhoneBookTest.Phone;

/*문제)

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
***************************/
public class HotelTest {
	private Scanner sc;
	private Map <String, Hotel> HotelMap;
	
	public HotelTest() {
		sc = new Scanner(System.in);
		HotelMap = new HashMap<>();
	}

	public void displayMenu() {
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.println("메뉴선택 => ");
	}
	
	private void HotelStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		

	
	while(true) {
	
		displayMenu();
		
		int menuNum = sc.nextInt();
		
		switch(menuNum) {
			case 1 : checkin();
				break;
			case 2 : checkout();
				break;
			case 3 : roomcondition();
				break;
			case 4 : 
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
			default :
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
				break;
			
		}
	}
}
	

	private void checkin() {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.println("방번호 입력 => ");
			String room = sc.next();
			
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.println("이름 입력 => ");
			String name = sc.next();
			
			if(HotelMap.get(room) != null){
				System.out.println(room +"방에는 이미 사람이 있습니다.");
				return;
			}
			
			HotelMap.put(room, new Hotel(room,name));	
			System.out.println("체크인되었습니다.");
			
	}
	
	private void checkout() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호 입력 => ");
		String room = sc.next();
		
		if(HotelMap.get(room) == null) {
			System.out.println(room+"방에는 체크인한 사람이 없습니다.");
			return;
		}
		HotelMap.remove(room);
		System.out.println("체크아웃 되었습니다.");		
	}
	

	private void roomcondition() {
		Set<String> keySet = HotelMap.keySet();
		
		if(keySet.size() == 0) {
			System.out.println("등록된 정보가 하나도 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				String room = it.next();	// 키값
				Hotel h = HotelMap.get(room);
				System.out.println(" " + cnt + "\t" + h.getName() + "\t" + h.getRoom() );
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		new HotelTest().HotelStart();
	}
	
	class Hotel{
		
		private String name;
		private String room;
		
		
		
		public Hotel(String room, String name) {
			super();
			this.name = name;
			this.room = room;
		}
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getRoom() {
			return room;
		}
		public void setRoom(String room) {
			this.room = room;
		}
		
		@Override
		public String toString() {
			return "Hotel [name=" + name + ", room=" + room + "]";
		}
	
		
	}
	}


