package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lotto {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.println("☆★☆★☆★☆ 일확천금의 꿈 ☆★☆★☆★☆");
	
		int menu;
		
		do {
			System.out.println("=============================");
			System.out.println("======== 1. 구매하기 ==========");
			System.out.println("======== 2. 프로그램 종료 ======");
			System.out.println("=============================");
			System.out.print("원하는 메뉴를 선택 해 주세요! >>>>>");
			menu = Integer.parseInt(s.nextLine());
			
			switch(menu) {
			case 1:
				LottoLot();
				break;
			case 2:
				System.out.println("$$구매 감사합니다. 당첨 기원!$$");
				break;
				default:
					System.out.println(" 잘못누르셨습니다. ");
				break;
			}
		}while(menu !=2);
		
		
	}

	
	
	
	
	
	
	
	
	
	private static void LottoLot() {
		
		Scanner s = new Scanner(System.in);
		
		Set<Integer> lottoNum = new HashSet<Integer>();
		
		System.out.println("===================================");
		System.out.println("$$ 로또금액 : 1,000 원!");
		int money = 0;
		do {
			System.out.println("구매하실 로또 금액을 입력 해 주세요! >>>");
			money = Integer.parseInt(s.nextLine());
			if(money < 1000) {
				System.out.println("______________________________");
				System.out.println("금액이 부족합니다!");
				System.out.println("______________________________");
			}
		}while(money < 1000);
		int money1 = money;
		int count = 0;
		System.out.println();
		
		while(money1 >= 1000) {
			
			count++;
			money1 -= 1000;
			
			lottoNum.clear();
			
			while(lottoNum.size() < 6) {
				int num = (int)(Math.random() *45 + 1);
				lottoNum.add(num);
			}
		ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoNum);
		
		Collections.shuffle(lottoList);
		
		String str = "";
		
		for(int i = 0; i < lottoNum.size(); i++) {
			str += lottoList.get(i) + ", ";
			
		}System.out.println(" 로또번호" + count + ":" + str.substring(0,str.lastIndexOf(", ")));
		
		}
		System.out.println();
		System.out.println("====================================");
		System.out.println("받은 금액은" + money + " 이고, 거스름돈은 " + money1 + "입니다.");
	
	}

}





































