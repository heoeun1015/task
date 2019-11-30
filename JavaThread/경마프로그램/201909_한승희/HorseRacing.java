package report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseRacing {
	public static List<Horse> horse = new ArrayList<Horse>();
	
	public static void main(String[] args) {
		//말이름 넣기 (반드시 3마리 이상 넣고 시작할 것.)
		//메인 메서드의 마지막에 1, 2, 3등의 말들을 출력한 후 마무리하기 때문에
		//말을 생성하지않으면 오류가 발생한다.
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
		
		// Racing쓰레드가 get(index)로 동작하여 가장 먼저 선언된 말이 더 유리할 수
		//있으므로 shuffle하여 공정함을 중시한다.
		Collections.shuffle(horse);
		
		//racing 쓰레드 시작
		//멀티 쓰레드로 구성하려고 생각해보았지만 이 기능의 속도는 비교적 중요한 것이 아니며,
		//제일 마지막에 들어오는 말을 체크하는 로직에서 충돌이 날 수 있다. yield를 사용하면
		//충돌을 방지할 수 있지만 그렇게 했을 때 싱글 쓰레드와 다를 것이 없다고 생각된다.
		Racing race = new Racing();
		
		race.start();
		
		//join으로 racing동작할 동안 시간벌기
		//join말고 다른것으로도 대체가능.
		try {
			race.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		//순위대로 출력하기 위해서 정렬
		Collections.sort(horse);
		
		System.out.println("Rank\tHorseName");
		for(int i=0;i<horse.size();i++)
		{
			//toString을 오버라이드했다.
			System.out.println(horse.get(i));
		}
		
		System.out.println("\n" + "1등은 " + horse.get(0).getHorseName()
				+ " 2등은 " + horse.get(1).getHorseName()
				+ " 3등은 " + horse.get(2).getHorseName() + "입니다.");
		
	}
}

class Horse implements Comparable<Horse>{

	String horseName;
	int location, tempLocation;
	int rank;

	public Horse(String name) {
		super();
		this.horseName = name;
		location = 0;
		tempLocation = 0;
		rank = 0;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
	public int getTempLocation() {
		return tempLocation;
	}
	
	public void setTempLocation(int tempLocation) {
		this.tempLocation = tempLocation;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String location()
	{
		String str=" ";
		
		for(int i=1;i<location;i++)
		{
			str += "-";
		}
		str += ">";
		if(location<50)
		{
			for(int i=location+1;i<=50;i++)
			{
				str += "-";
			}
		}
		
		return horseName + str;
	}
	
	@Override
	public String toString() {
		return rank + "등\t" + horseName;
	}

	@Override
	public int compareTo(Horse o) {
		return Integer.compare(getRank(), o.getRank());
	}
	
}

class Racing extends Thread
{
	@Override
	public void run() {
		boolean locationCheck;
		int rank = 0;
		
		while(true)
		{
			locationCheck=false;
			
			for(int i=0;i<HorseRacing.horse.size();i++)
			{
				if(HorseRacing.horse.get(i).location < 50)
				{
					locationCheck = true;
				}
				
				//등수를 정하기위해 추가. 임시 위치에 위치 대입
				HorseRacing.horse.get(i).setTempLocation(
						HorseRacing.horse.get(i).getLocation());
			}
			
			System.out.println();		//간격
			
			if(locationCheck)
			{
				for(int i=0;i<HorseRacing.horse.size();i++)
				{
					int tempLocation =
							HorseRacing.horse.get(i).getLocation()
							+ ((int)(Math.random()*2)+1);
					
					if(tempLocation >= 50)
					{
						HorseRacing.horse.get(i).setLocation(50);
						if(HorseRacing.horse.get(i).getTempLocation()!=50)
						{	//랭크
							HorseRacing.horse.get(i).setRank(++rank);
						}
					}
					else
					{
						HorseRacing.horse.get(i).setLocation(tempLocation);
					}
					System.out.println(HorseRacing.horse.get(i).location());
				}
				
				try {
					Thread.sleep(50);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
			{
				break;
			}
		}
	}
}