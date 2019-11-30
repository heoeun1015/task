package report;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hottel {
	static Scanner s = new Scanner(System.in);
	static Map<Integer, Guest> guestMap = new HashMap<Integer, Guest>();
	
	public static void main(String[] args) {
		int numberMenu=0;
		
		System.out.println("**************************\r\n" + 
				"호텔 문을 열었습니다.\r\n" + 
				"**************************");
		
		while(numberMenu!=4)
		{
			System.out.print("\r\n" + 
					"*******************************************\r\n" + 
					"어떤 업무를 하시겠습니까?\r\n" + 
					"1.체크인  2.체크아웃 3.객실상태 4.업무종료\r\n" + 
					"*******************************************\r\n" + 
					"메뉴선택 => ");
			numberMenu = Integer.parseInt(s.nextLine());
			
			switch(numberMenu)
			{
				case 1:
					checkIn();
					break;
				case 2:
					checkOut();
					break;
				case 3:
					roomInfo();
				default:
					break;
			}
		}
		
		System.out.println("**************************\r\n" + 
				"호텔 문을 닫았습니다.\r\n" + 
				"**************************");
		s.close();
	}

	private static void roomInfo() {
		Set<Integer> keySet = guestMap.keySet();
		
		Iterator<Integer> it = keySet.iterator();
		while(it.hasNext())
		{
			int key = it.next();
			System.out.println("방번호 : "+guestMap.get(key).getRoomNumber()+
			", 투숙객 : "+guestMap.get(key).getName());
		}
	}

	private static void checkOut() {
		int roomNumber=0;
		System.out.print("\n어느방을 체크아웃 하시겠습니까?\r\n" + 
				"방번호 입력 => ");
		roomNumber = Integer.parseInt(s.nextLine());
		
		if(guestMap.get(roomNumber)!=null)
		{
			guestMap.remove(roomNumber);
			System.out.println("체크아웃 되었습니다.");
		}
		else
		{
			System.out.println(roomNumber+"방에는 체크인한 사람이 없습니다.");
		}
	}

	private static void checkIn() {
		System.out.print("어느방에 체크인 하시겠습니까?\r\n" + 
				"방번호 입력 => ");
		int roomNumber = Integer.parseInt(s.nextLine());
		System.out.print("누구를 체크인 하시겠습니까?\r\n" + 
				"이름 입력 => ");
		String guestName = s.nextLine();
		
		if(guestMap.get(roomNumber)!=null)
		{
			System.out.println(roomNumber + "방에는 이미 사람이 있습니다.");
			return;
		}
		
		Guest guest = new Guest(roomNumber, guestName);
		
		guestMap.put(roomNumber, guest);
		System.out.println("체크인 되었습니다.");
	}
}

class Guest
{
	private int roomNumber;
	private String name;
	
	public Guest(int roomNumber, String name) {
		super();
		this.roomNumber = roomNumber;
		this.name = name;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
