package HomeWork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Lotto {

	/*
	* 
	*  로또를 구매하는 프로그램 작성하기
	* 
	* 사용자는 로또를 구매할 때 구매할 금액을 입력하고
	* 입력한 금액에 맞게 로또번호를 출력한다.
	* (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
	*      출력한다.)
	*
	*	==========================
	*         Lotto 프로그램
	*	--------------------------
	*	 1. Lotto 구입
	*	  2. 프로그램 종료
	*	==========================		 
	*	메뉴선택 : 1  <-- 입력
	*			
	*	 Lotto 구입 시작
	*		 
	*	(1000원에 로또번호 하나입니다.)
	*	금액 입력 : 2500  <-- 입력
	*			
	*	행운의 로또번호는 아래와 같습니다.
	*	로또번호1 : 2,3,4,5,6,7
	*	로또번호2 : 20,21,22,23,24,25
	*			
	*	받은 금액은 2500원이고 거스름돈은 500원입니다.
	*			
	*  	 ==========================
	*         Lotto 프로그램
	*	--------------------------
	*	  1. Lotto 구입
	*	  2. 프로그램 종료
	*	==========================		 
	*	메뉴선택 : 2  <-- 입력
	*		
	*	감사합니다
	*/
	static Scanner s = new Scanner(System.in);
	static HashSet<Integer> intRnd = new HashSet<>();
	static List<Integer> intRndList;
	
	public static void main(String[] args) {
		LottoPrgramStart();

	}

	private static void LottoPrgramStart() {
		while(true){
			System.out.println("==========================");
			System.out.println("	Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("	1.Lotto 구입");
			System.out.println("	2.프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			int menuNum = Integer.parseInt(s.nextLine());
			switch(menuNum) {
			case 1 :
				purchase();
				break;
			case 2 : 
				System.out.println("감사합니다");
				return;
			default :
				System.out.println("잘못 입력하였습니다. 다시 입력하세요.");
				
			}
		}
	}

	private static void purchase() {
		System.out.println();
		System.out.println("	Lotto 구입 시작");
		System.out.println();
		System.out.println("1000원에 로또번호 하나입니다. 구매량과 내실 돈을 입력하세요");
		int giveMoney;
		int rest;
		int amount;
		int noTaken = 0;
		
		while(true) {
			System.out.print("구매량 >> ");
			amount = Integer.parseInt(s.nextLine());
			System.out.print("내실 돈 >>");
			giveMoney = Integer.parseInt(s.nextLine());
			System.out.println();
			int check = giveMoney - amount*1000;
			noTaken = (int)((check/1000d - check/1000)*1000);
			if(check/1000d != check/1000 && noTaken>0) {
				System.out.println("잔돈은 필요없습니다.왜 주신거죠?" + noTaken + "은 가져가세요");
			}
			
			if(check >=0) {
				rest = check - noTaken;
				break;
			}else {
				System.out.println("돈이 부족합니다. 다시 입력해주세요.");
			}
		}
		System.out.println("행운의 로또번호는 아래와 같습니다");
		for(int i=1; i<=amount; i++) {
			System.out.print("로또번호" + i + " : ");
			lottoNum();
		}
		System.out.println();
		System.out.println("받은 금액은 " + (giveMoney - noTaken) + "원입니다.");
		
		
	}


	private static void lottoNum() {
		intRnd.clear();
		while(intRnd.size()<6) {
			int num = (int)(Math.random()*44 + 1);
			intRnd.add(num);
		}
		intRndList = new ArrayList<>(intRnd);	
		Collections.sort(intRndList);
		for(int j=0; j<intRndList.size()-1; j++) {
			System.out.print(intRndList.get(j) + ", ");
		}
		System.out.println(intRndList.get(intRnd.size()-1));
	}

	
}
