package kr.or.ddit.basic;

import java.awt.Dialog;

import javax.swing.JOptionPane;

/*	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	 	
	 	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
	 	사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
	 	
	 	입력시간은 5초로 제한하고 카운트다운을 진행한다.
	 	5초 안에 입력이 없으면 게임을 진 것으로 처리한다.
	 	
	 	5초 안에 입력이 완료되면 승패를 출력한다.
	 	
		결과예시) 	───── 결과 ─────
			 	컴퓨터: 가위
			 	당  신: 바위
			 	결  과: 당신이 이겼습니다.	 */


public class T07_ThreadGame {
	
	public static boolean strCheck = false;
	public static int user = 0;
	
	public static void main(String[] args) {
		
		System.out.println("▷ 가위바위보 게임을 시작합니다.");
		System.out.println("───────────────────────────────────────");
		
		Thread th1 = new User();
		Thread th2 = new GameCount();
		
		int random = (int)(Math.random() * 3) + 1;
		
		th1.start();
		th2.start();
		
			try {
				th1.join();
				th2.join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		
		new Computer().game();
			
			
	}
	
}


class User extends Thread{

	@Override
	public void run() {
		
		String input = "";
		
		input = JOptionPane.showInputDialog("가위 바위 보 중 하나를 입력하세요.");
		
		switch(input) {
		case "가위":
			T07_ThreadGame.user = 0;
			break;
		case "바위":
			T07_ThreadGame.user = 1;
			break;
		case "보":
			T07_ThreadGame.user = 2;
			break;
		}
		
		T07_ThreadGame.strCheck = true;
		
	}
	
}

class Computer{
	
	static void game() {
		
		int random = (int)(Math.random() * 3) + 1;
		
		switch(random - T07_ThreadGame.user) {
		case -1: case 2:
			System.out.println("───────────────────────────────────────");
			System.out.println("▷ 당신이 이겼습니다.");
			return;
		case 0:
			System.out.println("───────────────────────────────────────");
			System.out.println("▷ 비겼습니다.");
			return;
		case -2: case 1:
			System.out.println("───────────────────────────────────────");
			System.out.println("▷ 당신이 졌습니다.");
			return;
		}
		
	}
		
	
}




class GameCount extends Thread{

	@Override
	public void run() {
		
		for(int i = 10; i >= 1; i--) {
			if(T07_ThreadGame.strCheck) {
				return;
			} 
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		System.out.println("▷ 10초가 지났습니다. 프로그램을 종료합니다.");
		
		System.exit(0);
	}
	
	
}







