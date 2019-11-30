package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오
 * 
 * 컴퓨터의 가위바위보는 난수를 이용하여 구하고
 * 사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초안에 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시)
 * 	=== 결과 ===
 * 컴퓨터 : 가위
 * 당   신 : 바위
 * 결   과 : 당신이 이겼습니다.
 * 
*/
public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Thread t1 = new User();
		Thread t2 = new Count();
		
		t1.start();
		t2.start();
		
	}
}


class User extends Thread {
	
	@Override
	public void run() {

		String user = JOptionPane.showInputDialog("가위바위보를 입력하세요.");
		
		T07_ThreadGame.inputCheck = true;
		
		String[] random = {"가위", "바위", "보"};
		int i = (int)(Math.random() * 3);
		
		String str = "";
		if((random[i].equals("가위")) && user.equals("가위")||(random[i].equals("바위")) && user.equals("바위")||(random[i].equals("보")) && user.equals("보")) {
			str = "비겼습니다";
		}else if((random[i].equals("가위")) && user.equals("보")||(random[i].equals("바위")) && user.equals("가위")||(random[i].equals("보")) && user.equals("바위")) {
			str = "컴퓨터가 이겼습니다.";
		}else if((random[i].equals("바위")) && user.equals("보")||(random[i].equals("보")) && user.equals("가위")||(random[i].equals("가위")) && user.equals("바위")) {
			str = "당신이 이겼습니다.";
		}
		
		System.out.println("컴퓨터 : " + random[i]);
		System.out.println("당   신 : " + user);
		System.out.println("결   과 : " + str);
	}
	
}


class Count extends Thread {
	
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {
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
		System.out.println("시간 초과입니다.");
		
		
		System.exit(0);	
	
	}
}















