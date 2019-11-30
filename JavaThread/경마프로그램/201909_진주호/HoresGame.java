package kr.or.ddit.basic;

public class HoresGame {
	static String strRank =""; // 순위저장
 	public static void main(String[] args) {
 		HoresGame1[] game = new HoresGame1[] {
 				new HoresGame1("1번 말"),
 				new HoresGame1("2번 말"),
 				new HoresGame1("3번 말"),
 				new HoresGame1("4번 말"),
 				new HoresGame1("5번 말")
 		};
 		for(int i=0; i<game.length; i++) {
 			game[i].start();
 		}
 		for(HoresGame1 hg : game) {
 			try {
 				hg.join();
 			}catch(InterruptedException e) {
 				e.printStackTrace();
 			}
 		}
 		System.out.println("경기 끝....");
 		System.out.println("--------------------------------");
 		System.out.println();
 		System.out.println("경기결과");
 		System.out.println("순위 : " + strRank);
 		
 		
	}
}

class HoresGame1 extends Thread{
	private String name;

	public HoresGame1(String name) {
		super();
		this.name = name;
	}
	@Override
	public void run() {
		
		for(int i = 0; i<=50; i++) {
			System.out.println();
			System.out.print("☆" + name);
			for(int j=0; j<=50; j++) {
				if(i>j) {
					System.out.print("-");
				}else if(i==j) {
					System.out.print(">");
				}else if(j==50){
					System.out.println("■■");
				}
			}
			System.out.println();
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

			try {
				Thread.sleep((int)(Math.random()*500));
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(name + "출력끝...");
		HoresGame.strRank += name + "";
	}

}