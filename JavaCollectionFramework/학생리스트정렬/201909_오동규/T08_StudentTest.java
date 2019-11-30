package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
	문제 ) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 가지는
		  Student 클래스를 만든다.
		  
		  생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
		  
		  이 Student 객체들은 List에 저장하여 관리한다.
		  
		  List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
		   총점의 역순으로 정렬하는 부분을 프로그래밍 하시오.
		   (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
		   (학번 정렬기준은 Student 클래스 자체에서 제공하도록 하고,
		     총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */
public class T08_StudentTest{
	
	public static void main(String[] args) {
		
		List<Student> studentList = new ArrayList<>();
		
		studentList.add(new Student("14422001", "일동규", 71, 81, 91));
		studentList.add(new Student("14422012", "이동규", 72, 82, 92));
		studentList.add(new Student("14422004", "사동규", 74, 84, 94));
		studentList.add(new Student("14422033", "삼동규", 73, 83, 90));
		studentList.add(new Student("14422025", "오동규", 75, 85, 95));
		
		System.out.println("정렬 전");
		for(Student student : studentList) {
			System.out.println(student);
		}
		
		System.out.println("--------------------------------");
		
		Collections.sort(studentList); 	// 정렬하기
		
		System.out.println("학번의 오름차순으로 정렬 후..");
		for(Student student : studentList) {
			System.out.println(student);
		}
		
		System.out.println("--------------------------------");
		
		// 외부 정렬 기준을 이용한 정렬하기
		Collections.sort(studentList, new SortTotalDesc());
		
		System.out.println("총점의 내림차순으로 정렬 후..");
		int rank = 1;
		for(int i = 0; i < studentList.size(); i++) {
			if(i > 1 && studentList.get(i - 1).getTotal() == studentList.get(i).getTotal()) {
				studentList.get(i).setRank(rank);
			} else {
				rank = i;
				studentList.get(i).setRank(++rank);
			}
			System.out.println(studentList.get(i));
		}
	}
}

class Student implements Comparable<Student>{
	private String studentNum;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int total;
	private int rank;
	
	public String getStudentNum() {
		return studentNum;
	}


	public void setStudentNum(String studentNum) {
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


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	
	Student(String studentNum, String name, int korean, int english, int math){
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.total = korean + english + math;
	}

	
	@Override
	public int compareTo(Student student) {
		return getStudentNum().compareTo(student.getStudentNum());
	}
	
	@Override
	public String toString() {
		return "Student[ 등수 = " + rank + ", 학번 = " + studentNum + ", 이름 = " + name + ", 국어 = " + korean
								+ ", 영어 = " + english + ", 수학 = " + math + ", 총점 = " + total +"]";
		
	}
}

class SortTotalDesc implements Comparator<Student> {

	@Override
	public int compare(Student student1, Student student2) {
		if(student1.getTotal() > student2.getTotal()) {
			return -1;
		} else if(student1.getTotal() == student2.getTotal()) {
			if(student1.getStudentNum().compareTo(student2.getStudentNum()) > 0) {
				return -1;
			} else if(student1.getStudentNum().equals(student2.getStudentNum())) {
				return 0;
			}
			return 1;
		}
		return 1;
	}
}