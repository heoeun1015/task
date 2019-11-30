package Kr_or_ddit_basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.swing.JOptionPane;

/*
 	컴퓨터와 가위 바위 보 를 진행하는 프로그램을 작성하시오.
 	
 	컴퓨터의 가위 바위 보는 난수를 이용하여 구하고 
 	
 	사용자의 가위 바위 보는 ShowInpuDiolog() 메서드를 이용하여 입력받는다.
 	
 	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 	5초안에 입력이 없으면 게임을 진것으로 처리한다.
 	
 	5초에 입력이 완료되면 승패를 출력한다.
 	
 	결과예시)
 		=== 결과 ===
 		컴퓨터 : 가위
 		당   신 : 바위
 		결   과 : 당신이 이겼습니다.
 		
 */


public class T07_ThreadGame {

	

	public static boolean inputCheck = false;
		
	/* 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고  */
	 	
	private HashSet<Integer> nansu;
	
		
	private T07_ThreadGame() {		
		nansu = new HashSet<>();		
	}
	
	
	void nansuList() {
		int num1;
		while(nansu.size() < 3 ) {
			num1 = (int)(Math.random()*3 + 1);
			nansu.add(num1);
		}
				
		List<Integer> nansus = new ArrayList<Integer>(nansu);
		Collections.shuffle(nansus);
				
		System.out.println(nansus);
		
	}
	
	
	public static void main(String[] args) {
		
		Thread t1 = new DataInput2();
		Thread t2 = new Count();
		
		T07_ThreadGame n1 = new T07_ThreadGame();
	
		n1.nansuList();
		
		
	}
	
	
}









class DataInput2 extends Thread {
	
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		T07_ThreadGame.inputCheck = true;
		
		System.out.println("당신  :" +  str);
		
		
	}
		
}



class Count extends Thread{
	
	
	@Override
	public void run() {
		
		for(int i = 5; i >= 1; i--) {
			
			if(T07_ThreadGame.inputCheck) {
				return;
			}
			System.out.println(i);
		}
		
		
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("5초가 지났습니다. 게임에 지셨습니다.");
		
		System.out.println(0);
		
	}
	
	
	
}

