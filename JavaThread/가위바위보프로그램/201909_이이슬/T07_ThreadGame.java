package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 	컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오.
 	
 	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 	사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 	
 	입력시간은 5초로 제한하고 카운트다운을 진행한다.
 	5초안에 입력이 없으면 게임을 진 것으로 처리한다.
 	
 	5초안에 입력이 완료되면 승패를 출력한다.
 	
 	결과예시)
 	== 결과 ==
 	컴퓨터 : 가위
 	당   신 : 바위
 	결   과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	
	//입력여부 확인
	public static boolean inputCheck = false;
	
	
	public static void main(String[] args) {
		Thread th1 = new Input();
		Thread th2 = new CountDown1();
		
		System.out.println("5초안에 입력해주세요");
		
		th1.start();
		th2.start();
		
	}

}

//데이터 입력 Thread
class Input extends Thread{
	@Override
	public void run() {
		
		String[] com = {"가위", "바위", "보"};
		String str = JOptionPane.showInputDialog("가위/바위/보 중 하나를 입력하세요");
		
		//컴퓨터 입력을 랜덥으로 받기
		int rsp = (int)(Math.random() * com.length);
		
		System.out.println("컴퓨터 : " + com[rsp]);
		System.out.println("당   신 : " + str);
		
		//입력완료되면 InputCheck변수를 true로 변경
		T07_ThreadGame.inputCheck = true;
		
		if(com[rsp].equals(str)) {
			System.out.println("결   과  : 비겼습니다." );
		}else if(com[rsp].equals("가위") && str.equals("바위") || com[rsp].equals("바위") && str.equals("보") || com[rsp].equals("보") && str.equals("가위")) {
			System.out.println("결   과  : 당신이 이겼습니다.");
		}else if(com[rsp].equals("바위") && str.equals("가위") || com[rsp].equals("보") && str.equals("바위") || com[rsp].equals("가위") && str.equals("보")) {
			System.out.println("결   과  : 컴퓨터가 이겼습니다.");
		}
	}
}

//카운트 다운 처리 Thread
class CountDown1 extends Thread{
	@Override
	public void run() {
		//5초카운트
		for(int i=5; i>=1; i--) {
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
		System.out.println("입력시간이 초과되었습니다.");
		System.exit(0);
	}
}
