package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*		
			컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
			
			컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
			사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
			
			입력시간은 5초로 제한하고 카운트 다운을 진행한다.
			5초 안에 입력이 없으면 게임을 진것으로 처리한다.
			
			5초안에 입력이 완료되면 승패를 출력한다.
			
			결과예시)
			==== 결과 ====
			컴퓨터 : 가위
			당  신 : 바위
			결  과 : 당신이 이겼습니다.
		*/
public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		Thread th1 = new Game();
		Thread th2 = new CountDown2();
		
		th1.start();
		th2.start();
	}

}

class Game extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위!바위!보!!!");
		
		int user = 0;
		if(str.equals("가위")) {
			user = 0;
		}else if(str.equals("바위")) {
			user = 1;
		}else if(str.equals("보")) {
			user = 2;
		}else {
			System.out.println("잘못 입력하셨습니다.");
		}
		int computer = (int)(Math.random()*3);
		String result = "";
		
		if(user - computer == -2 || user - computer == 1) {
			result = "당신이 이겼습니다.";
		}else if(user - computer == -1 || user - computer == 2) {
			result = "컴퓨터가 이겼습니다.";
		}else if(user == computer) {
			result = "비겼습니다.";
		}
		
		String[] answer = {"가위", "바위", "보"};
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : " + answer[computer]);
		System.out.println("당  신 : " + str);
		System.out.println("결  과 : " + result);
		T07_ThreadGame.inputCheck = true;
	
	}
}

class CountDown2 extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >=1; i--) {
			
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
		
		System.out.println("5초가 지났습니다. 당신이 졌습니다.");
		
		System.exit(0);
	}
}
