package homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelManage {
	private static Scanner s;
	private static Map<Integer, Hotel> checkList;
	
	public HotelManage() {
		s = new Scanner(System.in);
		checkList = new HashMap<>();
	}
	
	public static void main(String[] args) {
		new HotelManage().start();
	}

	private static void start() {
		System.out.println("**************************\r\n"
							+ "호텔 문을 열었습니다.\r\n" 
							+ "**************************");
		
		
		while(true) {
			System.out.println("*******************************************\r\n" 
								+ "어떤 업무를 하시겠습니까?\r\n"
								+ "1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n"
								+ "*******************************************");
			
			System.out.print("메뉴 선택 =>");
			int selectMenu = Integer.parseInt(s.nextLine());
			
			switch(selectMenu) {
			case 1: 
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomStatus();
				break;
			case 4:
				System.out.println("\r\n**************************\r\n" 
									+ "호텔 문을 닫았습니다.\r\n" 
									+ "**************************");
				return;
			}
			
		}
		
	}
	private static void checkIn() {
		System.out.print("어느방에 체크인 하시겠습니까?\r\n" 
							+ "방번호 입력 =>");
		int roomNum = Integer.parseInt(s.nextLine());
		if(checkList.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.print("누구를 체크인 하시겠습니까?\r\n"
							+ "이름 입력 =>");
		String name = s.nextLine();
		
		checkList.put(roomNum, new Hotel(roomNum, name));
		System.out.println("체크인 되었습니다.");
		
	}

	private static void checkOut() {
		System.out.print("어느방을 체크아웃 하시겠습니까?\r\n" 
				+ "방번호 입력 =>");
		int roomNum = Integer.parseInt(s.nextLine());
		if(checkList.get(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			return;
		}
		checkList.remove(roomNum);
		System.out.println("체크아웃 되었습니다.");
		
	}
	
	private static void roomStatus() {
		Set<Integer> keySet = checkList.keySet();
		
		if(keySet.size() == 0) {
			System.out.println("객실이 비어있습니다.");
		} else {
			Iterator<Integer> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				Integer room = it.next();	// 키값
				Hotel h = checkList.get(room);
				System.out.println("방번호 : " + h.getRoom() + ", 투숙객 : " + h.getName());
			}
		}
	}

}

class Hotel{
	private int room;
	private String name;
	
	public Hotel(int room, String name) {
		super();
		this.room = room;
		this.name = name;
	}
	
	public int getRoom() {
		return room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}