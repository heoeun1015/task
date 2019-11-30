package kr.or.ddit.basic;

import java.util.Scanner;
import java.util.TreeSet;

public class Lotto {
	public static void main(String[] args) {
	Lotto l = new Lotto();
		
	l.Lotto1();
		
	}

	public void Lotto1() {
		System.out.println("=========================");
		System.out.println("Lotto 프로그램");
		System.out.println("-------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("=========================");
		System.out.print("메뉴선택 : ");
		Scanner s = new Scanner(System.in);
		
		int menu;
		menu =  Integer.parseInt(s.nextLine());
		switch(menu) {
		
		case 1: //로또 시작 
			Lotto();
			break;
		
		case 2: //프로그램 종료
			System.out.println("프로그램 종료");
		
		
		}
	}
	

	private void Lotto() {

		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.");
		System.out.print("금액 입력 : ");
		
		Scanner s = new Scanner(System.in);
		
		int money;
		money = Integer.parseInt(s.nextLine());
				
		TreeSet<Integer> tr = new TreeSet<>();
		
		
		for(int i = 0; i < 6; i++) {
			int num = (int)(Math.random()* 45 + 1);
			tr.add(num);
		}
		System.out.println("행운의 로또 번호는 아래와 같습니다.");
		System.out.println("로또번호1 : " + tr);
		tr.clear();
		for(int i = 0; i < 6; i++) {
			int num = (int)(Math.random()* 45 + 1);
			tr.add(num);
		} System.out.println("로또번호2 : " + tr);
		
		int change;
		change = money % 1000;
		System.out.println("받은 금액은 " + money + "거스름돈은 " + change + "원 입니다.");
		
		Lotto1();
		
	}
}