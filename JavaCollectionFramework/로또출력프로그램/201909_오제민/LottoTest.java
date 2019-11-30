package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;

public class LottoTest {
/*
 * 로또를 구매하는 프로그램 작성하기
 
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
		while(true) {
			int select;
			System.out.println("==========================");
			System.out.println("       Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("  1. Lotto 구입");
			System.out.println("  2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴 선택 : ");
			select = Integer.parseInt(s.nextLine());
			if(select == 1) {
				buyLotto();
			}else if(select == 2) {
				System.out.println();
				System.out.println("감사합니다.");
				break;
			}
		}
	}
	public static void buyLotto() {
		Scanner s = new Scanner(System.in);
		int insertMoney;
		System.out.println();
		System.out.println(" Lotto 구입 시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		insertMoney = Integer.parseInt(s.nextLine());
		System.out.println();
		if(insertMoney < 1000) {
			System.out.println("금액이 부족합니다."); return;
		}
		System.out.println("행운의 로또번호는 아래과 같습니다.");
		int lottoCnt = insertMoney / 1000; //로또 횟수
		int change = insertMoney % 1000; // 거스름돈
		for(int i = 0; i < lottoCnt; i++) {
			HashSet<Integer> lotto = new HashSet<>();
			while(lotto.size() < 6) {
				lotto.add((int)(Math.random() * 45 + 1));
			}
			System.out.println("로또번호" + (i + 1) + " : " + lotto);
		}
		System.out.println();
		System.out.println("받은 금액은 " + insertMoney + "원이고 거스름돈은 " + change + "원입니다.");
		System.out.println();
	}

}
