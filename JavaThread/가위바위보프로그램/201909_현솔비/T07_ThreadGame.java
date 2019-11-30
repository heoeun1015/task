package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

/*
 	컴퓨터와 가위바위보를 진행하는 프로그램을 작성하시오
 	
 	컴퓨터의 가위 바위보는 난수를 이용하여 구하고
 	사용자의 가위바위보는 showInputDialog()메서드를 이용하여 입력받는다.
 	
 	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 	5초안에 입력이 없으면 게임을 진것으로 처리한다.
 	
 	5초안에 입력이 완료되면 승패를 출력한다.
 	
 	결과예시 ) 
 	=== 결과 ===
 	컴퓨터 : 가위
 	당  신 : 바위
 	결  과 : 당신이 이겼습니다.
 */

public class T07_ThreadGame {

	//입력 여부를 확인하기 위한 변수 선언
	//모든 쓰레드에서 공통으로 사용할 변수
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Thread th1 = new DataInput2();
		Thread th2 = new CountDown2();
		
		th1.start();
		th2.start();
	}
}

//데이터를 입력받는 쓰레드
class DataInput2 extends Thread{
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위/바위/보 중 하나를 입력하세요.");
		
		//입력이 완료되면 inputCheck변수를 true로 변경한다.
		T07_ThreadGame.inputCheck = true;
		
		String[] randomArr = {"가위", "바위", "보"};
		int random =(int)(Math.random()*3+1);	//1~3의 난수
	
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : " + randomArr[random-1]);	//randomArr[0]= 가위, randomArr[1]=바위, randomArr[2]=보
		System.out.println("당  신 : " + str);
			
		 if(str.equals("가위") && random == 3 || str.equals("바위") && random == 1 || str.equals("보") && random == 2 ){
		 System.out.println("결  과 : 당신이 이겼습니다.");
		 }else if (str.equals("가위") && random == 2 || str.equals("바위") && random == 3 || str.equals("보") && random == 1 ){
		 System.out.println("결  과 : 당신이 졌습니다.");
		 }else if (str.equals("가위") && random==1 || str.equals("바위") && random == 2 || str.equals("보") && random == 3){
		 System.out.println("결  과 : 비겼습니다.");
		 }
	}
}

//카운트다운을 처리하는 쓰레드 클래스
class CountDown2 extends Thread{
	
	@Override
	public void run() {
		for(int i=5; i>=1; i--) {
			
			//입력이 완료되었는지 여부를 검사하고 입력이 완료되면
			//run()메서드를 종료시킨다. 즉 현재 쓰레드를 종료시킴
			if(T07_ThreadGame.inputCheck) {
				return; //가장바깥메서드 run()메서드벗어남. run()메서드가 종료되면 쓰레드도 끝난다.
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//5초가 경과되었는데도 입력이 없으면 게임을 종료한다.
		System.out.println("제한시간 초과. 당신이 졌습니다.");
		
		System.exit(0); 		//프로그램을 종료시키는 명령
		}
	}