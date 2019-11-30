package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class T13_Lotto {

	public static void main(String[] args) {
	
		 new T13_Lotto().lottoStart();
	}
	
	
	
	Scanner scan = new Scanner(System.in);
	public void lottoStart() {
		int menu = 99;
		
		do {
			try {
			System.out.println("========================");
			System.out.println("Lotto 프로그램");
			System.out.println("------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.print("메뉴선택: ");
			menu = Integer.parseInt(scan.nextLine());
			
				switch(menu) {
				case 1: buyLotto();	 						break;
				case 2: System.out.println("\n감사합니다.");	break;
				}
			
			}catch(Exception e) {
				System.out.println("숫자를 다시 입력해주세요.");
			}
		}while(menu != 2);
		
	}
	
	
	public void buyLotto() {
		HashSet<Integer> lottoNum = new HashSet<>();
		
		//로또 수랑 거스름돈 계산
		System.out.println("로또 구입 시작");
		System.out.println("\n(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력: ");
		int cost = Integer.parseInt(scan.nextLine());
		int lotto = cost / 1000;
		int change = cost % 1000;
		
		
		System.out.println("\n행운의 로또번호는 아래와 같습니다.");
	
		for(int i = 0; i < lotto; i++){
			lottoNum.clear();
			//랜덤 번호 6개 넣기==================================
			do {
				int num = (int)(Math.random()*45+1);
				lottoNum.add(num);
			}while(lottoNum.size() < 6);
			
			//출력 편하게 하려고 ArrayList에 넣기
			ArrayList<Integer> lottoNumList = new ArrayList<>(lottoNum);
			//오름차순으로 예쁘게 정렬
			Collections.sort(lottoNumList);
			
			//출력
			int count = 1;
			System.out.print("로또번호"+(i+1)+": ");
			for(int temp : lottoNumList) {
				if(count == lottoNumList.size()) {
					System.out.print(temp);
				}else {
					System.out.print(temp+",");
				}
				count++;
			}
				System.out.println();
		}
		
		System.out.println("\n받은 금액은 "+cost+"원이고 거스름돈은 "+change+"원입니다.");
		System.out.println();
		
	}
	
	
}
