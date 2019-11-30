public class horse {
	static boolean finalhor;
	static int rank = 1; //순위저장
	
	public static void main(String[] args) {
		Displayhorse [] dishorse = new Displayhorse[] {
				new Displayhorse("1번마", new Rank()),	
				new Displayhorse("2번마", new Rank()),	
				new Displayhorse("3번마", new Rank()),
				new Displayhorse("4번마", new Rank()),
				new Displayhorse("5번마", new Rank()),
				new Displayhorse("6번마", new Rank()),
				new Displayhorse("7번마", new Rank()),
				new Displayhorse("8번마", new Rank()),
				new Displayhorse("9번마", new Rank()),
				new Displayhorse("10번마", new Rank()),				
			};
			for(int i=0; i<dishorse.length;i++) {
				dishorse[i].start();
			}
			// join 문장이 나오면 dc가 끝날떄 까지 아래 문장이 실행이 안된다.
			for(Displayhorse dc : dishorse) {
				try{
					dc.join();
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("경기 끝");
			System.out.println("--------------------------");
			System.out.println();
//			finalhor = true;

	}
}

class Displayhorse extends Thread {
	
	static boolean finalhor;
	private String name;
	static int count = 1;
	Rank rank;
	public Displayhorse(String name) {
		this.name = name;
	}
	
	public Displayhorse(String name, Rank rank) {
		this.name = name;
		this.rank = rank;
	}

	@Override
	public void run() {
		// i = 0,1 j = 0
		for(int i= 0; i<= 20; i++) {						
			System.out.println(name);
			
			for(int j = 0; i > j; j++) {				
				System.out.print("*");
			}
			try {
				//sleep()메서드의 값을 200~500사이의 난수로 한다.
				Thread.sleep((int)(Math.random()*301+200));
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.rank.finishLine(name);
//		System.out.println(name + "완주" + horse.rank);
		
		
		}
	
}

class Rank {
	
	int rank;
	
	public Rank() {
		
	}	
	
	public void finishLine(String name) {
		rank = Displayhorse.count++;
		System.out.println(name + " 말 " + rank + "등으로 결승점 도착");
					
	}
	
}