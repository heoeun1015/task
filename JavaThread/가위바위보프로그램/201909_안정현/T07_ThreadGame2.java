package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 
 *  입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 *  5초안에 입력이 없으면 게임을 진것으로 처리한다.
 *  
 *  5초안에 입력이 완료되면 승패를 출력한다.
 *  
 *  결과예시
 *  === 결과 ===
 *  컴퓨터 : 가위
 *  당  신 : 바위
 *  결  과 : 당신이 이겼습니다.
 */

public class T07_ThreadGame2 {
	public static boolean inputCheck = false;

	public static void main(String[] args) {

		User th1 = new User();
		Computer th2 = new Computer();
		th1.start();
		th2.start();
		
		String str = JOptionPane.showInputDialog("가위, 바위, 보를 입력해주세요");
		T07_ThreadGame2.inputCheck = true;
		
		try {
			th1.join();
			th2.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.println("===결과===");
		System.out.println("사용자 : " + th1.str);
		System.out.println("컴퓨터 : " + th2.str);
		System.out.println("결과 : " + th2.result);
	}
}

class User extends Thread {
	public static String str;

	@Override
	public void run() {
		
	}

}

class Computer extends Thread {
	public String str;
	public String result;
	@Override
	public void run() {
		for(int i = 5; i >=1; i--) {
			if(T07_ThreadGame2.inputCheck) {
				break;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		if(!T07_ThreadGame2.inputCheck) {
			System.err.println("시간초과로 당신이 졌습니다.");
			System.exit(0);
		}
		
//		1"가위" 2"바위" 3"보"
		int comnum = (int)(Math.random() * 3 + 1);
		if(comnum == 1) {
			str = "가위";
		}else if(comnum == 2) {
			str = "바위";
		}else {
			str = "보";
		}
		
		if(comnum == 1 && User.str.equals("보") || comnum == 2 && User.str.equals("가위") || comnum == 3 && User.str.equals("바위")){
			result = "컴퓨터가 이겼습니다.";
		}else if(User.str.equals(this.str)) {
			result = "비겼습니다.";
		}else {
			result = "당신이이겼습니다.";
		}
		
	}
}


