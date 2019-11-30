package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T08_StudentTest {

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
 * 		Student클래스를 만든다.
 * 		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
 * 		
 * 		이 Student객체들은 List에 저장하여 관리한다.
 * 		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 * 		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 * 		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 *  	(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
 *  	 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */		
	public static void main(String[] args) {
		

		List<Student> memList = new ArrayList<>();
		
		memList.add(new Student(2, "김김김", 91, 88, 30));
		memList.add(new Student(3, "이이이", 92, 52, 80));
		memList.add(new Student(1, "박박박", 93, 36, 5));
		memList.add(new Student(4, "최최최", 91, 30, 88));
		memList.add(new Student(5, "오오오", 95, 10, 44));
		
		//등수
		for (int i =0; i<memList.size(); i++) {
			memList.get(i).setRank(1);
			for (int j=0; j<memList.size(); j++) {
				if(memList.get(i).getSum() < memList.get(j).getSum()) {
					memList.get(i).setRank(memList.get(i).getRank() +1) ;
				}
			}
		}
		
		System.out.println("정렬 전");
		for(Student mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
		Collections.sort(memList);	//정렬하기
		
		System.out.println("정렬 후");
		for(Student mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
		
		Collections.sort(memList, new SortSumDesc());
		
		System.out.println("총점 내림차순 정렬 후");
		for(Student mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-------------------------------");
	}
	

}
class SortSumDesc implements Comparator<Student>{

	@Override
	public int compare(Student mem1, Student mem2) {
		if(mem1.getSum() > mem2.getSum()) {
			return -1;
		}else if(mem1.getSum() == mem2.getSum()) {
			return Integer.compare(mem1.getNum(), mem2.getNum())*-1;	//등수가 같을 때 학번내림차순정렬
		}else {
			return 1;
		}
//		return new Integer(mem1.getSum()).compareTo(mem2.getSum())*-1;
	}
	
}

class Student implements Comparable<Student>{
	private int num;			//학번
	private String name;		//이름
	private int kor;			//국어점수
	private int eng;			//영어점수
	private int math;			//수학점수
	private int sum;			//총점
	private int rank;			//등수
	
	
	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.sum = kor+eng+math;
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
	public String toString() {
		return "Member[학번=" + num + ", 이름=" + name + ", 국어=" + kor + ", 영어=" + eng + ", 수학=" + math + ", 총점=" + sum + ", 등수=" + rank + "]";
	}
	
	@Override
	public int compareTo(Student mem) {
		return Integer.compare(getNum(), mem.getNum());
	}
	
}