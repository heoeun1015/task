package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는  
 * Student 클래스 만들기
 * 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리
 * 
 * 이 student 객체들은 List에 저장하여 관리
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력 
 * &
 * 총점의 내림차순으로 정렬
 * (총점이 같으면 학번의 내림차순으로 정렬)
 * (학번 정렬기준은 Student클래스 자체에서 제공)
 * (총점 정렬 기준은 외부 클래스에서 제공)
 * 
 */

public class T08_StudentTest {
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<>();
		stuList.add(new Student(2006006032, "박재욱", 80, 90, 20));
		stuList.add(new Student(2006006001, "나소두", 50, 50, 90));
		stuList.add(new Student(2006006022, "도루오", 30, 60, 60));
		stuList.add(new Student(2006006012, "미구로", 30, 70, 40));
		stuList.add(new Student(2006006072, "샤우도", 20, 80, 10));
		System.out.println("정렬 전");
		for(Student stu:stuList) {
			System.out.println(stu);
		}
		System.out.println("================================");
		System.out.println("학번으로 오름차순 정렬");
		Collections.sort(stuList);
		for(int i=0;i < stuList.size();i++) {
			Student student1 = stuList.get(i);
			student1.setRank(i+1);
			System.out.println(student1);
		}

		// 외부 정렬 기준을 통해 정렬하기
		Collections.sort(stuList, new SortSumDesc());

		System.out.println("================================");
		System.out.println("총점으로 내림차순 정렬");
		for(int i=0;i < stuList.size();i++) {
			Student student1 = stuList.get(i);
			
			student1.setRank(i+1);
			System.out.println(student1);
		}
	}
}
class SortSumDesc implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getSum() > stu2.getSum() | stu1.getSum() == stu2.getSum()) {
			return -1;
		}else {
			return 1;
		}
		
	}
	
}


class Student implements Comparable<Student>{
	private int id; // 학번
	private String name; // 이름
	int korean;
	int english;
	int math;
	int rank;
	int sum;

	public Student(int id, String name, int korean, int english, int math) {
		super();
		this.id = id;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		this.rank = 0;
		this.sum = korean+english+math;
	}
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	@Override
	public String toString() {
		return  rank + "Member[id=" + id + ",name=" + name + ", korean=" + korean + 
				", english=" + english + ",math=" + math + ", sum=" + getSum();
	}
	@Override
	public int compareTo(Student stu) {
		return Integer.compare(getId(), stu.getId());
	}
}
