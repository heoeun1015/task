package ko.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 전행하는 프로그램을 작성하시오
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다
 *  5초안에 입력이 없으면 게임을 진 것으로 처리한다
 * 
 *  5초안에 입력이 완료되면 승패를 출력한다
 *  
 *  결과예시)
 *   === 결과 ===
 *   컴퓨터 : 가위
 *   당  신 : 바위
 *   결  과 : 당신이 이겼습니다.
*/

//클래스 : 카운트다운, 사용자,컴퓨터,결과출력

public class T07_ThreadGame {

	public static boolean inputCheck = false;
	public static String[] arr = {"가위","바위","보"};
	
	public static void main(String[] args) {
		
		int randomNum = (int)(Math.random()*3);
		String computer = arr[randomNum];
		
		Thread th1 = new UserInput();
		try {
			th1.start();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : " + computer);
		System.out.println("당  신 : " + );
		System.out.println("결  과 : " + "당신이 " + );
	}
	
}

class UserInput extends Thread{
	
	@Override
	public void run() {
		Thread th2 = new CountDown$();
		
		String str = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력하세요");
		T07_ThreadGame.inputCheck = true;
		
		th2.start();
		
		try {
			th2.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}

class CountDown$ extends Thread{
	@Override
	public void run() {
		for(int i=5; i>=1; i--) {
			if(T07_ThreadGame.inputCheck) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("5초가 지났습니다.");
		System.out.println("=== 결과 ===");
		System.out.println("결  과 : 당신이 졌습니다.");
		
		System.exit(0);
	}
}


