package kr.or.ddit.basic;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class HotelManage {
	private Scanner scan;
	private Map<String, Hotel> hotelTest;
	
	public HotelManage() {
		scan = new Scanner(System.in);
		hotelTest = new HashMap<>();
	}
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("***********************************************");
		System.out.println("메뉴를 선택하세요.");
		System.out.print(" 1. 체크인");
		System.out.print(" 2. 체크아웃");
		System.out.print(" 3. 객실상태");
		System.out.println(" 4. 업무종료");
		System.out.println("***********************************************");
		System.out.print(" 메뉴선택 >> ");		
	}
	
	// 프로그램을 시작하는 메서드
	public void hotelStart(){
		System.out.println("===============================================");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("===============================================");
		
		while(true){
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			
			switch(menuNum){
				case 1 : checkin();		// 체크인
					break;
				case 2 : checkout();		// 체크아웃
					break;
				case 3 : room();		// 객실상태
					break;
				case 4 :
					System.out.println("호텔 문을 닫았습니다.");
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}
		
	
	
	private void room() {
		Set<String> keySet = hotelTest.keySet();
		
		
		if(keySet.size() == 0) {
			System.out.println("등록된 방이 없습니다");
		}else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				
				String name = it.next();//키값
				Hotel p = hotelTest.get(name);
				System.out.println("방번호 : " + p.getnum() + "\t 투숙객 : " + p.getname());
			}
		}
	}

	//호텔 체크아웃하는 메서드(삭제)
	private void checkout() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String num = scan.next();
		
		
		if(hotelTest.remove(num) == null) {
			System.out.println(num + "방에는 체크인한 사람이 없습니다.");
		}else {
			System.out.println("체크아웃 되었습니다.");
		}
		
		
		
		
	}

	//체크인 하는 메서드
	private void checkin() {
		System.out.println();
		System.out.println("어느 방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 >> ");
		String num = scan.next();
		
		//이미 등록된 방인지 검사
		//get()메서드로 값을 가져올 때 가져올 자료가 없으면 null을 반환함
		if(hotelTest.get(num) != null) {
			System.out.println(num + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		
		hotelTest.put(num, new Hotel(num, name));
		System.out.println("체크인 되었습니다.");
		
	}

	public static void main(String[] args) {
		new HotelManage().hotelStart();
		
	}
	
	

}


class Hotel{
	private String num;		//방번호
	private String name;	//이름
	
	
	public Hotel(String num, String name) {
		super();
		this.num = num;
		this.name = name;		
	}

	
	


	public String getnum() {return num;}
	public void setTel(String num) {this.num = num;}
	
	public String getname() {return name;}
	public void setName(String name) {this.name = name;}
	
/*	@Override
	public String toString() {
		
		return ;
	}
	*/
	
	
	
	
}

