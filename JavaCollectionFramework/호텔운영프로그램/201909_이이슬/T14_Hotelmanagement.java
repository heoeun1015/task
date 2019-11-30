package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T14_Hotelmanagement {
	private Scanner scan;
	private Map<String, Hotel> hotelmanagement;
	
	public static void main(String[] args) {
		new T14_Hotelmanagement().hotelStart();
	}
	public T14_Hotelmanagement() {
		scan = new Scanner(System.in);
		hotelmanagement = new HashMap<>();
	}
	// 메뉴출력메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println(" 1. 체크인");
		System.out.println(" 2. 체크아웃");
		System.out.println(" 3. 객실상태");
		System.out.println(" 4. 업무종료");
		System.out.println("*******************************************");
		System.out.print(" 메뉴선택 >> ");		
	}
	
	
	// 프로그램을 시작하는 메서드
		public void hotelStart(){
			System.out.println("*******************************************");
			System.out.println("   호텔 문을 열었습니다.");
			System.out.println("*******************************************");
			
			while(true){
				
				displayMenu();  // 메뉴 출력
				
				int menuNum = scan.nextInt();   // 메뉴 번호 입력
				
				switch(menuNum){
					case 1 : checkin();		// 체크인
						break;
					case 2 : checkout();		// 체크아웃
						break;
					case 3 : room();		// 객실상태
						break;
					case 4 :
						System.out.println("*******************************************");
						System.out.println("   호텔 문을 닫았습니다.");
						System.out.println("*******************************************");
						return;
					default :
						System.out.println("잘못 입력했습니다. 다시입력하세요.");
				} // switch문
			} // while문
		}
		
		//체크인 메서드
		private void checkin() {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까");
			System.out.print("방번호 >> ");
			String roomnum = scan.next();
			
			// 객실 체크인 상태확인
			// get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 반환함.
			if(hotelmanagement.get(roomnum) != null) {
				System.out.println(roomnum + "번 방에는 이미 사람이 있습니다.");
				return;
			}
			System.out.print("이름입력 >> ");
			scan.nextLine(); //입력버퍼에 남아 있는 엔터키 값까지 읽어와 버리는 역할을 한다.(쓰레기값 제거용) ..next를 쓰다 nextLine을 쓰면 발생
			String name = scan.nextLine();
			
			hotelmanagement.put(roomnum, new Hotel(roomnum, name));
			System.out.println("체크인 되었습니다.");
		}
		
		//체크아웃 메서드
		private void checkout() {
			System.out.println();
			System.out.println("어느 방을 체크아웃 하시겠습니까?");
			System.out.print("방번호 >> ");
			String roomnum = scan.next();
			
			// remove(key) => 삭제 성공하면 삭제된 value값을 반환하고
			//			   => 실패하면 null을 반환한다.
			if(hotelmanagement.remove(roomnum) == null) {
				System.out.println(roomnum + "번 방은 이미 체크아웃 되었습니다.");
			}else {
				System.out.println("체크아웃 되었습니다.");
			}			
		}
		
		//객실상태 메서드
		private void room() {
			Set<String> keySet = hotelmanagement.keySet();
			
			if(keySet.size() == 0) {
				System.out.println("등록된 예약이 없습니다.");
			}else {
				Iterator<String> it = keySet.iterator();
				while(it.hasNext()) {
					String roomNum = it.next();
					Hotel s = hotelmanagement.get(roomNum);
					System.out.println("방번호 : " + s.getRoomnum()+ " 투숙객 : " +s.getName());
				}
			}
		}
}
	
	// 
class Hotel{
	private String roomnum;	// 방번호
	private String name;		// 이름
	
	public Hotel(String roomnum, String name) {
		super();
		this.roomnum = roomnum;
		this.name = name;
	}

	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Hotel [방번호=" + roomnum + ", 투숙객=" + name + "]";
	}

	
	
	
	
}
