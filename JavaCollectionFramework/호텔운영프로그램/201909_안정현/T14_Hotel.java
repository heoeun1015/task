package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T14_Hotel {
	private Scanner s;
	private Map<String, Room> hotel;

	T14_Hotel() {
		s = new Scanner(System.in);
		hotel = new HashMap<>();
	}
	
	public static void main(String[] args) {
		new T14_Hotel().display();
		
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
		
	}
	

	public void display() {
		while (true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.println("메뉴선택 =>");
			int n = Integer.parseInt(s.nextLine());
			switch (n) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomState();
				break;
			case 4:
				System.out.println("종료되었습니다.");
				return;
			default:
				System.out.println("다시입력해주세요");
			}

		}
	}

	public void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 =>");
		String room = s.nextLine();
		if(hotel.get(room) != null) {
			System.out.println(room + "방에는 이미 사람이 있습니다.");
			return;
		}else {
			System.out.println("누구를 체크인 하시겠습니까?");
			String name = s.nextLine();
			hotel.put(room, new Room(room, name));
			System.out.println("체크인 되었습니다.");
		}
		
	}

	public void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호 입력 =>");
		String room = s.nextLine();
		if(hotel.get(room) == null) {
			System.out.println(room + "방에는 체크인한 사람이 없습니다.");
			return;
		}else {
			hotel.remove(room);
			System.out.println("체크아웃 되었습니다.");
		}
	}

	public void roomState() {
		Set<String> keySet = hotel.keySet();
	
		if(keySet.size() == 0) {
			System.out.println("등록된 호실이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String room = it.next();
				Room r = hotel.get(room);
				System.out.println("방번호 : " + r.getRoom() + "," + "투숙객 : " + r.getName());
			}
		}
	}
}

class Room {
	private String room;
	private String name;

	public Room(String room, String name) {
		super();
		this.room = room;
		this.name = name;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
