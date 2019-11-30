package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	private Scanner sc;
	private Map<String, Status> statusMap;
	
	public Hotel () {
		sc = new Scanner(System.in);
		statusMap = new HashMap<>();
	}

	// 메뉴를 출력하는 메서드
	public void menu() {
		System.out.println("*************************************************\r"
				+ "\t     호텔 문을 열었습니다.\r"
				+ "*************************************************");
		System.out.println("\t   어떤 업무를 하시겠습니까 ? \r "
				+ "1. 체크인 2. 체크아웃 3. 객실상태 4. 업무종료");
		System.out.println("*************************************************");
		System.out.print("메뉴 선택 > ");
	}
	
	// 프로그램을 시작하는 메서드
	public void start() {
		
		
		while(true) {
			
			menu();
			
			int menu = sc.nextInt();
			
			switch (menu) {
			case 1: CheckIn();	// 체크인
				break;
				
			case 2: CheckOut();	// 체크아웃
				break;

			case 3 : RoomStatus();// 객실상태
				break;
				
			case 4 :
				System.out.println("영업을 종료합니다.");// 업무종료
				return;
				
			default:
				System.out.println("잘못 입력했습니다. 다시 입력하세요.");
			}
			
		}
	}


	public static void main(String[] args) {
		new Hotel().start();
	}

	private void CheckIn() {
		
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까 ?");
		System.out.print("방번호 입력 >");
		String roomNum = sc.next();
		
		if(statusMap.get(roomNum) != null) {
			System.out.println(roomNum + "호는 이미 체크인된 방입니다.");
			return;
		}
		
		System.out.println();
		System.out.print("이름 입력 > ");
		String name = sc.next();
		
		statusMap.put(roomNum, new Status(roomNum, name));
		System.out.println("체크인 되었습니다.");
		
	}

	private void CheckOut() {
		System.out.println();
		System.out.println("체크아웃 방번호를 입력하세요.");
		System.out.print("방번호 > ");
		String roomNum = sc.next();
		
		if(statusMap.remove(roomNum) == null) {
			System.out.println(roomNum + "호는 빈방입니다.");
			return;
		}else {
			System.out.println("체크아웃 되었습니다");
		}
		
	}

	private void RoomStatus() {
		System.out.println();
		Set<String> KeySet = statusMap.keySet();
		System.out.println("*************************************************");
		
		if(KeySet.size() == 0) {
			System.out.println("모든 방이 비어있습니다.");
		}else {
			Iterator<String> it = KeySet.iterator();
			while(it.hasNext()) {
				String roomNum = it.next();
				Status s = statusMap.get(roomNum);
				System.out.println("방번호 : " + s.getRoomNum() + "," + " 투숙객 : " + s.getName());
			}
		}
		
	}
	
}

class Status {
	private String roomNum;
	private String name;
	public Status(String roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
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
		return "Phone[name-" + name + ", roomNum=" + roomNum + "]";
	}
}
