package homework;

import java.util.Scanner;
import java.util.TreeSet;

public class LottoTest {
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		
		while(true) {
			
			System.out.println("==========================");
			System.out.println(" Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.println("메뉴 선택 : ");
			
			int inputNum = Integer.parseInt(s.nextLine());
			
			if(inputNum == 1) {
				buyLotto();
			} else if (inputNum == 2) {
				break;
			} else {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	private static void buyLotto() {
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.println("금액 입력 : ");
		
		int inputMoney = Integer.parseInt(s.nextLine());
		while(true) {
			if(inputMoney < 0) {
				System.out.println("잘못 입력하셨습니다.");
				inputMoney = Integer.parseInt(s.nextLine());
			} else {
				break;
			}
		}
		System.out.println("행운의 로또 번호는 아래와 같습니다.\r");
		for(int i = 0; i < inputMoney / 1000; i++) {
			System.out.print("로또 번호" + (i + 1) + " : ");
			System.out.println(getLotto());
		}
		System.out.println("\r받은 금액은 " + inputMoney + "원이고 거스름돈은 " + inputMoney % 1000 + "원 입니다.");
		
	}
	

	private static TreeSet<Integer> getLotto() {
		TreeSet<Integer> ranNumSet = new TreeSet<>();
		
		while(ranNumSet.size() < 6) {
			ranNumSet.add((int)(Math.random() * 45 + 1));
		}
		
		return ranNumSet;
	}

}
