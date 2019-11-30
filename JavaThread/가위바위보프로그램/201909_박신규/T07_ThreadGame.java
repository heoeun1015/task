package kr.or.ddit.basic;
/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오

	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
	사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받는다.

	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초안에 입력이 없으면 게임을 진것으로 처리한다.

	5초안에 입력이 완려되면 승패를 출력한다.

	결과예시 )
	 === 결과 ===
	 컴퓨터 : 가위
	 당   신 : 바위
	 결   과 : 당신이 이겼습니다.
 */

import javax.swing.JOptionPane;

public class T07_ThreadGame {
	public static boolean inputCheck = false;

	public static void main(String[] args) {

		Thread in = new DataInput1();
		Thread out = new CountDown1();

		in.start();
		out.start();
	}
}

class DataInput1 extends Thread {
	String mychoice;
	@Override
	public void run() {
		mychoice = JOptionPane.showInputDialog("가위, 바위, 보로 입력하세요");
		// 입력이 완료되면 inputCheck변수를 true로 변경한다.
		T07_ThreadGame.inputCheck = true;

		//System.out.println("입력한 값은 " + str + "입니다.");

		System.out.println("=== 결과 ===");
		int cr =  (int)  (Math.random() * 3)+ 1;  // 각각 1, 2, 3을 1/3 확률로 출력
		String comchoice = null;
		if (cr == 1) {
			comchoice = "가위";
		} else if (cr==2) {
			comchoice = "바위";
		} else if (cr==3) {
			comchoice = "보";
		}

		String result = null;

		System.out.println("컴퓨터 : " + comchoice);
		System.out.println("당   신 : " + mychoice);
		if (comchoice.equals(mychoice)) {
			result = "비겼습니다.";
		} else if( (comchoice.contains("가위") && mychoice.contains("바위")) || 
				   (comchoice.contains("보") && mychoice.contains("가위"))  || 
				   (comchoice.contains("바위") && mychoice.contains("보")) ) {
			result = "당신이 승리하였습니다.";
		} else if( (comchoice.contains("가위") && mychoice.contains("보")) || 
				   (comchoice.contains("보") && mychoice.contains("바위")) || 
				   (comchoice.contains("바위") && mychoice.contains("가위")) ) {
			result = "컴퓨터가 승리하였습니다.";
		} else {
			result = "올바르지 않은 입력값입니다.";
		}
		System.out.println("결   과 : " + result);
	}
}

class CountDown1 extends Thread{
	@Override
	public void run() {
		for (int i = 5; i>=1; i--) {
			if(T07_ThreadGame.inputCheck) {
				return; // run() 메서드가 종료되면 쓰레드도 끝난다.
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 5초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("시간초과!! 컴퓨터가 승리했습니다.");
		System.exit(0); //프로그램 종료시키는 명령
	}
}