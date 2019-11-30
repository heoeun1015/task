package kr.or.ddit.basic;

import java.util.Random;

import javax.swing.JOptionPane;

/*
 	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 	
 	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 	사용자의 가위 바위 보는 shpwInputDialog()메서드를 이용하여 입력받는다.
 	
 	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 	5초안에 입력이 없으면 게임을 진것으로 처리한다.
 	
 	5초안에 입력이 완료되면 승패를 출력한다.
 	
 	결과에서)
 		=== 결과 ===
 		컴퓨터 : 가위
 		사용자 : 바위
 		결  과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	public static boolean inputCheck = false; // 입력체크
	public static String str= null;
	public static void main(String[] args) {
		
		Thread th1 = new DataInput1();
		Thread th2 = new CountDown1();
		th1.start();
		th2.start();	
	}
}

/**
 * 데이터를 입력받는 쓰레드
 */
class DataInput1 extends Thread{
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		// 입력이 완료되면 inputCheck변수를 true로 변경한다.
		T06_ThreadTest.inputCheck = true;
		String[] com = new String[]{"가위", "바위", "보"};
		String com1 = com[(int)(Math.random()*3)];
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : " + com1 );
		System.out.println("사용자 : " + str);
		if(str.equals("가위")|| str.equals("바위") || str.equals("보") ){
			if(com1.equals(str)){
				System.out.println("결  과 : 비겼습니다");
			}else if(com1.equals("가위")&&str.equals("바위")||com1.equals("바위")&&str.equals("보")||com1.equals("보")&&str.equals("가위")){
					System.out.println("결  과 : 당신이 이겼습니다.!!");
				}else{
					System.out.println("결  과 : 당신이 졌습니다.!!");
				}
			}else{
				System.out.println("잘못입력하셨습니다.");
			}
	}
}
/**
 * 카운트다운을 처리하는 쓰레드 클래스
 */
class CountDown1 extends Thread{
	@Override
	public void run() {
		for(int i = 10; i >= 1; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면
			// run()메서드를 종료시킨다. 즉 현재 쓰레드를 종료시킨다.
			if(T06_ThreadTest.inputCheck) {
				return; // run() 메서드가 종료되면 쓰레드도 끝난다.
			}
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("10초가 지났습니다. 당신이 졌습니다.");
		System.exit(0); // 프로그램을 종료시키는 명령
	}
}
