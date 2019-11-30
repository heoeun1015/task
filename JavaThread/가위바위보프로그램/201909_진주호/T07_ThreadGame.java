package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고 사용자의 가위바위보는 ShowInputDialog()메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 
 * 5초안에 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시) 
 * ----결과------
 * 컴퓨터 : 가위
 * 당 신 : 바위
 * 결과 : 당신이 이겼습니다.
 * 
 * @author pc09
 *
 */
public class T07_ThreadGame {
	public static boolean inputcheck = false;

	public static void main(String[] args) {
		Thread th11 = new DataInput2();
		Thread th22 = new CountDown2();
		
		th11.start();
		th22.start();
		
	}
}

class DataInput2 extends Thread{
	@Override
	public void run() {
		// 컴퓨터
		String com[] = {"가위", "바위", "보"};
		int rd = (int)(Math.random()*3);
		String computer = com[rd]; // 컴퓨터 랜덤값 받음
		String msg ="";
		
		String str = JOptionPane.showInputDialog("가위,바위,보를 입력하세요.");
		
		T07_ThreadGame.inputcheck = true; // 입력이 완료되면 inputchek가 true로 바뀜
		
		if(computer.equals(str)) {
			msg ="비김";
		}else if(computer.equals("가위")&&str.equals("바위") 
				|| computer.equals("보")&&str.equals("가위") 
				|| computer.equals("바위")&&str.equals("보")) {
			msg ="당신이 이겼습니다.";
		}else {
			msg ="컴퓨터가 이겼습니다.";
		}
		
		System.out.println("----- 결과 -----");
		System.out.println("컴퓨터 : " + computer);
		System.out.println("사용자 : " + str);
		System.out.println("=======================");
		System.out.println("결과 : " + msg);
		System.out.println("=======================");
	}
}

class CountDown2 extends Thread{
	@Override
	public void run() {
		for(int i =5; i>=1; i--) {
			if(T07_ThreadGame.inputcheck) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("5초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); // 프로그램을 종료시키는 명령
	}
}

