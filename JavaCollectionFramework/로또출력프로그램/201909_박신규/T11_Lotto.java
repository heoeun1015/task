package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;

public class T11_Lotto {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			 
			int choice = Integer.parseInt(sc.nextLine());
			if( choice == 1 ) {
				int c;
				int n;
				System.out.println("Lotto 구입 시작");
				System.out.println("1000원에 로또번호 하나입니다.");
				System.out.print("금액 입력 : ");
				int money = Integer.parseInt(sc.nextLine());
				System.out.println("행운의 로또번호는 아래와 같습니다.");
				
				c = money / 1000;  // 횟수 출력
				n = money % 1000;  // 거스름돈 
				
				for (int i =0; i<c; i++) {
					HashSet<Integer> intRnd = new HashSet<>();
					while(intRnd.size() < 6) {  //Set의 데이터 5개가 될때까지 반복
						int num = (int)(Math.random() * 45 + 1);//1~45사이 난수
						intRnd.add(num);
					}
					System.out.println("로또번호 "+(i+1)+ " : " + intRnd);
				}
				System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + n +"원 입니다." );
			} else if ( choice == 2 ) {
				System.out.println("시스템을 종료합니다.");
				System.exit(0);
			}
		}
	}
}