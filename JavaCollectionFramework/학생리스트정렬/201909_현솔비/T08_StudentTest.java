package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학 점수, 총점, 등수를 멤버로 갖는
 * 		Student클래스를 만든다.
 * 		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 
 * 		이 Student 객체들은 List에 저장하여 관리한다. 
 * 		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 //comparable compareTo구현
 * 		총점의 역순으로 정렬하는 부분을 프로그램 하시오.					//총점의멤버변수에 셋팅 후 외부정렬자 comparator구현 메서드 : compare, Student타입의 객체두개가 파라미터
 * 		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)?
 * 		(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
 * 		총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */

public class T08_StudentTest {

	public static void main(String[] args) {
		
		List<Student> studentList = new ArrayList<>();
		
		studentList.add(new Student("1", "현솔비", 100, 90, 80));
		studentList.add(new Student("2", "박희제", 90, 90, 80));
		studentList.add(new Student("3", "전보영", 100, 90, 80));
		studentList.add(new Student("5", "오동규", 70, 90, 80));
		studentList.add(new Student("4", "유지상", 100, 100, 90));
		
		
		for(int i=0; i<studentList.size(); i++) {
			for(int j=0; j<studentList.size(); j++) {			//등수를 구할 점수보다 큰 점수가 있는 경우 1을 증가시킨다.
				if(studentList.get(j).getSum() > studentList.get(i).getSum() ) {	
					studentList.get(i).setRank(studentList.get(i).getRank()+1);
				}
			}
		}
		
		System.out.println("정렬 전");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		for (Student s : studentList) {
			System.out.println(s);
		}
		
		System.out.println("------------------------------------------");
		
		
		Collections.sort(studentList); //정렬하기
		
		System.out.println("학번의 오른차순 정렬 후...");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		for (Student s : studentList) {
			System.out.println(s);
		}
		
		System.out.println("------------------------------------------");
		
		
		//외부 정렬 기준을 이용해 총점 역순으로 정렬하기
		Collections.sort(studentList, new SortSumDesc());
		System.out.println("총점의 내림차순 정렬 후...");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		for (Student s : studentList) {
			System.out.println(s);
		}
		
		System.out.println("------------------------------------------");
	}
}

//총점의 역순으로 정렬(내림차순)
class SortSumDesc implements Comparator <Student> {
	@Override
	public int compare(Student s1, Student s2) {
		if(s1.getSum() > s2.getSum()) {
			return -1;
		}else if(s1.getSum() == s2.getSum()) {
			//총점같을때 학번 내림차순
			return s1.getNum().compareTo(s2.getNum())*-1;
		}else {
			return 1;
		}
	}
}

class Student implements Comparable<Student>{
	private String num;		//학번
	private String name;	//이름
	private int score1;		//국어점수
	private int score2;		//영어점수
	private int score3;		//수학점수
	int sum;				//총점
	private int rank;
	
	
	public Student(String num, String name, int score1, int score2, int score3) {
		super();
		this.num = num;
		this.name = name;
		this.score1 = score1;
		this.score2 = score2;
		this.score3 = score3;
		this.sum = score1+score2+score3;
		this.rank = 1;						//등수배열을 1로
	}


	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getscore1() {
		return score1;
	}
	public void setScore1(int score1) {
		this.score1 = score1;
	}
	public int getScore2() {
		return score2;
	}
	public void setScore2(int score2) {
		this.score2 = score2;
	}
	public int getScore3() {
		return score3;
	}
	public void setScore3(int score3) {
		this.score3 = score3;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString(){
		return num +"\t"+ name + "\t"+ score1  + "\t"+ score2 + "\t"+ score3 
				 + "\t"+ sum +"\t"+rank;
	}
	
	
//학번의 오름차순으로 정렬
	@Override
	public int compareTo(Student s) {
		return getNum().compareTo(s.getNum());
	}	
}

	