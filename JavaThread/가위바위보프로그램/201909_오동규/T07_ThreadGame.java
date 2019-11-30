package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위바위보는 난수를 이용하여 구하고
	사용자의 가위바위보는 showInputDialog() 메서드를 이용하여 입력받는다.
	
	입력시간은 5초로 제한하고 카운트다운을 진행한다.
	5초안에 입력이 없으면 게임을 진것으로 처리한다.
	
	5초안에 입력이 완료되면 승패를 출력한다.
	
	결과예시)
	 === 결과 ===
	 컴퓨터 : 가위
	 당  신 : 바위
	 결  과 : 당신이 이겼습니다.
	 
	 
 */
public class T07_ThreadGame {
	public static boolean inputCheck = false;
	public static String str = "";
	
	public static void main(String[] args) {
		Thread th1 = new DataInput2();
		Thread th2 = new CountDown2();
		
		th1.start();
		th2.start();
		
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doRSP();
	}
	
	public static void doRSP(){
		String rspStr = "가위바위보";
		String rspStrArr[] = {"가위", "바위", "보"};
		int com = (int)(Math.random() * 3);
		int user = rspStr.indexOf(T07_ThreadGame.str) / 2;
		
		if(com == user) {
			printRSP(rspStrArr[com],rspStrArr[user],"비겼습니다.");
		} else if((com + 1) % 3 == user) {
			printRSP(rspStrArr[com],rspStrArr[user],"당신이 이겼습니다.");
		} else {
			printRSP(rspStrArr[com],rspStrArr[user],"당신이 졌습니다.");
		}
	}

	private static void printRSP(String string, String string2, String string3) {
		System.out.println("=== 결과 ===\r\n" + 
				"컴퓨터 : "+ string + "\r\n" + 
				"당  신 : "+ string2 + "\r\n" + 
				"결  과 : " + string3);
	}
}

class DataInput2 extends Thread {

	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		while(!(str.equals("가위") || str.equals("바위") || str.equals("보"))) {
			System.out.println("잘못 입력하셨습니다.");
			str = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		}
		
		// 입력이 완료되면 inputCheck 변수를 true로 변경한다.
		T07_ThreadGame.inputCheck = true;
		T07_ThreadGame.str = str;
	}
}
/**
 * 카운트다운을 처리하는 쓰레드 클래스
 */
class CountDown2 extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면
			// run() 메서드를 종료시킨다. 즉, 현재 쓰레드를 종료시킨다.
			if(T07_ThreadGame.inputCheck) {
				return; // run() 메서드가 종료되면 쓰레드도 끝난다.
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("5초가 지났습니다. 당신이 졌습니다.");
		
		System.exit(0); // 프로그램을 종료시키는 명령
	}
}