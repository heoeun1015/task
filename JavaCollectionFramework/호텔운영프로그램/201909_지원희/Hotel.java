package homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Hotel {
	private Scanner scan;
	private Map<String, Hotel> hotelMap;
	
	public Hotel() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	public Hotel(String room, String name) {
		
	}
	//메뉴를 출력하는 메서드
	public void displayMenu() {
		System.out.println("◐◑◐◑◐◑◐◑◐◑◐◑◐◑◐◑◐◑");
		System.out.println();
		System.out.println("원하시는 업무 번호를 선택하십시오.");
		System.out.println("1. 체크인  2. 체크아웃  3. 객실현황 4. 업무종료");
		System.out.println("메뉴번호입력 ==>>");
	}
	//프로그램을 시작하는 메서드
	public void hotelStart() {
		System.out.println("◐◑◐◑◐◑◐◑◐◑◐◑◐◑◐◑");
		System.out.println("◐◑Hotel JAVA 오픈◐◑");
		System.out.println("◐◑◐◑◐◑◐◑◐◑◐◑◐◑◐◑");
		
		while(true) {
			displayMenu(); //메뉴출력
			
			int menuNum = scan.nextInt(); //메뉴번호 입력
			switch(menuNum) {
			case 1 : checkIn(); //체크인
				break;
			case 2 : checkOut();//체크아웃
				break;
			case 3 : checkRoom();  //객실현황
				break;
			case 4 : System.out.println("◐◑◐◑◐◑◐◑이용해 주셔서 감사합니다.◐◑◐◑◐◑◐◑");
				break;
				//업무 종료.
			}
		}//switch문
	}//while문
	
	
	/* 체크인 */
	private void checkIn() {
		System.out.println();
		System.out.println("몇 번 방에 체크 인 하시겠습니까?");
		System.out.println("방 번호 ==>>");
		String room = scan.next();
		
		//이미 체크인 한 방인지 검사.
		if(hotelMap.get(room) != null) {
			System.out.println(room + " 방은 이미 체크 인 된 방입니다.");
			return;
		}
		System.out.println("체크 인 하실 분 성함이?");
		System.out.println("이름 입력 ==>>");
		String name = scan.next();
		hotelMap.put(room, new Hotel(room, name));
		System.out.println(name + " 님, 체크 인 되셨습니다.");
	
	}
	/* 체크아웃 */
	private void checkOut() {
		System.out.println();
		System.out.println("몇 번 방을 체크아웃 하시겠습니까?");
		System.out.println("방 법호 ==>>");
		String room = scan.next();
		if(hotelMap.remove(room) == null) {
			System.out.println(room + " 번 방은 체크아웃 할 수 없습니다.");
		}else {
			System.out.println(room + " 번 방이 체크아웃 되었습니다.");
		}
	
	}
	/*객실현황 확인*/
	private void checkRoom() {
		Set<String> keySet = hotelMap.keySet();
		if(keySet.size() == 0) {
			System.out.println("빈 방이 없슴니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNest()) {
				cnt++;
				String room = it.next(); //방번호 : 키값
				Hotel h = hotelMap.get(room);
				System.out.println(cnt + "방번호 : " + h.getRoom() + ", 투숙객 : " + h.getName());
			}
		}
		System.out.println("객실상태 출력 완료");
	}
	
	public static void main(String[] args) {
		new Hotel().hotelStart();
	}
}

class Hotel1 {
	private String name; 	//이름
	private String room;	//방번호
	//생성자
	public Hotel1(String name, String room) {
		super();
		this.name = name;
		this.room = room;
	}
	
	private int getName() {
		
		return name;
	}
	private int setName(String name) {
		
		this.name = name;
	}
	private int getRoom() {
		
		return room;
	}


private int setRoom(String room) {
	
		this.room = room;
}

}
	

























