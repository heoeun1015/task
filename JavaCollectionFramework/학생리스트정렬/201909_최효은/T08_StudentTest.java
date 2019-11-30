package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student 클래스를 만든다.
		 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
		 
		  이 Student 객체들은 List에 저장하여 관리한다.
		 List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과,
		  총점의 역순으로 정렬하는 부분을 프로그래밍 하시오.
		 (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
		 (학번 정렬 기준은 Student 클래스 자체에서 제공하도록 하고, 총점 정렬 기준은 외부클래스에서 제공하도록 한다.)*/


public class T08_StudentTest {
	public static void main(String[] args) {
		
		List<Student> list = new ArrayList<Student>();
		Student st = new Student(0, null, 0, 0, 0);
		
		System.out.println("▷ 학생 리스트 정렬");
		System.out.println("---------------------------------------------------------------");
		list.add(new Student(201900001, "홍길동", 98, 52, 54));
		list.add(new Student(201900005, "임꺽정", 56, 57, 78));
		list.add(new Student(201900004, "강강찬", 78, 15, 97));
		list.add(new Student(201900009, "이순신", 21, 81, 48));
		list.add(new Student(201900002, "이순신", 21, 41, 38));
		list.add(new Student(201900008, "콩두유", 21, 81, 48));
		list.add(new Student(201900006, "장보고", 94, 87, 66));
		
		Collections.sort(list);
		
		int sum = 0;
		int avg = 0;
		
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i).getTotal();
		}
		
		for(int i = 0; i < list.size(); i++) {
			int rank = 1;
			for(int j = 0; j < list.size(); j++) {
				if(list.get(i).getTotal() < list.get(j).getTotal()) {
					rank++;
					list.get(i).setRank(rank);
				}else {
					list.get(i).setRank(rank);
				}
			}
		}
		
		Collections.sort(list, new SortDesc());
		
		for(Student a : list) {
			System.out.println(a);
		}
		
		System.out.println("---------------------------------------------------------------");

		
	}
}

class SortDesc implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getTotal() > stu2.getTotal()) {		
			return -1;
		}else if(stu1.getTotal() == stu2.getTotal()) {
			return stu1.compareTo(stu2) * -1;
		}else {
			return 1;
		}
	}
	
}



class Student implements Comparable<Student>{
	
	private int classNum;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;
	
	
	public Student(int classNum, String name, int kor, int eng, int math) {
		super();
		this.classNum = classNum;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;
	}
	
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
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

	@Override
	public String toString() {
		return "[학번=" + classNum + ", 이름=" + name + ", 국어=" + kor + ", 영어=" + eng + ", 수학=" + math
				+ ", 총점=" + total + ", 등수=" + rank + "]";
	}

	@Override
	public int compareTo(Student str) {
		return new Integer(getClassNum()).compareTo(str.getClassNum());
	}
	
	
}
