package kr.or.ddit.basic;

import java.util.Scanner;
import java.util.TreeSet;

/*로또를 구매하는 프로그램 작성하기

사용자는 로또를 구매할 때 구매할 금액을 입력하고
입력한 금액에 맞게 로또번호를 출력한다.
(단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
     출력한다.)
*/
public class T11_Lotto {
	public static void main(String[] arg) {
		menu();
	}
	private static void menu() {
		Scanner s = new Scanner(System.in);
		
		System.out.println("================");
		System.out.println("Lotto 프로그램");
		System.out.println("----------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("================");
		String input = s.nextLine();
		switch(input) {
			case "1" : lotto();break;
			case "2" : System.out.println("종료되었습니다."); break;
		}
	}
	private static void lotto() {
		Scanner s = new Scanner(System.in);
		TreeSet<Integer> numList = new TreeSet<>();
		System.out.println("lotto 구입 시작");
		System.out.println("1000원에 로또번호 하나입니다.");
		System.out.print("금액 입력 : ");
		int money = Integer.parseInt(s.nextLine());
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		 
		for(int i = 1; i <= money/1000; i++ ) {
			
			while(numList.size() < 6) {// Set의 데이토가 5개가 될때까지 반복
				int num = (int)(Math.random()* 45 + 1); // 1~45사이 난수
				numList.add(num);
			}
			System.out.println("로또번호"+ i + " : " + numList);
			numList.clear();
			
		}
		System.out.println("받은 금액은 " + money + "이고 거스름돈은" + money%1000 + "원 입니다.");
		menu();
	}
}
















