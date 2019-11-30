package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class HotelTest {

	private Scanner s = new Scanner(System.in);
	private HashMap<Integer, HotelRoom> checkMap = new HashMap<>();
	//방번호가 기본키, 방번호와 투숙객을 저장할 맵
	
	public static void main(String[] args) { //메인
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		new HotelTest().hotelStart();
		System.out.println();
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");

	}

	private void hotelStart() { //초기 화면
		while(true) {
			System.out.println();
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴 선택 => ");
			int select = Integer.parseInt(s.nextLine());
			switch(select) {
			case 1 : checkin(); break;
			case 2 : checkout(); break;
			case 3 : roomState(); break;
			case 4 : return;
			default : break;
			}
		}
		
	}

	private void checkin() { //체크인 메서드
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방 번호 입력 => ");
		int roomNum = Integer.parseInt(s.nextLine());
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String guest = s.nextLine();
		if(checkMap.get(roomNum) != null) { //맵에 데이터가 있다면
			System.out.println(roomNum + "방에는 이미 사람이 있습니다."); return;
		}
		checkMap.put(roomNum, new HotelRoom(roomNum, guest));
		System.out.println("체크인 되었습니다.");
	}

	private void checkout() { //체크아웃 메서드
		System.out.println();
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방 번호 입력 => ");
		int roomNum = Integer.parseInt(s.nextLine());
		if(checkMap.get(roomNum) == null) { //맵에 데이터가 없다면
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다."); return;
		}
		checkMap.remove(roomNum);
		System.out.println("체크아웃 되었습니다.");
	}
	
	private void roomState() { //객실 상태 메서드
		System.out.println();
		if(checkMap.size() == 0) { //맵의 크기가 0일 때
			System.out.println("현재 객실은 모두 투숙가능합니다."); return;
		}
		Set<Integer> roomNumSet = checkMap.keySet();
		for(int roomNum : roomNumSet) {
			System.out.println("방 번호 : " + checkMap.get(roomNum).getRoomNum()
								+ ", 투숙객 : " + checkMap.get(roomNum).getGuest());
		}
	}
}

class HotelRoom { 
	private int roomNum;
	private String guest;
	
	public HotelRoom(int roomNum, String guest) {
		super();
		this.roomNum = roomNum;
		this.guest = guest;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}
	
	
}
