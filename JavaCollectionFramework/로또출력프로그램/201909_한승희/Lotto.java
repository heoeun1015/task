package report;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int answerMain=0, money=0;
		
		while(answerMain!=2)
		{
			System.out.print(
					"==========================\r\n" + 
					"	Lotto 프로그램\r\n" + 
					"--------------------------\r\n" + 
					"	1. Lotto 구입\r\n" + 
					"	2. 프로그램 종료\r\n" + 
					"==========================\r\n" + 
					"	메뉴선택 : ");
			answerMain = Integer.parseInt(s.nextLine());
			
			if(answerMain==1)
			{
				System.out.print(
						"	Lotto 구입 시작\r\n" + 
						"	\r\n" + 
						"	(1000원에 로또번호 하나입니다.)\r\n" + 
						"	금액 입력 : ");
				money = Integer.parseInt(s.nextLine());
				
				System.out.println("\n행운의 로또번호는 아래와 같습니다.");
				for(int i=0;i<money/1000;i++)
				{
					Set<Integer> lotto = new HashSet<Integer>();
					while(lotto.size()<6)
					{
						lotto.add((int)(Math.random()*45 + 1));
					}
					List<Integer> lottoList = new ArrayList<Integer>(lotto);
					Collections.shuffle(lottoList);
					System.out.print("로또번호" + (i+1) + " : " + lottoList.toString()+"\n");
					
				}
				System.out.println("\n받은 금액은 " + money + "원이고 거스름돈은 "+(money-money/1000*1000)+"원입니다.\n");
			}
		}
		System.out.println("\n감사합니다.");
		s.close();
	}
}
