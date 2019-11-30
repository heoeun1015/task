package kr.or.ddit.basic;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {

	public static void main(String[] args) {
		
		LottoAuto lt = new LottoAuto();
		lt.lottoPrint();
		
		
	}

}
class LottoAuto {
	
	int input1;
	int money;

	void lottoPrint() {
		Scanner sn = new Scanner(System.in);
		
		System.out.println("============================");
		System.out.println("\t Lotto 프로그램 \t");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("============================");
		System.out.print("메뉴선택 : ");
		int input1 = Integer.parseInt(sn.nextLine());
		int money = 0;
		if(input1 == 1) {
			System.out.println("(1000원에 로또번호 하나입니다.)");
			System.out.print("금액 입력 : ");
			money = Integer.parseInt(sn.nextLine());
			System.out.println("행운의 로또번호는 아래와 같습니다.");

			for(int i = 1; i <= (money / 1000); i++) {
				Set<Integer> ts = new TreeSet<>();
				for(int j = 0; j < 6; j++) {
					int temp = (int)(Math.random()*45 + 1); 
					if(!(ts.add(temp))) {
						j--;
					}else {
						ts.add(temp);
					}
				}	
				System.out.println("로또번호" + i + " : " + ts);
			}
			System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + money % 1000 + "원입니다.");
			lottoPrint();
		}else if(input1 == 2) {
			System.out.println("감사합니다.");
		}else {
			System.out.println("번호를 잘못 입력하셨습니다.");
			lottoPrint();
		}
	}

}
