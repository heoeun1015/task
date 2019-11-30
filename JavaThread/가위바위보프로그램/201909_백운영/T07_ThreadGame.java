package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초안에 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시)
 * 	=== 결과 ===
 * 컴퓨터 : 가위
 * 당  신 : 바위
 * 결  과 : 당신이 이겼습니다.
 */

public class T07_ThreadGame {
	public static boolean inputCheck = false;

	public static void main(String[] args) {
		Thread th1 = new RSPGame();
		Thread th2 = new Count();

		th1.start();
		th2.start();
	}
}

class RSPGame extends Thread {

	String[] rsp = { "가위", "바위", "보" };

	String com = rsp[(int) (Math.random() * 3)];

	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위 바위 보 중 하나를 입력하세요.");
		String result = "";

		T07_ThreadGame.inputCheck = true;

		if (str.endsWith("가위") && com.equals("보") || str.endsWith("바위") && com.equals("가위")
				|| str.endsWith("보") && com.equals("바위")) {
			result = "당신이 이겼습니다.";
		} else if (str.endsWith("가위") && com.equals("바위") || str.endsWith("바위") && com.equals("보")
				|| str.endsWith("보") && com.equals("가위")) {
			result = "컴퓨터가 이겼습니다.";
		} else {
			result = "비겼습니다.";
		}

		System.out.println("============ 결과 ============");
		System.out.println("컴퓨터 : " + com);
		System.out.println("당  신 : " + str);
		System.out.println("결  과 : " + result);

	}

}

class Count extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면 run()메서드를 종료시킨다.
			if (T07_ThreadGame.inputCheck) {
				return; // run()메서드가 종료되면 쓰레드도 끝난다.
			}

			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// 10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("5초가 지났습니다. 패배하였습니다.");

		System.exit(0); // 프로그램을 종료시키는 명령
	}
}