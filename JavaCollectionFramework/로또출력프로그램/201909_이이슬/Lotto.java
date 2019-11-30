package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;

public class T11_Lotto {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.println("==========================");
			System.out.println("	Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("	1. Lotto 구입");
			System.out.println("	2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			int num = Integer.parseInt(s.nextLine());

			if(num==1) {
				System.out.println(" Lotto 구입 시작");
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				int price = Integer.parseInt(s.nextLine());
				System.out.println("받은 금액은 "+ price +"원이고 거스름돈은 "+ price%1000 +"원입니다.");
				
				
				System.out.println("행운의 로또번호는 아래와 같습니다.");
				for(int i=1; i<=price/1000; i++) {
					
					HashSet<Integer> random = new HashSet<>();
					
					while(random.size() < 6) {	// Set의 데이터가 6개 될 때까지 반복
						int ran = (int)(Math.random()*45+1);	// 1부터 45사이 난수
						random.add(ran);
					}
					System.out.println("로또번호" +i+" : " + random);
				}
			}else if(num==2) {
				System.out.println("감사합니다.");
				System.exit(0);
			}
		}
	}

}
