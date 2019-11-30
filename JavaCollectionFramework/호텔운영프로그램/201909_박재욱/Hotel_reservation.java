package kr.or.ddit.homework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day_four {
	private Scanner s; 
	private Map<String, hotel> hotelMap; // hotel 클래스를 받아서 해쉬맵 생성
	
	public Day_four() {
		s = new Scanner(System.in);
		hotelMap = new HashMap<>();
	}
	
	//메뉴 출력
	public void displayMenu() {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		
	}
	// 프로그램 시작 메소드
	public void hotelStart() {
		
		System.out.println();
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		while(true) {
			displayMenu();
			 // 메뉴 출력
			int menuNum = Integer.parseInt(s.nextLine());
			switch(menuNum) {
				case 1:
					checkIn();
					break;
				case 2:
					checkOut();
					break;
				case 3:
					roomSit();
					break;
				case 4:
					System.out.println("업무종료");
					System.exit(0);
				default:
					System.out.println("다시 입력하세요.");
			}
		}
	}
	public void checkOut() {
		System.out.println();
		System.out.println("방 번호를 입력해주세요.");
		String room = s.nextLine();
		
		if(hotelMap.remove(room)==null) {
				System.out.println(room + "에는 아무도 없습니다.");
		}else {
			System.out.println(room + "방이 체크아웃 되었습니다.");
		}
	}
	
	public void roomSit() {
		Set<String> keySet = hotelMap.keySet();
		System.out.println("==================================");
		System.out.println("번호\t이름\t방 번호");
		System.out.println("==================================");
		if(keySet.size()==0) {
			System.out.println("체크인 된 방이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				String room = it.next();
				hotel h = hotelMap.get(room);
				System.out.println(" " + cnt +"\t"+h.getName()
				+"\t" + h.getRoom());
			}
		}
		
	}


	public void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방 번호 입력 >>");
		String room = s.nextLine();
		
		// 이미 등록한 방인지 검사
		if(hotelMap.get(room) !=null) {
			System.out.println(room + "이 이미 등록되어 있습니다.");
			return;
		}
		System.out.println("이름을 입력하세요");
		String name = s.nextLine();
		hotelMap.put(room, new hotel(room, name));
		System.out.println(room + "번 방 등록 완료");
	}

	public static void main(String[] args) {
		new Day_four().hotelStart();
	}
}
	
class hotel{
	private String room;  // 키 값
	private String name;

	
	public hotel(String room, String name) {
		super();
		this.room = room;
		this.name = name;
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String toString() {
		return "Hotel[room=" + room + ",name= " + name +"]";
				
	}
}