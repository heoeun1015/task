package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T16_HorseRacingTest {

	public static int rank = 1;
	public static List<Horse> horesList = new ArrayList<Horse>();
	
	public static void main(String[] args) {
		
		horesList.add(new Horse("01번말"));
		horesList.add(new Horse("02번말"));
		horesList.add(new Horse("03번말"));
		horesList.add(new Horse("04번말"));
		horesList.add(new Horse("05번말"));
		horesList.add(new Horse("06번말"));
		horesList.add(new Horse("07번말"));
		horesList.add(new Horse("08번말"));
		horesList.add(new Horse("09번말"));
		horesList.add(new Horse("10번말"));
		
		HorseRun hpd = new HorseRun();
		hpd.start();

		for(int i = 0; i < horesList.size(); i++) {
			horesList.get(i).start();
		}

		try {
			hpd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println();
		System.out.println("▷ 경마가 종료되었습니다.");
		System.out.println();

		Collections.sort(horesList); // 리스트를 순위 오름차순으로 정렬하기
		
		System.out.println("━━━━━━━━━━━━");
		System.out.println("   경마 순위    ");
		System.out.println("━━━━━━━━━━━━");
		for (int i = 0; i < horesList.size(); i++) {
			System.out.println(horesList.get(i).getHorseRank() + "등" + " → " + horesList.get(i).getHorseName());
		}
		
	}
}

class Horse extends Thread implements Comparable<Horse> {
	
	private String horseName;
	private int horseRank;
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

	public int getHorseRank() {
		return horseRank;
	}

	public void setHorseRank(int horseRank) {
		this.horseRank = horseRank;
	}

	public int getlocation() {
		return location;
	}

	public void setlocation(int location) {
		this.location = location;
	}
	
	@Override
	public int compareTo(Horse o) {
		return Integer.compare(horseRank, o.getHorseRank());
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) { 
			try {
				Thread.sleep((int) (Math.random() * 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setlocation(i);
		}
		this.horseRank = T16_HorseRacingTest.rank;
		T16_HorseRacingTest.rank++;
	}
}


class HorseRun extends Thread {
	
	public void clear() {
		for (int i = 0; i < 33; i++) {
			System.out.println();
		}
	}

	@Override
	public void run() {
		String course = "──────────────────────────────────────────────────";
		
		while (true) {
			clear();
			int cnt = 0;
			System.out.println("▷ 경마가 시작되었습니다.");
			System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
			System.out.println();
			for (int i = 0; i < T16_HorseRacingTest.horesList.size(); i++) {
				if (T16_HorseRacingTest.horesList.get(i).getlocation() != 49) {
					System.out.print(T16_HorseRacingTest.horesList.get(i).getHorseName() + " : ");
					System.out.print(course.substring(0, T16_HorseRacingTest.horesList.get(i).getlocation()) + ">");
					System.out.println(course.substring(T16_HorseRacingTest.horesList.get(i).getlocation(), 49));
				} else {
					System.out.print(T16_HorseRacingTest.horesList.get(i).getHorseName() + " : ");
					System.out.print(course.substring(0, T16_HorseRacingTest.horesList.get(i).getlocation()) + " 도착");
					System.out.println();
					cnt++;
				}
			}

			if (cnt == 10) {
				return;
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
