package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 
 * 		Student클래스를 만든다.
 * 		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 
 * 		이 Student객체들은 List에 저장하여 관리한다.
 * 		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 * 		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * 		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 * 		(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
 * 		총점 정렬기준은 외부클래스에서 제공하도록 한다.)
*/

public class T08_StudentTest {
	public static void main(String[] args) {
			
		List<Student> list = new ArrayList<>();
		
		list.add(new Student("2", "a", 70, 80, 90));
		list.add(new Student("1", "b", 60, 80, 80));
		list.add(new Student("4", "c", 80, 80, 90));
		list.add(new Student("3", "d", 80, 70, 40));
		//list.add(new Student("5", "e", 80, 70, 40));
		
		for(int i = 0; i < list.size(); i++){
			for(int j = 0; j < list.size(); j++){
				if((list.get(i).get총점() - 1)
						< (list.get(j).get총점() - 1)){
					list.get(i).등수++; //석차
				}
			}
		}
		
		System.out.println("정렬 전========================");
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("");
		System.out.println("학번정렬 후======================");
		Collections.sort(list);
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("");
		System.out.println("총점정렬 후======================");
		Collections.sort(list, new SortNumDesc1());
		for(Student s : list) {
			System.out.println(s);
		}
		
		System.out.println("");
		
	}
	
}

class SortNumDesc1 implements Comparator<Student>{
	
	public int compare(Student list1, Student list2) {
		if(list1.get총점() > list2.get총점()) {
				return -1;
			}else if(list1.get총점() == list2.get총점()) {
				return list1.get학번().compareTo(list2.get학번()) * -1;
			}else {
				return 1;
			}
		
		//Wrapper클래스에는 제공하는 메서드를 이용하는 방법
		//return Integer.compare(mem1.getNum(), mem2.getNum()) * (-1);
		
		//Wrapper클래스에서 제공하는 메서드를 이용하는 방법2
		/*return new Integer(list1.get총점())
				.compareTo(list2.get총점())*-1;*/
	}
}


class Student implements Comparable<Student>{

	private String 학번;
	private String 이름;
	private int 국어점수;
	private int 영어점수;
	private int 수학점수;
	private int 총점;
	public int 등수;
	
	
	public Student(String 학번, String 이름, int 국어점수, int 영어점수, int 수학점수) {
		super();
		this.학번 = 학번;
		this.이름 = 이름;
		this.국어점수 = 국어점수;
		this.영어점수 = 영어점수;
		this.수학점수 = 수학점수;
		this.총점 = 국어점수 + 수학점수 + 영어점수;
		this.등수 = 1;
	}
	
	public int get등수() {
		return 등수;
	}
	
	public int get총점() {
		return 총점;
	}

	public String get학번() {
		return 학번;
	}

	public void set학번(String 학번) {
		this.학번 = 학번;
	}

	public String get이름() {
		return 이름;
	}

	public void set이름(String 이름) {
		this.이름 = 이름;
	}

	public int get국어점수() {
		return 국어점수;
	}

	public void set국어점수(int 국어점수) {
		this.국어점수 = 국어점수;
	}

	public int get영어점수() {
		return 영어점수;
	}

	public void set영어점수(int 영어점수) {
		this.영어점수 = 영어점수;
	}

	public int get수학점수() {
		return 수학점수;
	}

	public void set수학점수(int 수학점수) {
		this.수학점수 = 수학점수;
	}

	@Override
	public int compareTo(Student list) {
		return get학번().compareTo(list.get학번());
	}
	
	@Override
	public String toString() {
		return "\r" + "등수 = " + 등수 + ", 학번 = " + 학번 + ", name = " + 이름 + ", 국어점수 = " + 국어점수 + ", 수학점수 = " + 수학점수 + ", 영어점수 = " + 영어점수 + ", 총점 = " + 총점;
	}

	
}


