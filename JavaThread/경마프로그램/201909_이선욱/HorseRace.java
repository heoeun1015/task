package kr.or.ddit.basic.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HorseRace {
	public static int rank = 1;
	public static List<Horse> horseList = new ArrayList<Horse>();
	
	public static void main(String[] args) {
		
		horseList.add(new Horse(" 1번말"));
		horseList.add(new Horse(" 2번말"));
		horseList.add(new Horse(" 3번말"));
		horseList.add(new Horse(" 4번말"));
		horseList.add(new Horse(" 5번말"));
		horseList.add(new Horse(" 6번말"));
		horseList.add(new Horse(" 7번말"));
		horseList.add(new Horse(" 8번말"));
		horseList.add(new Horse(" 9번말"));
		horseList.add(new Horse("10번말"));
		
		BoardcastLive bl = new BoardcastLive();
		bl.start();
		
		for(int i = 0; i < horseList.size(); i++) {
			horseList.get(i).start();
		}
		
		try {
			bl.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("레이스가 끝났습니다!!!");
		System.out.println();
		
		Collections.sort(horseList);
		
		System.out.println("~~~~~~");
		System.out.println("  랭킹  ");
		System.out.println("~~~~~~");
		for (int i = 0; i < horseList.size(); i++) {
			System.out.println(horseList.get(i).gethRank() + "등" + " : " + horseList.get(i).getHorseName());
		}
	}
}

class Horse extends Thread implements Comparable<Horse> {
	private String horseName;
	private int hRank;
	private int location;

	public Horse(String horseName) {
		this.horseName = horseName;
	}
	
	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int gethRank() {
		return hRank;
	}

	public void sethRank(int hRank) {
		this.hRank = hRank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(hRank, horse.gethRank());
	}

	@Override
	public void run() {
		for(int i = 0; i < 50; i++) {
			try {
				Thread.sleep((int)(Math.random() * 750));
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			setLocation(i);
		}
		this.hRank = HorseRace.rank;
		HorseRace.rank++;
	}
}

class BoardcastLive extends Thread {
	
	@Override
	public void run() {
		String trace = "-------------------------------------------------";

		while(true) {
			int cnt = 0;
			System.out.println();
			System.out.println();
			System.out.println("==================================================");
			for(int i = 0; i < HorseRace.horseList.size(); i++) {
				if(HorseRace.horseList.get(i).getLocation() != 49) {
					System.out.print(HorseRace.horseList.get(i).getHorseName() + " | ");
					System.out.print(trace.substring(0, HorseRace.horseList.get(i).getLocation()) + "~@>");
					System.out.println(trace.substring(HorseRace.horseList.get(i).getLocation(), 49));
				}else {
					System.out.print(HorseRace.horseList.get(i).getHorseName() + " | ");
					System.out.print(trace.substring(0, HorseRace.horseList.get(i).getLocation()) + "");
					System.out.println();
					cnt++;
				}
			}
			if(cnt == 10) {
				return;
			}
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}