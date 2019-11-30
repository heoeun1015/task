package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위바위보는 난수를 이용하여 구하고
	사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받는다.
	
	입력시간은 5초로 제한하고 카운트다운을 진행한다.
	5초안에 입력이 없으면 게임은 진 것으로 처리한다.
	
	5초 안에 입력이 완료되면 승패를 출력한다.
	
	결과예시)
	
	 === 결과 ===
	 컴퓨터 : 가위
	 당  신 : 바위
	 결  과 : 당신이 이겼습니다. 
*/

public class T07_ThreadGame {

	public static boolean check;
	
	public static void main(String[] args) {
		
		Thread th1 = new GameInput();
		Thread th2 = new Count();
		
		th1.start();
		th2.start();
		
	}
	
}

class GameInput extends Thread{

	String[] com = new String[] {"가위", "바위", "보"};
	
	@Override
	public void run() {
		int comResult = (int)(Math.random()*3);
		String comInput = com[comResult];
		int userResult = 0; 
		
		String userInput = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력해주세요");
		T07_ThreadGame.check = true;
		
		switch(userInput) {
			case "가위": userResult = 0;	break;
			case "바위": userResult = 1;	break;
			case "보": userResult = 2;	break;
			default: System.out.println("잘못된 값을 입력하셨습니다.");
		}
		
		switch(userResult - comResult) {
			case 0: result(userInput, comInput, "비겼습니다.");			  		break;
			case 1: case -2: result(userInput, comInput, "당신이 이겼습니다.");	break;
			case 2: case -1: result(userInput, comInput, "컴퓨터가 이겼습니다.");	break;
		}
	}
	
	static void result(String userInput, String comInput, String str) {
		System.out.println();
		System.out.println("   === 결과 ===");
		System.out.println("컴퓨터 : " + comInput);
		System.out.println("당  신 : " + userInput);
		System.out.println("결  과 : " + str);
	}
	
}

class Count extends Thread{
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {
			
			if(T07_ThreadGame.check) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("5초가 지났습니다. 컴퓨터 승.");
	}
}









