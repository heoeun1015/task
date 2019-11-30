package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 문제) 학번, 이름 , 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
 * 		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 		이 Student객체들은 List에 저장하여 관리한다.
 * 		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 * 		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * 		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 * 		(학번 정렬기준은 Student 클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다.
 */

public class T08_StudentTest {

	public static void main(String[] args) {
		
		ArrayList<Student> stList = new ArrayList<>();
		stList.add(new Student(2, "오제민", (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50)));
		stList.add(new Student(6, "박신규", (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50)));
		stList.add(new Student(3, "이이슬", (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50)));
		stList.add(new Student(1, "이선욱", (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50)));
		stList.add(new Student(5, "김영호", (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50)));
		stList.add(new Student(4, "최도혁", (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50), (int)(Math.random() * 51 + 50)));
		
		for(int i = 0; i < stList.size(); i++) {// 등수 구해서 넣기
			stList.get(i).setRank(1);
			for(int j = 0; j < stList.size(); j++) {
				if(stList.get(i).getSum() < stList.get(j).getSum()) {
					stList.get(i).setRank(stList.get(i).getRank() + 1);
				}
			}
		}
		
		System.out.println("===== 정렬 전 Student =====");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		System.out.println("----------------------------------------------------");
		for(Student stu : stList) {
			System.out.println(stu.getNum() + "\t" + stu.getName() + "\t" + stu.getKor()
					+ "\t" + stu.getEng() + "\t" + stu.getMath() + "\t" + stu.getSum()
					+ "\t" + stu.getRank()
					);
		}
		System.out.println("----------------------------------------------------");
		
		Collections.sort(stList);
		System.out.println("===== 학번 오름차순 정렬 Student =====");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		System.out.println("----------------------------------------------------");
		for(Student stu : stList) {
			System.out.println(stu.getNum() + "\t" + stu.getName() + "\t" + stu.getKor()
					+ "\t" + stu.getEng() + "\t" + stu.getMath() + "\t" + stu.getSum()
					+ "\t" + stu.getRank()
					);
		}
		System.out.println("----------------------------------------------------");
		
		Collections.sort(stList, new numDesc());
		Collections.sort(stList, new sumDesc());
		System.out.println("===== 총점 내림차순 정렬 Student =====");
		System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t등수");
		System.out.println("----------------------------------------------------");
		for(Student stu : stList) {
			System.out.println(stu.getNum() + "\t" + stu.getName() + "\t" + stu.getKor()
					+ "\t" + stu.getEng() + "\t" + stu.getMath() + "\t" + stu.getSum()
					+ "\t" + stu.getRank()
					);
		}
		System.out.println("----------------------------------------------------");
	}

}
class numDesc implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		return -(Integer.compare(stu1.getNum(), stu2.getNum()));
	}
}

class sumDesc implements Comparator<Student>{
	
	@Override
	public int compare(Student stu1, Student stu2) {
		return -(Integer.compare(stu1.getSum(), stu2.getSum()));
	}
}

class Student implements Comparable<Student>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;

	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		sum = kor + eng + math;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
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
	public int compareTo(Student stu) {
		return Integer.compare(getNum(), stu.getNum());
	}
	
	
	
	
	
}