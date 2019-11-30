package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
 * Student클래스를 만든다.
 * 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 
 * 이 Student객체들은 List에 저장하여 관리한다.
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 * (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */
public class T08_StudentTest {
	public static void main(String[] args) {
		List<Student> stList = new ArrayList<Student>();
		stList.add(new Student("101","홍길동", 80, 45, 65));
		stList.add(new Student("104","변학도", 65, 70, 82));
		stList.add(new Student("102","성춘향", 40, 65, 55));
		stList.add(new Student("109","이순신", 95, 80, 75));
		System.out.println("정렬 전 : ");



		for(Student stu : stList) {
			System.out.println(stu);
		}
		System.out.println("==================");
		Collections.sort(stList);
		System.out.println();


		System.out.println("정렬 후(학번순 오름차순) : ");
		for(Student stu : stList) {
			System.out.println(stu);

		}
		System.out.println("======================");
		System.out.println("======================");
		System.out.println("순위로 정렬 : ");
		System.out.println();

		for(int i=0; i< stList.size(); i++) {
			int rank2 =1;
			
			for(int j=0; j<stList.size(); j++) {
				
				if(stList.get(i).getSum() < stList.get(j).getSum()) {
					stList.get(i).setRank(rank2++);
				}
			}
		}

	}
}



class Student implements Comparable<Student>{
	private String number;
	private String name;
	private int a1; // 국어
	private int a2; // 영어
	private int a3; // 수학
	private int sum;
	private int rank;


	public Student(String number, String name, int a1, int a2, int a3) {
		super();
		this.number = number;
		this.name = name;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.sum = a1+a2+a3;
		
	}





	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getA1() {
		return a1;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public int getA2() {
		return a2;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public int getA3() {
		return a3;
	}

	public void setA3(int a3) {
		this.a3 = a3;
	}
	@Override
	public String toString() {

		return "Student[학번 = "+number + ", 이름 = " + name +  ", 국어 = "+a1 +", 영어 = "+a2+" 수학 ="+a3+" 총점="+sum+" 순위 ="+rank+"]";
	}

	@Override
	public int compareTo(Student stu) {

		return getNumber().compareTo(stu.getNumber());
	}

}
	class St implements Comparator<St>{
	private int sum;
	private int rank;


	public St(int sum, int rank) {
		super();
		this.sum = sum;
		this.rank = rank;
	}


	public int getsum() {
		return sum;
	}




	public void setsum(int sum) {
		this.sum = sum;
	}




	public int getRank() {
		return rank;
	}




	public void setRank(int rank) {
		this.rank = rank;
	}








	@Override // 내림차순
	public int compare(St o1, St o2) {

		return new Integer(o1.getsum()).compareTo(o2.getsum())*-1;
	}






}