package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
	문제1) 학번, 이름, 국어점수, 영어점수, 수학정수, 총점, 등수를 멤버로 갖는
		Student클래스를 만든다.
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
		studentList.add(new Student("2", "ㄱㄱㄱ", 100, 100, 100));
		studentList.add(new Student("1", "ㄴㄴㄴ", 10, 10, 10));
		studentList.add(new Student("5", "ㄹㄹㄹ", 99, 99, 99));
		studentList.add(new Student("3", "ㄷㄷㄷ", 50, 50, 50));
		studentList.add(new Student("6", "ㅁㅁㅁ", 99, 99, 99));
		studentList.add(new Student("4", "ㅂㅂㅂ", 99, 99, 99));
		

		
		
		//석차를 구한다.
		for(int i = 0; i < studentList.size(); i++){
			for(int j = 0; j < studentList.size(); j++){
				if(studentList.get(i).sum < studentList.get(j).sum){
					studentList.get(i).setRank(studentList.get(i).getRank()+1); 
				}
			}
		}
		
		
		System.out.println("정렬 전");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		for(Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("---------------------------------------------------");
		
		Collections.sort(studentList);
		System.out.println("학번의 오름차순으로 정렬 후...");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		for(Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("---------------------------------------------------");
		
		Collections.sort(studentList, new SortSumDesc());
		System.out.println("총점의 내림차순으로 정렬 후...");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		for(Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("---------------------------------------------------");
		


	}
}


class SortSumDesc implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
				if(s1.getSum() > s2.getSum()) {
					return -1;
				}else if(s1.getSum() == s2.getSum()) {
					return s1.getNum().compareTo(s2.getNum()) * -1;
				}else {
					return 1;
				}
	}

}


class Student implements Comparable<Student>{
	String num;
	String name;
	int kor;
	int eng;
	int math;
	
	int sum;
	int rank;
	
	public Student(String num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor + eng + math;
		this.rank = 1;
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
		return num + "\t" + name + "\t" + kor  + "\t" + eng + "\t" + math + "\t"+ sum + "\t"+ rank;
	}

	@Override
	public int compareTo(Student s) {
		return getNum().compareTo(s.getNum());
	}


	
	
	
}