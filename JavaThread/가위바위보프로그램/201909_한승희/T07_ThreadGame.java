package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보 난수를 이용하여 구하고 사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
	
	입력시간은 5초로 제한하고 카운트 다운을 출력한다.
	5초안에 입력이 없으면 게임을 진것으로 처리한다.
	
	5초안에 입력이 완료되면 승패를 출력한다.
	
	결과 예시)
	=====결과=====
	컴퓨터 : 가위
	당  신 : 바위
	결  과 : 당신이 이겼습니다.
*/
public class T07_ThreadGame {
	public static boolean answerCheck=false;
	public static void main(String[] args) {
		Thread th1 = new CountDown1();
		Thread th2 = new RSP_Game();
		
		th2.start();
		th1.start();
	}
}

class CountDown1 extends Thread
{
	@Override
	public void run() {
		System.out.println("카운트다운을 시작합니다.");
		for(int i=10;i>=1;i--)
		{
			if(T07_ThreadGame.answerCheck)
			{
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("=====결과=====");
		System.out.println("결  과 : 당신이 졌습니다.");
	}
}

class RSP_Game extends Thread
{
	@Override
	public void run() {
		String card[] = {"가위","바위","보"};
		
		System.out.println("가위바위보를 시작합니다.");
		String yourCard = JOptionPane.showInputDialog("당신의 패를 입력해주세요 : ");
		T07_ThreadGame.answerCheck=true;
		
		String comCard = card[(int)Math.random()*3];
		String result = "";
		
		if(yourCard.equals(comCard))
		{
			result = "비겼습니다.";
		}
		else
		{
			if(yourCard.equals("가위")&&comCard.equals("보")||yourCard.equals("보")&&comCard.equals("바위")||yourCard.equals("바위")&&comCard.equals("가위"))
			{
				result = "당신이 이겼습니다.";
			}
			else
			{
				result = "컴퓨터가 이겼습니다.";
			}
		}
		System.out.println("=====결과=====");
		System.out.println("컴퓨터 : " + comCard);
		System.out.println("당  신 : " + yourCard);
		System.out.println("결  과 : " + result);
	}
}