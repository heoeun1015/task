package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
	사용자의 가위 바위 보는 showInputDialog() 메서드를 이용하여 입력받는다.
	
	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초 안에 입력이 없으면 게임을 진 것으로 처리한다.
	
	5초 안에 입력이 완료되면 승패를 출력한다.
	
	결과예시)
	 === 결과 ===
	 컴퓨터 : 가위
	 당  신 : 바위
	 결  과 : 당신이 이겼습니다.
	
 */
public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Thread th1 = new DataInput1();
		Thread th2 = new CountDown1();
		
		th1.start();
		th2.start();
	}
}

class DataInput1 extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위 바위 보 중 입력하세요");
		T07_ThreadGame.inputCheck = true;
		
		String random[] = new String[] {"가위","바위","보"};
		int j = (int)(Math.random() * 3);
		
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : " + random[j]); 
		System.out.println("당  신 : " + str);
		
		if(random[j].equals("가위") && str.equals("가위") || random[j].equals("바위") && str.equals("바위") || random[j].equals("보") && str.equals("보")) {
			System.out.println("결  과 : 비겼습니다.");
		} else if(random[j].equals("가위") && str.equals("바위") || random[j].equals("바위") && str.equals("보") || random[j].equals("보") && str.equals("가위")) {
			System.out.println("결  과 : 당신이 이겼습니다.");
		} else if(random[j].equals("가위") && str.equals("보") || random[j].equals("바위") && str.equals("가위") || random[j].equals("보") && str.equals("바위")){
			System.out.println("결  과 : 컴퓨터가 이겼습니다.");
		} else {
			System.out.println("잘못 입력하셨습니다.");
		}
		
		
	}
}

class CountDown1 extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {
			if(T07_ThreadGame.inputCheck) {
				return;
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("5초가 지났습니다. 당신이 졌습니다.");
		
		System.exit(0);
	}
}