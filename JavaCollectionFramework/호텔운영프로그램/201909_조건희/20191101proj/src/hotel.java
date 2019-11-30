
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
public class hotel {
	Scanner scan;
	private Map<String, hotelVO> HotelMap;
		
	public hotel() {
		scan = new Scanner(System.in);
		HotelMap = new HashMap<String, hotelVO>();
	}
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("메뉴를 선택하세요.");
		System.out.println(" 1. 체크인");
		System.out.println(" 2. 체크아웃");
		System.out.println(" 3. 객실상태");
		System.out.println(" 4. 업무종료");		
		System.out.println(" 0. 프로그램 종료");
		System.out.print(" 번호입력 >> ");		
	}
	
	// 프로그램을 시작하는 메서드
	public void hotelVOStart(){
		System.out.println("===============================================");
		System.out.println("   		호텔 관리 프로그램					   ");
		System.out.println("===============================================");
		
		while(true){
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = scan.nextInt(); // 메뉴 번호 입력
			
			switch(menuNum){
				case 1 : checkin();		// 체크인
					break;				
				case 2 : checkout();	// 체크아웃
					break;				
				case 3 : displayAll();	// 전체 출력
					break;
				case 0 :
					System.out.println("프로그램을 종료합니다...");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}
		
	private void checkout() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력");
		String room = scan.next(); 
				
		// remove(key) => 삭제 성공하면 삭제된 value값을 반환하고 실패하면
		// 			   => null을 반환한다.		
		if(HotelMap.remove(room) == null) {
			System.out.println(room + "은 없는방입니다.");
		} else {
			System.out.println(room + "방을 삭제했습니다.");
		}
		
	}

	/*
	 * 전체 자료를 출력하는 메서드
	 */	
	private void displayAll() {
		
		Set<String> keySet = HotelMap.keySet();		
		
		if(keySet.size() == 0) {
			System.out.println("등록된 방번호가 정보가 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();			
			while(it.hasNext()) {
				
				String room = it.next(); // 키값
				hotelVO p = HotelMap.get(room); // value값
				
				System.out.println("방번호 " + p.getRoom()
								   + " 투숙객  " + p.getGuest());
			}
		}
	}

	/**
	 * 	새로운 전화번호 정보를 등록하는 메서드
	 * 	(이미 등록된 사람은 등록되지 않는다.)
	 */
	private void checkin() {		
		System.out.println("어느 방에 체크인 하시겠습니까?");
		String room = scan.next();
		
		// 방이 이미 체크인 되었는지 확인 
		// get() 메서드로 값을 가져올떄 가져올 자료가 없으면 null을 반환함
		if(HotelMap.get(room) != null) {
			System.out.println(room + "이 이미 등록되었습니다.");
			return;
		} 	
							
		System.out.println("이름을 입력하세요  >> ");		
		String guest = scan.next();
				
		HotelMap.put(room, new hotelVO(guest, room));
		System.out.println(guest + "씨 등록 완료....");		
	}
	

	public static void main(String[] args) {
		new hotel().hotelVOStart();
	}

}

/*
 *	전화번호를 저장할 수 있는 VO 클래스 
 */
class hotelVO {
		private String guest;	// 게스트
		private String room; 	// 방번호		
				
		public hotelVO(String guest, String room) {
			super();
			this.guest = guest;
			this.room = room;
		}
		
		public String getGuest() {
			return guest;
		}
		public void setGuest(String guest) {
			this.guest = guest;
		}
		public String getRoom() {
			return room;
		}
		public void setRoom(String room) {
			this.room = room;
		}						
}



