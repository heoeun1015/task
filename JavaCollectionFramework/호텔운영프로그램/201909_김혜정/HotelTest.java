package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import kr.or.ddit.basic.T14_PhoneBookTest.Phone;

public class HotelTest {

	Scanner s = new Scanner(System.in);
	Map<String, Hotel> javahotel = new HashMap<>();

	public void select() {
		System.out.println("*******************************************\r\n" + "어떤 업무를 하시겠습니까?\r\n"
				+ "1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n" + "*******************************************\r\n" + "메뉴선택 =>");
	}

	public void bookStart() {
		System.out.println("**************************\r\n" + "호텔 문을 열었습니다.\r\n" + "**************************\r\n"
				+ "\r\n");
		while (true) {

			select();

			int menu = Integer.parseInt(s.nextLine());
			switch (menu) {
			case 1:
				checkIn();
				break;
			case 2:
				checkout();
				break;
			case 3:
				status();
				break;
			case 4:
				end();
				return;
			default:
				bookStart();
				break;
			}
		}
	}

	public void end() {
		System.out.println(" 호텔 문을 닫았습니다.\r\n" + "***************************");
	}

	public void status() {
		Set<String> keySet = javahotel.keySet();

		if (keySet.size() == 0) {
			System.out.println("투숙중인 손님이 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();
			while (it.hasNext()) {
				String room = it.next(); // 키값
				Hotel h = javahotel.get(room);
				System.out.println("방번호 : " + h.getroom() + "\t투숙객 : " + h.getName());

			}
		}

	}

	public void checkout() {
		System.out.println(" * 어느방을 체크아웃 하시겠습니까? 방번호 입력 =>\r\n" + "*******************************************");
		String room = s.nextLine();
		if (javahotel.get(room) != null) {
			System.out.println("체크아웃 되었습니다.");
			javahotel.remove(room);
		} else {
			System.out.println(room + "호에는 체크인한 사람이 없습니다.");
		}
	}

	public void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까? 방번호 입력 => ");
		String room = s.nextLine();
		// 빈방인지 확인
		if (javahotel.get(room) != null) {
			System.out.println("이미 투숙중인 방입니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까? 이름 입력 =>");
		String name = s.nextLine();
		javahotel.put(room, new Hotel(room, name));
		System.out.println("체크인 되었습니다.");

	}

	public static void main(String[] args) {
		new HotelTest().bookStart();
	}

	class Hotel {
		private String room; // 방번호
		private String name; // 이름

		public Hotel(String room, String name) {
			super();
			this.room = room;
			this.name = name;
		}

		public String getroom() {
			return room;
		}

		public void setroom(String room) {
			this.room = room;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
