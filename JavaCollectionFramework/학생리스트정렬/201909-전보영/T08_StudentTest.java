package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 	문제) 학번,이름,국어점수,영어점수,수학점수,총점, 등수를 멤버로 갖는
 		 Student 클래스를 만든다.
 		 생성자는 학번,이름,국어,영어,수학점수만 매개변수로 받아서 처리한다. 		 
 		 이 Student객체들은 List에 저장하여 관리한다.		 
 		 List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 		 
 		 총점의 역순으로 정렬하는 부분을 프로그램하시오.
 		 (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)		
		  
 		 (학번정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에 제공하도록 한다.)
 */

public class T08_StudentTest {
	
	public static void main(String[] args) {
		
		List<Student> stuList = new ArrayList();
		
		stuList.add(new Student("20190303", "홍길동", 80, 90, 100));
		stuList.add(new Student("20190505", "변학도", 50, 70, 80));
		stuList.add(new Student("20190101", "성춘향", 40, 90, 60));
		stuList.add(new Student("20190202", "이순신", 80, 50, 100));
		stuList.add(new Student("20190404", "강감찬", 70, 90, 50));
		
		System.out.println("정렬전");
		
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		
		
		
		Collections.sort(stuList, new SortNumDesc());
	
		System.out.println("학번의 오름차순으로 정렬 후 ...");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
				
		Collections.sort(stuList, new sortStuDesc());
		System.out.println("총점 내림차순으로 정렬 후 ");
			

	}
	
}

class sortStuDesc implements Comparator<Student>{

	
	@Override
	public int compare(Student sum1, Student sum2) {
			
		return Integer.compare(sum1.getSum(), sum2.getSum())*-1;
		
	}
	
}




class Student implements Comparable<Student>{

// 학번,이름,국어점수,영어점수,수학점수를 멤버로 갖는 Student 클래스를 만든다.
	
	private String number;
	private String name;
	private int kNumber;
	private int eNumber;
	private int mNumber;

	private int sum;
	private int grade;
		

	public Student(String string, String name, int kNumber, int eNumber, int mNumber) {
		super();
		this.number = string;
		this.name = name;
		this.kNumber = kNumber;
		this.eNumber = eNumber;
		this.mNumber = mNumber;
		this.sum = kNumber + eNumber + mNumber;
		this.grade = sum / 3;
	}
		



	//getter setter 생성하기
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
	public int getkNumber() {
		return kNumber;
	}
	public void setkNumber(int kNumber) {
		this.kNumber = kNumber;
	}
	public int geteNumber() {
		return eNumber;
	}
	public void seteNumber(int eNumber) {
		this.eNumber = eNumber;
	}
	public int getmNumber() {
		return mNumber;
	}
	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
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
			
			return "Student [학번 = " + number + ", 이름 =" + name + ", 국어점수 = " + kNumber + ", 영어점수 = " + eNumber + ", 수학점수 = " + mNumber + " ]";
		}
	
	
	
	@Override
	public int compareTo(Student stu) {		
		
		return getNumber().compareTo(stu.getNumber());
		
	}
	

	
}