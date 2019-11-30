package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class T11_BaseBallTest {
/*
 * 문제) Set을 이용하여 숫자 야구게임 프로그램을 작성하시오.
 * 		컴퓨터의 숫자는 난수를 이용하여 구한다.
 * 		(스트라이크는 'S', 볼은 'B'로 출력한다.)
 * 
 * 		컴퓨터의 난수가 9 5 7 일때 실행 예시)
 * 		숫자 입력 => 3 5 6
 * 		3 5 6 => 1S 0B
 * 		숫자 입력 => 7 8 9
 * 		7 8 9 => 0S 2B
 * 
 * 		숫자 입력 => 9 5 7
 * 		9 5 7 => 3S 0B
 * 		n번째 만에 맞췄군요.
 */
	public static void main(String[] args) {
		HashSet<Integer> bsnum = new HashSet<>();
		while(bsnum.size() < 3) {
			bsnum.add((int)(Math.random() * 9 + 1));
		}
		ArrayList<Integer> bsnumList = new ArrayList<>(bsnum);
		Collections.shuffle(bsnumList);
		Scanner s = new Scanner(System.in);
		
		System.out.println(bsnumList);
		
		int answer = 0;
		int count = 0;
		while(true) {
			int strike = 0;
			int ball = 0;
			count++;
			System.out.print("중복되지 않는 3자리 숫자를 입력하세요.(1 ~ 9 / 0. 종료)> ");
			answer = Integer.parseInt(s.nextLine());
			if(answer == 0) {System.out.println("종료합니다."); break;}
			int fir = answer / 100;
			int sec = (answer - fir * 100) / 10;
			int thi = answer - (fir * 100 + sec * 10);
			for(int i = 0; i < bsnumList.size(); i++) {
				if(fir == bsnumList.get(i)) {
					if(i == 0) {
						strike++;
					}else {ball++;}
				}
				if(sec == bsnumList.get(i)) {
					if(i == 1) {
						strike++;
					}else {ball++;}
				}
				if(thi == bsnumList.get(i)) {
					if(i == 2) {
						strike++;
					}else {ball++;}
				}
			}
			System.out.println(strike + "S " + ball + "B");
			if(strike == 3) {
				System.out.println(count + "번 만에 맞췄군요.");
				break;
			}
		}
		
		
	}

}
