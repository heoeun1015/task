package HomeWork;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {

	static Scanner s = new Scanner(System.in);
	static Map<String, HotelList> hotelInfo = new HashMap<>();
	
	static String roomNum;
	static String name;
	
	public static void main(String[] args) {
		runHotel();
	}

	private static void runHotel() {
		System.out.println("*****************************");
		System.out.println("	호텔 문을 열었습니다");
		System.out.println("*****************************");
		while(true) {
			System.out.println("*****************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*****************************");
			System.out.print("메뉴선택 >> ");
			int menuNum = Integer.parseInt(s.nextLine());
			
			if(menuNum==1) {
				checkIn();
			}else if(menuNum==2) {
				checkOut();
			}else if(menuNum==3) {
				state();
			}else if(menuNum==4) {
				System.out.println("*****************************");
				System.out.println("	호텔 문을 닫았습니다");
				System.out.println("*****************************");
				return;
			}else {
				System.out.println("잘못 눌렀습니다. 메인메뉴로 돌아갑니다.");
			}
		
		}
	}

	private static void checkIn() {
		
		while(true) {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 >> ");
			roomNum = s.nextLine();
			if(hotelInfo.get(roomNum)!=null) {
				System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			}else {
				break;
			}
		}
		System.out.println();
		System.out.println("누구를 체크인하시겠습니까?");
		System.out.print("이름 입력 >> ");
		name = s.nextLine();
		
		hotelInfo.put(roomNum, new HotelList(roomNum,name));
		System.out.println("체크인 되었습니다. 객실상태메뉴에서 확인할 수 있습니다.");
		
	}

	private static void checkOut() {
		while(true) {
			System.out.println();
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			System.out.print("방번호 입력 >> ");
			roomNum = s.nextLine();
			if(hotelInfo.get(roomNum)==null) {
				System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			}else {
				break;
			}
		}
		
		hotelInfo.remove(roomNum);
		System.out.println("체크아웃 되었습니다. 객실상태메뉴에서 확인할 수 있습니다.");
		
	}

	private static void state() {
		System.out.println();
		Set<String> keySet = hotelInfo.keySet();
		if(keySet.size() ==0) {
			System.out.println("배정된 방이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String roomNum = it.next();
				HotelList h = hotelInfo.get(roomNum);
				
				System.out.println("방번호 : " + h.getRoomNum() + ", 투숙객 : " + h.getName());
			}
		}
		
		
		
	}

}

class HotelList {
	private String roomNum;
	private String name;  
	
	public HotelList(String roomNum, String name) {
		this.roomNum = roomNum;
		this.name = name;
	}

	public String getRoomNum() {return roomNum;}
	public void setRoomNum(String roomNum) {this.roomNum = roomNum;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	
}