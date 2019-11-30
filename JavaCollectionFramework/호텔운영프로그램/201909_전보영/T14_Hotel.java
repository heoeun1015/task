package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class T14_Hotel {

	//멤버변수
	private Scanner scan;
	private Map<String, Hotel> hotelRoomCheck;
	
	
	//생성자 
	private T14_Hotel() {
		scan = new Scanner(System.in);	
		hotelRoomCheck = new HashMap<>(); //맵 객체에 생성 
	}
	
	//메뉴
	
	public void displayMenu() {
		System.out.println();
		System.out.println("**************************");	
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.print("1.체크인 ");
		System.out.print("2.체크아웃 ");
		System.out.print("3.객실상태 ");
		System.out.println("4.업무종료 ");
		System.out.println("**************************");	
		System.out.print("메뉴입력>>");
	}
	

	 public void HotelStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		int menu;		
		do {
			displayMenu();			

			menu = scan.nextInt();
			
			switch (menu) {
			case 1 : checkIn() ;
					break;	
					
			case 2 : checkOut();
					break;	
					
			case 3 : roomStatus();
				break;	
				
			case 4 : 
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				break;
				
			default	: System.out.println("잘못 입력하셨습니다."); 
			
			}			
		}
		 while (menu != 4);
		
	 }
	


	public void checkIn() {
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");		
		System.out.print("방 번호 입력>>");
		String roomNumber = scan.next();
		
		//등록된 방번호 인지 검사
		//get() 메서드 값을 가져올 때 가져올 자료가 없으면 null을 반환 
		
		if(hotelRoomCheck.get(roomNumber) != null) {
			System.out.print("이름 입력>>");
			scan.nextLine();
			String name = scan.next();
			System.out.println(roomNumber + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까? ");
		System.out.print("이름 입력>>");
		scan.nextLine();
		String name = scan.next();
		
		
		hotelRoomCheck.put(roomNumber, new Hotel(name,roomNumber));		
		System.out.println("체크인 되었습니다.");		
				
	}
	
	
	
	
	
	public void roomStatus() {
	Set<String> keySet= hotelRoomCheck.keySet();
	
	
	if(keySet.size() == 0) {
		System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
	}else {
		Iterator<String> it = keySet.iterator();
		int cnt = 0;
		while(it.hasNext()) {
			cnt++;
			String roomNumber = it.next();
			Hotel h = hotelRoomCheck.get(roomNumber);
			System.out.println("방번호 : " + h.getRoomNumber() + "," + "투숙객 : " + h.getName());
		}
		
	}
	
	}
	

	public void checkOut() {
		System.out.println();
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방 번호 입력>>");
		String roomNumber = scan.next();
		
		//remove(key) => 삭제 성공하면 삭제된 value 값을 반환하고 실패하면 null을 반환한다.
	
		if(hotelRoomCheck.remove(roomNumber) == null) {
			System.out.println(roomNumber + "방에는 체크인한 사람이 없습니다.");
		}else {
			System.out.println("체크아웃 되었습니다.");
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		new T14_Hotel().HotelStart();
		
	}
	
	
	
}


class Hotel {
	
	private String name;
	private String roomNumber; //key
	
	public Hotel(String name, String roomNumber) {
		super();
		this.name = name;
		this.roomNumber = roomNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	@Override
	public String toString() {
		
		return "방번호 : " + roomNumber + "," + "투숙객 : " + name ;
	}

	
}