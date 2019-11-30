package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T14_Hotel {
	private Scanner scan;
	private Map<String, Hotel> hotelMap;
	
	public T14_Hotel() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	
	//메뉴 출력
	public void menu() {
		System.out.println();
		System.out.println("**********************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인  2.체크아웃  3. 객실상태  4.업무종료");
		System.out.println("**********************************");
		System.out.print("메뉴선택 => ");	
	}
	
	//프로그램 시작
	public void hotelStart() {
		System.out.println("*****************");
		System.out.println(" 호텔 문을 열었습니다. ");
		System.out.println("*****************");
		
		while(true) {
			menu();	//메뉴 출력
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			switch(menuNum) {
			case 1: checkIn();break;	//체크인
			case 2: checkOut();break;	//체크아웃
			case 3: checkRoom();break;	//객실상태
			case 4: System.out.println("*****************\r"+"호텔 문을 열었습니다.\r"+"*****************");;break;
					//업무종료
			} // switch문
		} // while 문
	}
	
	/**
	 * 체크인
	 */
	private void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String room = scan.next();
		
		//이미 체크인 한 방인지 검사
		if(hotelMap.get(room) != null) {
			System.out.println(room + "방은 체크인 된 방입니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = scan.next();
		hotelMap.put(room, new Hotel(room, name));
		System.out.println(name + "님 체크인 되었습니다");
	}
	/**
	 * 체크아웃
	 */
	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String room = scan.next();	
		if(hotelMap.remove(room) == null) {
			System.out.println(room + "번 방은 체크아웃 할 수 없습니다.");
		}else {
			System.out.println(room + "번 방 체크아웃 되었습니다.");
		}
	}
	/**
	 * 객실상태 확인
	 */
	private void checkRoom() {
		Set<String> keySet = hotelMap.keySet();
		if(keySet.size() == 0) {
			System.out.println("빈 방이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				String room = it.next(); //방번호 : 키값
				Hotel h = hotelMap.get(room);
				System.out.println(cnt+ "방번호 : " + h.getRoom()
									+", 투숙객 : " + h.getName());
			}
		}
		System.out.println("객실상태 출력 완료");
	}
	
	public static void main(String[] args) {
		new T14_Hotel().hotelStart();
	}
	
}


class Hotel {
		private String name;	//이름
		private String room;	//방번호
		//생성자
		public Hotel(String name, String room) {
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
}