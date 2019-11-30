package kt.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/*
 로또를 구매하는 프로그램 작성하기
 
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
public class LottoTest {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int menu;
		
		while(true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println(" 1. Lotto 구입");
			System.out.println(" 2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			menu = Integer.parseInt(sc.nextLine());	
		
			int money = 0;
			
			if(menu == 1) {
				System.out.println(" Lotto 구입 시작 ");
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액 입력 : ");
				money = Integer.parseInt(sc.nextLine());
				int charge = money % 1000;
			
					for(int i=0; i < (money / 1000); i++){	
						TreeSet lotto = new TreeSet();
						for(int j =0; j < 6; j++) {
							int lottonumber = (int)(Math.random() * 45 +1);
							lotto.add(lottonumber);
					}
					System.out.print("로또번호 : ");

					System.out.println(lotto);
					
				}
					System.out.println("받은 금액은 : " + money +"원 이고"+"거스름돈은 : " + charge +"입니다.");
		}else {
			System.out.println(" 2. 프로그램 종료");
			System.out.println("감사합니다");
			break;
		}

	
		
	}
	}
}
