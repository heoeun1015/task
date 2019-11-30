package homework_01;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.jar.Attributes.Name;




public class Hotel_01 {


	public static void main(String[] args) {
		new Hotel_01().hotelStart();
		
	}

	private Scanner scan;
	private Map<String, Hotel> HotelMap;

	public Hotel_01() {
		scan = new Scanner(System.in);
		HotelMap = new HashMap<>();

	}
	public void menu() {// 메뉴를 출력하는 메서드
		System.out.println();
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인");
		System.out.println("2. 체크아웃");
		System.out.println("3. 객실상태");
		System.out.println("4. 나가기");
		System.out.println();
		System.out.println("입력 : ");
	}
	public void hotelStart() {// 호텔프로그램을 시작하는 메서드
		System.out.println("--------------------------------------");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("--------------------------------------");
		System.out.println();
		
		while(true) {
			menu(); // 메뉴창 출력
			int num = scan.nextInt(); // 메뉴 입력창
			
			switch(num) {
			case 1 : checkin(); // 체크인
				break;
			case 2 : checkout(); // 체크 아웃
				break;
			case 3 : roomstate(); // 객실상태
				break;
			case 4 : 
				System.out.println("호텔을 나왔습니다.");
				return;
			default : 
				System.out.println("잘못입력했습니다. 다시 입력해주세요.");
			}
		}
	}
	private void checkin() {// 체크인 메서드
		System.out.println();
		System.out.println("********************");
		System.out.println("객실을 선택해주세요.");
		System.out.println("********************");
		System.out.println("객실 번호  : ");
		String roomnum = scan.next(); 
		
		if(HotelMap.get(roomnum) != null) {
			System.out.println(roomnum + "방은 이미 체크인 된 상태입니다.");
			return;
		}
			String name ="";
			System.out.println("-------------------------");
			System.out.println("등록할 사람 이름 : ");
			name = scan.next();
			System.out.println(name + "체크인 되었습니다.");
		
		//name = scan.nextLine();
		HotelMap.put(roomnum, new Hotel(roomnum, name));
		System.out.println(name +"씨  " +roomnum +"번 방 등록이 완료되었습니다.");
		
	}
	private void checkout() { // 체크아웃
		System.out.println();
		System.out.println("************************");
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("************************");
		System.out.println("방 번호 : ");
		String roomnum = scan.next(); 
		
		if(HotelMap.remove(roomnum) == null) {
			System.out.println(roomnum + "은  예약된 방이 아닙니다.");
		}else {
			System.out.println(roomnum +"번 방이 체크아웃 되었습니다.");
		}
	}
	private void roomstate() {
		Set<String> keySet = HotelMap.keySet();
		System.out.println();
		System.out.println("*************************");
		System.out.println("방 번호 \t\t 이름");
		System.out.println("*************************");
		
		if(keySet.size() == 0) {
			System.out.println("예약된 방이 없습니다.");
		}else {
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()) {
				String roomnum = it.next();
				Hotel h = HotelMap.get(roomnum);
				System.out.println( h.getName()+"\t\t" +h.getRoomnum() );
			}
		}
		System.out.println();
		System.out.println("*******확인 완료*********");
	}

}


class Hotel { // 호텔 예약 Vo
	private String name; // 이름
	private String roomnum; // 객실번호
	
	public Hotel(String name, String roomnum) {
		super();
		this.name = name;
		this.roomnum = roomnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomnum() {
		return roomnum;
	}

	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", roomnum=" + roomnum + "]";
	}
	
	
}
