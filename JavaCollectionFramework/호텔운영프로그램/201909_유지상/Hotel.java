package study;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {

	public static void main(String[] args) {
		System.out.println("*************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("*************************");
		new Hotel().Start();
	}
	
	//======================================================
	// Map<key, value>
	Scanner scan;
	Map<String, Hotel_VO> hotelMap;

	public Hotel() {
		scan = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	//======================================================

	
	public void Start() {
		int menu = 99;
		do {
		try {
		
		System.out.println();
		System.out.println("********************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인 | 2. 체크아웃 | 3. 객실 상태 | 4. 업무 종료");
		System.out.println("********************************************");
		System.out.print("메뉴 선택 -> ");
		menu = Integer.parseInt(scan.nextLine());
		
			switch(menu) {
				case 1: checkIn(); 		break;
				case 2: checkOut();		break;
				case 3: roomStatus();	break;
				case 4: end();			break;
			}
			
		}catch(Exception e) {
			System.out.println("숫자로 입력해주세요.\n");
		}
		}while(menu != 4);
	}

	
	public void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 -> ");
		String roomNumber = scan.nextLine();
		
		System.out.println("\n누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 -> ");
		String name = scan.nextLine();
		
		//방이 비어있는지 검사
		if(hotelMap.get(roomNumber) != null) {
			System.out.println(roomNumber+"방에는 이미 사람이 있습니다.");
			return;
		}
		
		//빈 방이라면 입력된 정보를 맵에 저장
		hotelMap.put(roomNumber, new Hotel_VO(roomNumber, name));
		System.out.println("체크인 되었습니다.");
	}
	
	
	public void checkOut() {
		System.out.println();
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 -> ");
		String roomNumber = scan.nextLine();
		
		if(hotelMap.get(roomNumber) == null) {
			System.out.println(roomNumber+"방에는 체크인한 사람이 없습니다.");
			return;
		}
		hotelMap.remove(roomNumber);
		System.out.println("체크아웃 되었습니다.");
	}
	
	
	public void roomStatus() {
		Set<String> room = hotelMap.keySet();
		
		System.out.println("\n===========================================");
		if(room.size() == 0) {
			System.out.println("체크인 된 방이 없습니다.");
			System.out.println("===========================================");
			return;
		}
		
		Iterator<String> it = room.iterator();
		while(it.hasNext()) {
			String roomNum = it.next();
			Hotel_VO status = hotelMap.get(roomNum);
			System.out.println("방번호 : " + status.getRoomNumber() + " 투숙객 : " + status.getName());
		}
		System.out.println("===========================================");
	}
	
	public void end() {
		System.out.println();
		System.out.println("*************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("*************************");
	}
	
}

/*
 * 저장해야하는 데이터: 방번호(key), 이름 
*/
class Hotel_VO{
	String name;
	String roomNumber; //key
	
	public Hotel_VO(String roomNumber, String name) {
		super();
		this.roomNumber = roomNumber;
		this.name = name;
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
	
}