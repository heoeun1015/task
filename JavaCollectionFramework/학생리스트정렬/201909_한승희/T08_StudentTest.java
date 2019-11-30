package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
	문제 1) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
		Student 클래스를 만든다.
		
		 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아 처리한다.
		 
		 이 Student 객체들은 List에 저장하여 관리한다.
		 List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
		총점의 역순으로 정렬하는 부분을 프로그램 하라.
		(총점이 같으면 학번의 내림차순으로 정렬되도록 하라.)
		(학번 정렬기준은 Student 클래스 자체에서 제공하도록 하고,
		총점 정렬기준은 외부클래스에서 제공하도록 하라.)
*/

public class T08_StudentTest {
	public static void main(String[] args) {
		
		List<Student> student = new ArrayList<Student>();
		
		student.add(new Student(1, "가나다", 100, 22, 11));
		student.add(new Student(2, "ABC", 22, 33, 44));
		student.add(new Student(3, "ASD", 44, 55, 66));
		student.add(new Student(4, "ZXC", 77, 88, 99));
		student.add(new Student(5, "QWE", 100, 11, 22));
		
		Collections.shuffle(student);
		
		System.out.println(student);
		
		Collections.sort(student);
		
		System.out.println(student);
		
		System.out.println();
		System.out.println();
		
		Collections.sort(student, new sortSum());
		
		System.out.println("등수\t학번\t총점");
		for(int i=0;i<student.size();i++)
		{
			Student stu = student.get(i);
			
			stu.setRank(i+1);
			System.out.println(stu);
		}
	}
}

class Student implements Comparable<Student>
{
	int StudentNumber;
	String StudentName;
	int koreanScore;
	int englishScore;
	int mathScore;
	int sumScore;
	int rank;
	
	public Student(int studentNumber, String studentName, int koreanScore, int englishScore, int mathScore) {
		super();
		StudentNumber = studentNumber;
		StudentName = studentName;
		this.koreanScore = koreanScore;
		this.englishScore = englishScore;
		this.mathScore = mathScore;
		this.sumScore = koreanScore + englishScore + mathScore;
		this.rank = 0;
	}
	
	public int getStudentNumber() {
		return StudentNumber;
	}
	public void setStudentNumber(int studentNumber) {
		StudentNumber = studentNumber;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public int getKoreanScore() {
		return koreanScore;
	}
	public void setKoreanScore(int koreanScore) {
		this.koreanScore = koreanScore;
	}
	public int getEnglishScore() {
		return englishScore;
	}
	public void setEnglishScore(int englishScore) {
		this.englishScore = englishScore;
	}
	public int getMathScore() {
		return mathScore;
	}
	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	public int getSumScore() {
		return sumScore;
	}
	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return rank + "\t" + StudentNumber + "\t" + sumScore;
	}
	
	@Override
	public int compareTo(Student o) {
		return Integer.compare(getStudentNumber(), o.getStudentNumber());
	}

	
	
}

class sortSum implements Comparator<Student>
{
	@Override
	public int compare(Student o1, Student o2) {
		if(o1.getSumScore()<o2.getSumScore())
		{
			return 1;
		}
		else if(o1.getSumScore()==o2.getSumScore())
		{
			if(o1.getStudentNumber() < o2.getStudentNumber())
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
		else
		{
			return -1;
		}
	}
}