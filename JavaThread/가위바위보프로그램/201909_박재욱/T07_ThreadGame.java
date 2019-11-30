package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위바위보 게임
 * 컴퓨터의 가위 바위 보는 난수로
 * 사용자의 가위 바위 보는 showInputDialog()메소드 이ㅛㅇ
 * 
 * 입력 시간은 5초로 제한, 카운트 다운을 진행
 * 5초 안에 입력이 없을 시 게임을 진것으로 처리
 * 
 * 5초 안에 입력 완료 시 승패를 출력 
 * 결과예시)
 * === 결과 ===
 * 컴퓨터 : 가위
 * 당 신 : 바위
 * 결과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	
	
	public static void main(String[] args) {
		Data th3 = new Data();
		Count th4 = new Count();
		th3.start();
		th4.start();
	}
}
/*
 * 데이터를 입력받는 쓰레드
 */
class Data extends Thread{
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위,바위,보를 입력하세요");
		//입력 완료 시 inputCheck를 True로
		T07_ThreadGame.inputCheck = true;
		String[] rsp = {"가위","바위","보"};
		String com = rsp[(int)((Math.random()*2)+1)];
		System.out.println("===결과===");
		System.out.println("컴퓨터 : " + com);
		System.out.println("당 신 : " + str);
		if(str.equals("가위") && com.equals("바위") || str.equals("바위") && com.equals("보") 
				|| str.equals("보") && com.equals("가위")) {
			System.out.println("컴퓨터가 이겼습니다.");
		}else if(str.equals("가위") && com.equals("보") || str.equals("바위") && com.equals("가위") 
				|| str.equals("보") && com.equals("바위")){
			System.out.println("당신이 이겼습니다.");
		}else {
			System.out.println("비겼습니다.");
		}
	}
}
class Count extends Thread{
	@Override
	public void run() {
		for(int i=5;i>=1;i--) {
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
		System.out.println("5초가 지났습니다. 당신이 졌습니다");
		System.exit(0);
	}
}