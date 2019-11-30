package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LottoTest {

	public static void main(String[] args) {

		Set lotto = new HashSet<>();
		Scanner s = new Scanner(System.in);

		int cash = 0;
		int line = 0;
		int nmg = 0;
		int menu = 0;

		while (true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.println("");
			System.out.println("");
			menu = Integer.parseInt(s.nextLine());

			if (menu == 1) {

				System.out.println("(1000원에 로또번호 하나입니다.)\r\n" + "금액 입력 :");
				cash = Integer.parseInt(s.nextLine());
				line = cash / 1000;
				nmg = cash % 1000;

				for (int i = 0; i < line; i++) {
					while (lotto.size() < 6) {
						int num = (int) (Math.random() * 45 + 1);
						lotto.add(num);
					}
					System.out.println("로또번호"+(i+1)+" : "+lotto);
					lotto.clear();
				}
				System.out.println("받은 금액은 " + cash + "이고 거스름돈은 " + nmg + "원 입니다.");
			}
			if (menu == 2) {
				System.out.println("감사합니다.");
				break;
			}

		}
	}

}
