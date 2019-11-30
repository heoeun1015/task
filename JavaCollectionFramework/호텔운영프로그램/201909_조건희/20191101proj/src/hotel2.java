import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class hotel2 {
	Scanner scan = new Scanner(System.in);		
	HashMap<String, hotelVO2>HotelMap = new HashMap<String, hotelVO2>();
	
	public static void main(String[] args) {
			hotel2 hotel = new hotel2();
			hotel.start();
	}
	
	public void start() {
		while(true) {					
			displayMenu();
			int input = scan.nextInt();
			switch(input) {
			case 1: insert(); break;
			case 2: checkout(); break;
			case 3: state(); break;
			case 4: modify(); break;
			case 5: break;
			}
		}	
	}
	
	public void checkout() {
		System.out.println("****반납할 방을 입력해주세요***");
		String Room = scan.next();
		
		if(HotelMap.remove(Room) == null) {
			System.out.println(Room + "방이 존재하지 않습니다.");
		} else {
			System.out.println(Room + "방을 삭제하였습니다.");
		}			
	}																			

	public void displayMenu() {
		System.out.println("**************************\r\n" + 
				"호텔 문을 열었습니다.\r\n" + 
		   "**************************\r\n" + 
			"\r\n" + 
			"*******************************************\r\n" + 
			"어떤 업무를 하시겠습니까?\r\n" + 
			"1.체크인  2.체크아웃 3.객실상태 4. 객실 수정하기 5.업무종료");
	}
	public void insert() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 =>");
		String Rmnum = scan.next();
		
		if(HotelMap.get(Rmnum) != null) {
			System.out.println(Rmnum + "이 이미 등록되었습니다.");
			return;
		}
		
		System.out.println("이름을 입력하세요");
		String gstName = scan.next();
		
		HotelMap.put(Rmnum, new hotelVO2(Rmnum, gstName));
		System.out.println(Rmnum + "방이 정상적으로 등록되었습니다");
				
	}
	public void state() {
		if(HotelMap.isEmpty()) {
			System.out.println("방이 비었습니다.");
			return;
		}
		
		Set<String> keySet = HotelMap.keySet();		
		System.out.println("***************현재 방 정보들**********************");
		for(hotelVO2 value : HotelMap.values()) {
			
			System.out.println("게스트 이름 " +value.GstName);
			System.out.println("방 번호 " + value.Rmnum);
		}
		System.out.println("***************************************************");
		System.out.println("");
	}
	
	public void modify() {			
		System.out.println("수정할 방을 입력해주세요");
		String room = scan.next();
		
		if(HotelMap.get(room) == null) {
			System.out.println(room + "이 존재하지 않습니다.");
		}
		
		System.out.println("이름 입력");
		String GstName = scan.next();
		
		HotelMap.put(room, new hotelVO2(room,GstName));
		System.out.println("정상적으로 수정되었습니다.");		
	}
}

class hotelVO2 {
	String Rmnum;
	String GstName;
	
	public hotelVO2(String rmnum, String gstName) {
		super();
		Rmnum = rmnum;
		GstName = gstName;
	}

	public String getRmnum() {
		return Rmnum;
	}

	public void setRmnum(String rmnum) {
		Rmnum = rmnum;
	}

	public String getGstName() {
		return GstName;
	}

	public void setGstName(String gstName) {
		GstName = gstName;
	}
	
	
}
