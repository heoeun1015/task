package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
	문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
		student클래스를 만든다.
		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
		
		이 Student객체들은 List에 저장하여 관리한다.
		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
		(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 
		총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */
public class T08_StudentTest {

	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<>();

		studentList.add(new Student("201100713", "조아라", 80, 80, 100));
		studentList.add(new Student("201100714", "김민정", 70, 90, 80));
		studentList.add(new Student("201100704", "고은진", 40, 50, 100));
		studentList.add(new Student("201100717", "신예진", 100, 40, 50));
		studentList.add(new Student("201100707", "오은지", 60, 70, 80));
		studentList.add(new Student("201100708", "오은지", 60, 70, 40));
		
		int[] rank = new int[6];
		for(int i = 0; i <rank.length; i++){
			rank[i] = 1;
		}
		for(int i = 0; i < studentList.size(); i++){
			for(int j = 0; j < studentList.size(); j++){
				//등수를 구할 점수보다 큰 점수가 있는 경우
				if(studentList.get(i).getSum()< studentList.get(j).getSum()){
					//등수를 1 증가시킨다.
					rank[i]++;
				}
			}
		}
		
		for(int k=0; k < studentList.size(); k++) {
			studentList.get(k).setRank(rank[k]);
		}
		
		Collections.sort(studentList);
		
		System.out.println("학번 오름차순 정렬 : " );
		
/*		for(int i = 0; i < studentList.size(); i++) {
			System.out.println(studentList.get(i));
		}*/
		for(Student student : studentList) {
			System.out.println(student);
		}
		
		Collections.sort(studentList, new DescStudent());
		
		System.out.println("총점 내림차순 정렬: ");
		for(Student student : studentList) {
			System.out.println(student);
		}
		
	}
}

class DescStudent implements Comparator<Student>{

	@Override
	public int compare(Student student1, Student student2) {

		if(student1.getSum() > student2.getSum()) {
			return -1;
		}else if(student1.getSum() == student2.getSum()) {
			return student1.getNum().compareTo(student2.getNum()) * -1;
		}else {
			return 1;
		}
	}
	
}

class Student implements Comparable<Student> {
	private String num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	public Student(String num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Student[num=" + num
				+ ", name=" + name
				+ ", kor=" + kor
				+ ", eng=" + eng
				+ ", math=" + math
				+ ", sum=" + sum
				+ ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student student) {
		// 학번의 오름차순 정렬
		return this.getNum().compareTo(student.getNum());
	}
}
