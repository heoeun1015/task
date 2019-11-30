package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
 * Student클래스를 만든다.
 * 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 이 Student객체들은 List에 저장하여 관리한다.
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 * 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 * (학번 정렬기준은 Student클래스에서 자체 제공하도록 하고,
 * 총점 정렬기준은 외부클래스에서 제공하도록 한다.
 *
 */
public class T08_StudentTest {
	public static void main(String[] args) {
		List<Student> student = new ArrayList<>();
		student.add(new Student("s1","학생1",40,30,20));
		student.add(new Student("s2","학생2",50,30,10));
		student.add(new Student("s3","학생3",60,30,20));
		student.add(new Student("s4","학생4",40,30,40));
		
		
		
		for(int i = 0; i < student.size(); i++) {
			for(int j =student.size()-1; j >=0; j--) {
				if(student.get(i).getSum() < student.get(j).getSum()) {
					int count = student.get(i).getLanking();
					student.get(i).setLanking(++count);
				}
			}
		}
	
		Collections.sort(student);
		
		for(int i =0; i < student.size(); i++) {
			System.out.println(student.get(i).toString());
		}
	
		System.out.println("===========================================");
		
		Collections.sort(student, new SumDesc());
		
		for(int i =0; i < student.size(); i++) {
			System.out.println(student.get(i).toString());
		}
	}
}

class SumDesc implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		if(Integer.compare(o1.getSum(), o2.getSum()) == 0) {
			if(o1.getStnum().compareTo(o2.getStnum())==1) {
				return -1;
			}
		}
		return Integer.compare(o1.getSum(), o2.getSum()) * -1;
	}
	
}


class Student implements Comparable<Student>{

	
	private String stnum;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int lanking;
	
	public String getStnum() {
		return stnum;
	}
	public void setStnum(String stnum) {
		this.stnum = stnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		
		this.sum = sum;
	}
	public int getLanking() {
		return lanking;
	}
	public void setLanking(int lanking) {
		this.lanking = lanking;
	}
	
	public Student(String stnum, String name, int kor, int eng, int math) {
		super();
		this.stnum = stnum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		setSum(kor + eng + math);
		this.lanking = 1;
	}
	
	@Override
	public String toString() {
		return "학번 : " + stnum + "이름" + name + "국어 : " + kor 
				+ "영어 : " + math + "수학 : " + math + "총점 : " + sum
				+ "등수" + lanking;

	}
	
	
	@Override
	public int compareTo(Student o) {
		
		return getStnum().compareTo(o.getStnum()) * 1;
	}
	
	
}