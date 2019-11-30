package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위바위보는 난수를 이용하여 구하고
 * 사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초안에 입력이 없으면 게임을 진 것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과 예시)
 * === 결과 ===
 * 컴퓨터 : 가위
 * 당   신 : 바위
 * 결   과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	public static boolean inputCheck;
	public static int rsp_num = (int)(Math.random() * 3);//0 = 가위, 1 = 바위, 2 = 보
	public static String[] com_rsp = {"가위", "바위", "보"};
	
	public static void main(String[] args) {
		CntDown cnt = new CntDown();
		Rsp rsp = new Rsp();
//		System.out.println(T07_ThreadGame.rsp_num);
		rsp.start();
		cnt.start();

	}

}

class Rsp extends Thread{
	@Override
	public void run() {
		String input = JOptionPane.showInputDialog("가위, 바위, 보 입력");
		T07_ThreadGame.inputCheck = true;
		int comRsp = T07_ThreadGame.rsp_num;
		String result = "";
		switch(comRsp) {
		case 0 : 
			if(input.equals("가위")) {
				result = "비겼습니다."; break;
			}else if(input.equals("바위")) {
				result = "당신이 이겼습니다."; break;
			}else if(input.equals("보")) {
				result = "컴퓨터가 이겼습니다."; break;
			}
		case 1 : 
			if(input.equals("가위")) {
				result = "컴퓨터가 이겼습니다."; break;
			}else if(input.equals("바위")) {
				result = "비겼습니다."; break;
			}else if(input.equals("보")) {
				result = "당신이 이겼습니다."; break;
			}
		case 2 : 
			if(input.equals("가위")) {
				result = "당신이 이겼습니다."; break;
			}else if(input.equals("바위")) {
				result = "컴퓨터가 이겼습니다."; break;
			}else if(input.equals("보")) {
				result = "비겼습니다."; break;
			}
		}
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : " + T07_ThreadGame.com_rsp[comRsp]);
		System.out.println("당   신 : " + input);
		System.out.println("결   과 : " + result);
	}
}

class CntDown extends Thread{
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
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : " + T07_ThreadGame.com_rsp[T07_ThreadGame.rsp_num]);
		System.out.println("당   신 : 미입력");
		System.out.println("결   과 : 컴퓨터가 이겼습니다.");
		System.exit(0);
	}
}