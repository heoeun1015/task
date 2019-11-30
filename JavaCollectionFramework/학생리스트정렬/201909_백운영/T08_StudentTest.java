package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
 * 		 Student클래스를 만든다.
 * 		 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 
 * 		 이 Student객체들은 List에 저장하여 관리한다.
 * 		 List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 * 		 총점의 역순으로 정렬하는 부분을 부로그램 하시요.
 * 		 (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 * 		 (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
 * 		 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */
public class T08_StudentTest {

	public static void main(String[] args) {
		
		List<Students> stList = new ArrayList<>();
		
		stList.add(new Students(1008, "백운영", 88, 93, 95));
		stList.add(new Students(1005, "홍길동", 88, 93, 95));
		stList.add(new Students(1001, "강감찬", 89, 98, 90));
		stList.add(new Students(1006, "김두한", 78, 83, 77));
		stList.add(new Students(1003, "최번개", 98, 80, 89));
		
		System.out.println("=================================== 정렬 전 ===================================");
		
		for(Students st : stList) {
			System.out.println(st);
		}
		
		
		System.out.println("=================================== 정렬 후 ===================================");
		
		Collections.sort(stList, new TotalScore());	
		
		for(int i = 0; i < stList.size(); i++) {
			Students stRank = stList.get(i);
			stRank.setRank(i+1);
		}
		
		for(Students st : stList) {
			System.out.println(st);
		}
		
		
	}
}


	class TotalScore implements Comparator<Students>{

		@Override
		public int compare(Students total, Students total1) {
			
			if(total.getTotalScore() > total1.getTotalScore()){
				return -1;
			}else if(total.getTotalScore() == total1.getTotalScore()) {
				return new Integer(total.getStudentNum()).compareTo(total1.getStudentNum()) * -1;
			}else {
				return 1;
			}

		}
		
	}

	
	class Students implements Comparable<Students>{

		private int studentNum;
		private String name;
		private int korean;
		private int english;
		private int math;
		public int rank;
		private int totalScore;
		
		
		public Students(int studentNum, String name, int korean, int english, int math) {
			super();
			this.studentNum = studentNum;
			this.name = name;
			this.korean = korean;
			this.english = english;
			this.math = math;
			this.totalScore = korean + english + math;
			this.rank = 1;
			
		}
	
	
				
		public int getStudentNum() {
			return studentNum;
		}


		public void setStudentNum(int studentNum) {
			this.studentNum = studentNum;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public int getKorean() {
			return korean;
		}


		public void setKorean(int korean) {
			this.korean = korean;
		}


		public int getEnglish() {
			return english;
		}


		public void setEnglish(int english) {
			this.english = english;
		}


		public int getMath() {
			return math;
		}


		public void setMath(int math) {
			this.math = math;
		}
		
		
		public int getRank() {
			return rank;
		}


		public void setRank(int rank) {
			this.rank = rank;
		}


		public int getTotalScore() {
			return totalScore;
		}


		public void setTotalScore(int totalScore) {
			this.totalScore = totalScore;
		}


		public String toString() {
			return "등수 : " + rank + " 학번 :  " + studentNum + " 이름 : " + name + " 국어 : " + korean + " 영어 : " + english + " 수학 : " + math + " 총점 : " + totalScore;
		}


		@Override
		public int compareTo(Students o) {
			return 0;
		}
	}
