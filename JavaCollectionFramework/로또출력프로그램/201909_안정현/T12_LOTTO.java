package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class T12_LOTTO {
	public static void main(String[] args) {
		do {
			Scanner s = new Scanner(System.in);
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println(" 2. 프로그램 종료");
			System.out.println("==========================");
			System.out.printf("메뉴선택 :");
			int select = Integer.parseInt(s.nextLine());
			if (select == 1) {
				System.out.println("LOTTO 구입 시작");
				System.out.println("1000원에 로또번호 하나입니다.");
				System.out.printf("금액 입력 : ");
				int money = Integer.parseInt(s.nextLine());
				System.out.println("행운의 로또번호는 아래와 같습니다.");
				int count = money / 1000;
				int change = money % 1000;

				for (int i = 0; i < count; i++) {
					TreeSet lotto = new TreeSet();
					for (int j = 0; j < 6; j++) {
						int lottonum = (int) (Math.random() * 45 + 1);
						lotto.add(lottonum);
					}
					System.out.print("로또번호" + (i + 1) + ": ");
					for (Object obj : lotto) {
						System.out.print(obj + " ");
					}
					System.out.println();
				}

				System.out.println("받은 금액은 : " + money + "원이고 거스름돈은 : " + change + "원 입니다.");

			} else if (select == 2) {
				System.out.println("감사합니다.");
				break;
			}
		} while (true);
	}
}
