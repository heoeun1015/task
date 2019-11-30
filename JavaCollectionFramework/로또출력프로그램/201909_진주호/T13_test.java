package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Scanner;

public class T13_test {
	public static void main(String[] args) {

		T13_test toto = new T13_test();
		Scanner s = new Scanner(System.in);
		System.out.println("금액을 입력해주세요.");
		int gamecoin = Integer.parseInt(s.nextLine());
		
		int a = gamecoin / 1000;
		int c = gamecoin -(a*1000);
		System.out.println("출력할 로또 수 : "+a);
		
		//System.out.println("출력할 로또 개수 :");
		int gamecnt = a;

		for(int i=0; i< gamecnt; i++) {
			System.out.println("출력한 로또 번호 : " + T13_test.lottonum());
		}
		System.out.println("잔돈은" + c + "입니다");

	}
	public static HashSet<Integer> lottonum() {
		HashSet<Integer> lotto2 = new HashSet<Integer>();
		while(lotto2.size() < 6) {
			int num = (int)(Math.random()*44+1);
			lotto2.add(num);
		}
		return lotto2;
	}
}



