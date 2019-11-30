package kr.or.ddit.basic;

import java.util.Scanner;
import java.util.TreeSet;

public class T13_Lotto {
	
/*	 로또를 구매하는 프로그램 작성하기
	 
	 사용자는 로또를 구매할 때 구매할 금액을 입력하고
	 입력한 금액에 맞게 로또번호를 출력한다.
	 (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
	      출력한다.)

		==========================
	         Lotto 프로그램
		--------------------------
		 1. Lotto 구입
		 2. 프로그램 종료
		==========================		 
		메뉴선택 : 1  <-- 입력
				
		 Lotto 구입 시작
			 
		(1000원에 로또번호 하나입니다.)
		금액 입력 : 2500  <-- 입력
				
		행운의 로또번호는 아래와 같습니다.
		로또번호1 : 2,3,4,5,6,7
		로또번호2 : 20,21,22,23,24,25
				
		받은 금액은 2500원이고 거스름돈은 500원입니다.
				
	   	 ==========================
	         Lotto 프로그램
		--------------------------
		  1. Lotto 구입
		  2. 프로그램 종료
		==========================		 
		메뉴선택 : 2  <-- 입력
			
		감사합니다
*/
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int menu = 0;
		do {
			try {
				System.out.println("==========================");
				System.out.println(" Lotto 프로그램");
				System.out.println("--------------------------");
				System.out.println(" 1. Lotto 구입");
				System.out.println(" 2. 프로그램 종료");
				System.out.println("==========================");
				System.out.print("메뉴선택 : ");
				menu = Integer.parseInt(s.nextLine());

				switch (menu) {
				case 1: buyLotto(); break;
				case 2:             break;
				}
			} catch (Exception e) {
				System.out.println("올바른 값을 입력해주세요.");
			}
		} while (menu != 2);

	}

	static void buyLotto() {
		Scanner s = new Scanner(System.in);

		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		
		int money = Integer.parseInt(s.nextLine());

		System.out.println("행운의 로또번호는 아래와 같습니다.");

		for (int i = 0; i < money / 1000; i++) {
			TreeSet<Integer> lotto = new TreeSet<>();
			while (lotto.size() < 6) {
				int num = (int) (Math.random() * 45) + 1;
				lotto.add(num);
			}
			System.out.println("로또번호" + (i + 1) + " : " + lotto);
		}
		System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + money % 1000 + "원입니다.");

	}
}
