package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class T15_HotelService {


	private Scanner scan;
	private Map<String,detail>roombookMap;

	public T15_HotelService() {
		scan = new Scanner(System.in);
		roombookMap = new HashMap<>();
	} 



	public void displayMenu() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		while(true) {
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인");
		System.out.println("2.체크아웃");
		System.out.println("3.객실상태 ");
		System.out.println("4.업무종료");
		System.out.print("번호 입력");

		int menuNum= scan.nextInt();
			
			switch(menuNum) {
			case 1: insert();
				break;
			case 2: checkout();
				break;
			case 3: state();
				break;
			case 4 :
				System.out.println("프로그램을 종료합니다...");
				return;
			default :
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}



	public void state() {

		Set<String>keySet= roombookMap.keySet();
		System.out.println("===============================================");
		System.out.println("방번호\t\t\t이름");
		System.out.println("===============================================");

		if(keySet.size()==0) {
			System.out.println("등록된 방 번호 정보가 하나도 없습니다.");

		}else {
			Iterator<String> it= keySet.iterator();
			int cnt=0;
			while(it.hasNext()){
				cnt++;
				String name= it.next();
				detail p = roombookMap.get(name);

				System.out.println(""+ cnt+"\t"+p.getName()+"\t"+p.getRoomnum());
			}
		}

	}



	public void checkout() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호 입력 =>");
		String roomnum =scan.next(); 


		if(roombookMap.remove(roomnum)==null) {
			System.out.println(roomnum +"잘못 입력 하셨습니다.");
		}else {
			System.out.println(roomnum +"가 체크아웃 되었습니다.");
		}
	}



	public void insert() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 =>");
		String roomnum=scan.next();

		if(roombookMap.get(roomnum)!=null) {
			System.out.println(roomnum+"는 이미 예약되었습니다.");
			return;
		}

		System.out.println("이름 입력 =>");
		String name=scan.next();

		roombookMap.put(roomnum, new detail(roomnum,name));
		System.out.println("체크인 되었습니다");
	}



	public static void main(String[]args) {
		new T15_HotelService().displayMenu();
	}



	class detail{
		private String roomnum;
		private String name;

		public String getRoomnum() {
			return roomnum;
		}
		public void setRoomnum(String roomnum) {
			this.roomnum = roomnum;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public detail(String roomnum,String name){
			this.roomnum = roomnum;
			this.name= name;
		}
		@Override
		public String toString() {
			return "detail [roomnum=" + roomnum + ", name=" + name + "]";
		} 
	}
}

