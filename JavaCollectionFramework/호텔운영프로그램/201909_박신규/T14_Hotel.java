package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T14_Hotel {

	private Scanner sc;
	private Map<Integer, Hotel> hotelResMap;

	public T14_Hotel() {
		sc = new Scanner(System.in);
		hotelResMap = new HashMap<>();
	}

	public static void main(String[] args) {
		new T14_Hotel().hotelResStart();
	}

	public void hotelResStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();

		while(true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");
			int a = Integer.parseInt(sc.nextLine());
			if ( a == 1 ) {
				checkin();
			} else if ( a == 2 ) {
				checkout();
			} else if ( a == 3 ) {
				roomstatus();
			} else if ( a == 4 ) {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}
		}
	}

	public void roomstatus() {
		Set<Integer> keySet = hotelResMap.keySet();

		if(keySet.size() == 0) {
			System.out.println("등록된 예약이 없습니다.");
		} else {
//			Iterator<Integer> it = keySet.iterator();
//			while(it.hasNext()) {
//				int room = it.next(); // 키값
//				Hotel p = hotelResMap.get(room);
//				System.out.println("방번호 :" + "\t" + p.getRoomnum() + "\t" + "투숙객 : "+ p.getName());
//			}
			System.out.println("=====================");
			for (int a : hotelResMap.keySet()) {
				Hotel p = hotelResMap.get(a);
				System.out.println("방번호 :" + "\t" + p.getRoomnum() + "\t" + "투숙객 : "+ p.getName());
			}
		}
	}

	public void checkout() {
		System.out.println();
		System.out.println("어떤 방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 >>  ");
		int room = Integer.parseInt(sc.nextLine());

		if(hotelResMap.remove(room) == null) {
			System.out.println(room + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println("체크아웃 되었습니다.");
		}
	}

	public void checkin() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int room = Integer.parseInt(sc.nextLine());

		if(hotelResMap.get(room) != null) {
			System.out.println(room + "번 방은 사람이 이미 있습니다.");
			return;
		}

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = sc.nextLine();
		System.out.println("체크인 되었습니다");

		hotelResMap.put(room, new Hotel(name, room));
	}
}

class Hotel{
	private String name;
	private int roomnum;

	public Hotel(String name, int roomnum) {
		super();
		this.name = name;
		this.roomnum = roomnum;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(int roomnum) {
		this.roomnum = roomnum;
	}
}