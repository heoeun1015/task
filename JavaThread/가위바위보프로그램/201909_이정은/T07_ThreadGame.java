package kr.or.ddit.basic;

import java.util.Scanner;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
	사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
	
	입력시간은 5초로 제한하고 카운트 다운을 진행하다.
	5초안에 입력이 없으면 게임을 진것으로 처리한다.
	
	5초안에 입력이 완료되면 승패를 출력한다.
	
	결과 예시 )
		=== 결과 ===
		컴퓨터 : 가위
		당  신 : 바위
		결  과 : 당신이 이겼습니다.
	
*/
public class T07_ThreadGame {
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in);		
		
	Thread th1 = new DataInputt();
	Thread th2 = new CountDownn();
	
	th1.start();
	th2.start();	
	
	}
}

class DataInputt extends Thread{
	
	@Override
	public void run() {
		String[] rsp = {"가위","바위","보"};
		
		String str = JOptionPane.showInputDialog("가위바위보 중 하나를 선택하세요");	
		String com = rsp[(int)(Math.random()*3)];
		
		T07_ThreadGame.inputCheck = true;
		
		System.out.println("=== 결과 ===");
		
		if(str.equals("가위") && com.equals("바위") || str.equals("바위") && com.equals("보") || str.equals("보") && com.equals("가위")){
			System.out.println("당신이 졌습니다.");
		}else if(str.equals("바위") && com.equals("가위") || str.equals("보") && str.equals("바위") || str.equals("가위") && com.equals("보")){
			System.out.println("당신이 이겼습니다.");
		}else{
				System.out.println("비겼습니다.");
		}

		
		
		
		
	}
}


class CountDownn extends Thread{
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {

			if(T07_ThreadGame.inputCheck) {
				return;		// run() 메서드가 종료되면 쓰레드도 끝난다.
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("5초가 지났습니다. 프로그램을 종료합니다.");
		
		System.exit(0); 	
	}
}