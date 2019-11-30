package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
 * 컴퓨터의 가위바위보는 난수를 이용하여 구하고 사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초안에 입력이 없으면 게임을 진것으로 처리한다.
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 결과예시)
 * === 결과 ===
 * 컴퓨터 : 가위
 * 당신 : 바위
 * 결과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	public static boolean inputCheck = false;

	public static void main(String[] args) {
		Thread th2 = new Count_Down();
		th2.start();

		String[] game = new String[] { "가위", "바위", "보" };
		int num = (int) (Math.random() * 3);
		String com = game[num];

		String str = JOptionPane.showInputDialog("가위바위보 게임~! 입력하세요!");
		T07_ThreadGame.inputCheck = true;
		System.out.println("당신 : " + str);
		System.out.println("컴퓨터 : " + com);
		
		if (com.equals(str)) {
			System.out.println("비겼습니다.");
		} else if (com.equals("가위") && str.equals("바위") || com.equals("바위") && str.equals("가위") || com.equals("보") && str.equals("바위")) {
			System.out.println("컴퓨터가 이겼습니다.");
		} else
			System.out.println("당신이 이겼습니다.");
	}

}

/*
 * 카운트다운을 처리하는 쓰레드 클래스
 */
class Count_Down extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (T07_ThreadGame.inputCheck) {
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