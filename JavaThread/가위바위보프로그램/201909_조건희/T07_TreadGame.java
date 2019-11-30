

import javax.swing.JOptionPane;

/*
 *  컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 *  
 *  컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 *  사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 *  
 *  입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 *  5초안에 입력이 없으면 게임을 진것으로 처리한다.
 *  
 *  5초안에 입력이 완료되면 승패를 출력한다.
 *  
 *  결과예시 )
 *  	=== 결과 ===
 *  	컴퓨터 : 가위
 *  	당  신  : 바위
 *		결  과  : 당신이 이겼습니다.
 *		
 */

public class T07_TreadGame {
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Countdown count = new Countdown();
		count.start();
		
		String ran[] = {"가위","바위","보"};
		int random = (int) (Math.random() * 3);
		String comresult;
		if(random == 0 ) 
			   comresult = ran[0];		
		else if(random == 1) 
			   comresult = ran[1];
		else 
			   comresult = ran[2];
		 			
		String user = JOptionPane.showInputDialog("가위 바위 보를 입력하세요");
		if(user != null) {
			inputCheck = true;
		}
		
		System.out.println("내가 낸 것 : " + user);
		System.out.println("컴퓨터가 낸 것 : " + comresult);
		
		if(user.equals(comresult)) {
			System.out.println("비겼습니다.");
		}else if((comresult.equals("가위")) && user.equals("보") || (comresult.equals("바위")) && user.equals("가위") || (comresult.equals("보")) && user.equals("바위")) {
			//컴퓨터가 이길 경우 
			//가위 보자기, 바위 가위 , 보자기 바위
			System.out.println("컴퓨터가 이겼습니다.");
		} else {
			System.out.println("사용자가 이겼습니다.");
		}			
	}
		
}

class Countdown extends Thread {
	@Override
	public void run() {
		for(int i = 5; i >= 1; i--) {

			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면 
			// run() 메서드를 종료시킨다. 즉 현재 쓰레드를 종료시킨다.
			if(T07_TreadGame.inputCheck) {
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}				
		}
		// 10초가 경과되었는데도 입력이  없으면 프로그램을 종료한다.
		System.out.println("5초가 지났습니다. 게임을 졌습니다..");
	}
}
