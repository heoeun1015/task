package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 	문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
 		Student클래스를 만든다.
 		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 		
 		이 Student객체들은 List에 저장하여 관리한다.
 		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 		(학번의 정렬기준은 Student클래스 자체에서 제공 하도록 하고,
 		총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */
public class T08_StudentTest {

	public static void main(String[] args) {
		List<Student> memberList = new ArrayList<>();
		memberList.add(new Student("4", "홍길동", 80, 90, 99));
		memberList.add(new Student("3", "김명성", 86, 93, 77));
		memberList.add(new Student("1", "백운영", 61, 95, 78));
		memberList.add(new Student("5", "조윤호", 48, 96, 79));
		memberList.add(new Student("2", "최도혁", 86, 96, 74));
		memberList.add(new Student("6", "오동규", 86, 98, 72));
		
		System.out.println("정렬 전");
		for(Student std : memberList) {
			System.out.println(std);
		}

		System.out.println();
		System.out.println("======================================================================");
		System.out.println();
		Collections.sort(memberList);
		System.out.println("학번 오름차순 정렬 후");
		for(Student std : memberList) {
			System.out.println(std);
		}
		
		System.out.println();
		System.out.println("======================================================================");
		System.out.println();
		Collections.sort(memberList,new Studentsort()); //객체로 넘겨야함
		System.out.println("총점 내림차순 정렬 후");
		for(Student std : memberList) {
			
		
			for(int i=0; i<memberList.size(); i++) {
				
				int first = 1;
				for(int j=0; j<memberList.size(); j++) {
					if(memberList.get(i).getSum() < memberList.get(j).getSum()) {
						first++;
					}
				}
				memberList.get(i).setGrade(first);

			}System.out.println(std);
		}
		
	}

}
class Studentsort implements Comparator<Student>{

	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getSum()>std2.getSum()) {
			return -1;
		}else if(std1.getSum() == std2.getSum()) {
				return std1.getNum().compareTo(std2.getNum())*-1; //총점이 같을때 학번의 내림차순으로 하는 방법 물어보기
		}else {
			return 1;
		}
	}
	
}

class Student implements Comparable<Student>{
	 private String num;		//학번
	 private String name;	//이름
	 private int korscore;	//국어점수
	 private int engscore;	//영어점수
	 private int mathscore;	//수학점수
	 private int sum;		//총점
	 private int grade;		//등수
	 
	 public Student(String num, String name, int korscore, int engscore, int mathscore) {
		 super();
		 this.num = num;
		 this.name = name;
		 this.korscore = korscore;
		 this.engscore = engscore;
		 this.mathscore = mathscore;
		 this.sum = korscore + engscore + mathscore;
		 this.grade = 1;
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

	public int getKorscore() {
		return korscore;
	}

	public void setKorscore(int korscore) {
		this.korscore = korscore;
	}

	public int getEngscore() {
		return engscore;
	}

	public void setEngscore(int engscore) {
		this.engscore = engscore;
	}

	public int getMathscore() {
		return mathscore;
	}

	public void setMathscore(int mathscore) {
		this.mathscore = mathscore;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getGrade() {
		
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "Member[ 학번=" + num
				+ ", 이름 = " + name
				+ ", 국어 = " + korscore
				+ ", 영어 = " + engscore
				+ ", 수학 = " + mathscore
				+ ", 총점 = " + sum
				+ ", 등수 = " + grade + "]";
	}
	

	@Override
	public int compareTo(Student std) {
		
		return this.getNum().compareTo(std.getNum());
	}
	 
 }