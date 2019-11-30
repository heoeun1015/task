import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;



public class Lotto {
	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		
		
		
		int money = 0;
		Set set = new HashSet();			
		while(true) {
			System.out.println("메뉴 선택");
			
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			int input = a.nextInt();
			switch(input) {
			
				case 1 : System.out.println("금액을 입력하세요"); 
				money = a.nextInt();								
				while(money >= 1000) {
						for(int i=0; i < 6; i++) {							
							int num = (int) (Math.random() *45) +1;
							set.add(num);
						}
						money = money - 1000;						
						System.out.println("행운의 로또번호" +set);
						set.clear();
				} 
				System.out.println("거스름돈은 " + money +"입니다.");			
				case 2 : break;
			}
			
			if(input == 2 ) {
				break;
			}
		}
		
	}
}
