package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class T11_horse {
	static int rank = 1;

	public static void main(String[] args) {

		ArrayList<Horse> horse = new ArrayList<>();
		horse.add(new Horse("1번말"));
		horse.add(new Horse("2번말"));
		horse.add(new Horse("3번말"));
		horse.add(new Horse("4번말"));
		horse.add(new Horse("5번말"));
		horse.add(new Horse("6번말"));
		horse.add(new Horse("7번말"));
		horse.add(new Horse("8번말"));
		horse.add(new Horse("9번말"));
		horse.add(new Horse("10번말"));

		for (int i = 0; i < horse.size(); i++) {
			horse.get(i).start();
		}

		for (Horse h : horse) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Collections.sort(horse);

		System.out.println("경기 끝.....");
		System.out.println("--------------------------------------------");
		System.out.println();
		System.out.println("경기 결과 ");
		System.out.println("순위 : ");
		for (int i = 0; i < horse.size(); i++) {
			System.out.println(horse.get(i).rank + "등 : " + horse.get(i).getHorsename());
		}

	}
}

class Horse extends Thread implements Comparable<Horse> {
	private String horsename;
	public int rank;
	private String str = "";

	public String getHorsename() {
		return horsename;
	}

	public void setHorsename(String horsename) {
		this.horsename = horsename;
	}

	public Horse(String horsename) {
		this.horsename = horsename;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 50; i++) {
			if (i % 5 == 0) {
				str += ">";
			} else {
				str += "-";
				str = str.replace(">", "-");
			}
			try {
				Thread.sleep((int) (Math.random() * 301 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(horsename + " : " + str);
		}
		System.out.println(horsename + "도착..");
		this.rank = T11_horse.rank;
		T11_horse.rank++;
	}

	@Override
	public int compareTo(Horse o) {
		return Integer.compare(this.rank, o.rank);
	}
}