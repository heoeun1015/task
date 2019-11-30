package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T15_HotelManageTest {
	
	private Scanner s;
	private Map<Integer, Hotel> hotelManage;
	
	public T15_HotelManageTest() {
		s = new Scanner(System.in);
		hotelManage = new HashMap<>();
	}
	
	public static void main(String[] args) {
		new T15_HotelManageTest().mainMenu();
	}
	
	public void displayMenu(){
		
		System.out.println("─────────────────────────────────────");
		System.out.println("1.체크인 / 2.체크아웃 / 3.객실상태 / 4.업무종료");
		System.out.println("─────────────────────────────────────");
		System.out.print("▷ 어떤 업무를 하시겠습니까?: ");
		
	}

	public void mainMenu(){
		
		int menu = 0;
		
		System.out.println("─────────────────────────────────────");
		System.out.println();
		System.out.println("▷ 호텔 문을 열었습니다.");
		System.out.println();
		
		do {
			
			displayMenu();
			
			menu = Integer.parseInt(s.nextLine());
			
			switch(menu) {
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
				System.out.println("─────────────────────────────────────");
				System.out.println();
				System.out.println("▷ 호텔 문을 닫았습니다.");
				System.out.println();
				System.out.println("─────────────────────────────────────");
				break;
			default:
				System.out.println("▷ 다시 선택해주세요.");
				break;
			}
			
		}while(menu != 4);
	}

	public void roomState() {
		
		System.out.println();
		Set<Integer> keySet = hotelManage.keySet();
		Iterator<Integer> its = keySet.iterator();
		int cnt = 0;
		
		System.out.println("\t《 투숙객 리스트 》");
		System.out.println("─────────────────────────────────────");
		System.out.println();
		while(its.hasNext()) {
			cnt++;
			Integer key = its.next();
			Hotel h = hotelManage.get(key);
			System.out.println("\t" + h);
		}
		System.out.println();
		
		
	}

	public void checkOut() {
		
		System.out.println();
		System.out.print("▷ 어느 방을 체크아웃 하시겠습니까?: ");
		int roomNum = Integer.parseInt(s.nextLine());
		System.out.println();
		
		if(hotelManage.get(roomNum) == null) {
			System.out.println("▷ " + roomNum + "번 방에는 체크인한 사람이 없습니다.");
			return;
		}
		
		hotelManage.remove(roomNum);
		
		System.out.println("▷ 체크아웃 되었습니다.");
		
	}

	public void checkIn() {
		
		System.out.println("─────────────────────────────────────");
		System.out.println(" (방 번호 형식: 세 자리 숫자)");
		System.out.print("▷ 어느 방을 체크인 하시겠습니까?: ");
		int roomNum = Integer.parseInt(s.nextLine());
		System.out.println();
		System.out.print("▷ 누구를 체크인 하시겠습니까?: ");
		String name = s.nextLine();
		System.out.println();
		
		if(hotelManage.get(roomNum) != null) {
			System.out.println("▷ " + roomNum + "번 방에는 이미 사람이 있습니다.");
			return;
		}
		
		hotelManage.put(roomNum, new Hotel(roomNum, name));
		System.out.println("▷ 체크인 되었습니다.");
		
		
		
	}

}

class Hotel{
	
	private int roomNum;
	private String name;
	
	public Hotel(int roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	
	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
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
		return "방번호: " + roomNum + ", 투숙객: " + name;
	}
	
	
	
}
