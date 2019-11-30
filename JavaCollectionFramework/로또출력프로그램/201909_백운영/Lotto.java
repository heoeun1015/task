package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;

public class Lotto {

	public static void main(String[] args) {
		start();
	}
	private static void start() {
		Scanner sc = new Scanner(System.in);
		String menu;
		
		System.out.println("=====================LOTTO=====================");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("-----------------------------------------------");
		
		menu = sc.nextLine();
		
		switch (menu) {
		case "1":
			Lotto();
			break;

		case "2":
			System.out.println("감사합니다.");
			break;
		default :
			System.out.println("다시 입력해주세요.");
			start();
		}
	}
	private static void Lotto() {
		
		Scanner sc = new Scanner(System.in);
		int input = 0;
		
		System.out.println("로또 구입을 시작합니다.");		
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println("---------  로또 한장에 1000원 입니다  ---------");
		System.out.println("-----------------------------------------------");
		System.out.println("금액을 입력하세요 > ");
		input = Integer.parseInt(sc.nextLine());
		
		HashSet lotto = new HashSet();
		
		if(input < 1000) {
			System.out.println("금액이 모자랍니다. 다시 입력해주세요.");
			Lotto();
		}
		
		System.out.println("행운의 로또번호는 다음과 같습니다.");
		for(int i = 1; i <= input/1000; i++) {
			while(lotto.size() < 6) {
				int number = (int)(Math.random() * 45 + 1);
				lotto.add(number);
			}
			System.out.println("로또 번호"+ i +":"+ lotto);
			lotto.clear();
		}
		
			System.out.println("거스름돈은 : " + input%1000 + " 입니다.");
			start();
			}
		}