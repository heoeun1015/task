package kr.or.ddit.basic;
/*
	문제 ) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
		Student클래스를 만든다.
		생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.

		이 Student객체들은 List에 저장하여 관리한다.
		List에 저장된 데이터들은 학번의 오름차순으로 정렬하여 출력하는 부분과
		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
		(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 
		총점 정렬기준은 외부클래스에서 제공하도록 한다.)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T08_StudentTest {
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<>();

		stuList.add(new Student(313, "최입분", 54, 71, 45));
		stuList.add(new Student(120, "박성만", 64, 53, 37));
		stuList.add(new Student(824, "조윤정", 34, 54, 89));
		stuList.add(new Student(319, "박신규", 62, 84, 93));
		stuList.add(new Student(120, "이중훈", 35, 73, 69));
		
		for (int i =0; i<stuList.size(); i++) {
			//stuList.get(i).setRank(1);
			for (int j=0; j<stuList.size(); j++) {
				if(stuList.get(i).getSum() < stuList.get(j).getSum()) {
					//stuList.get(i).setRank(stuList.get(i).getRank() +1) ;
					stuList.get(i).rank++;
				}
			}
		}
		
		System.out.println("정렬 전 : " );
		
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		Collections.sort(stuList);
		
		System.out.println("정렬 후 : " );
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		Collections.sort(stuList, new SortNumDesc1());
		System.out.println("번호의 내림차순으로 정렬 후...");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
	}
}

class SortNumDesc1 implements Comparator<Student>{
	
	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getSum() > stu2.getSum()) {
			return -1;   // 1로 바꾸고 else를 -1로 바꾸면 오름차순 정렬이 된다.
		} else if(stu1.getSum() == stu2.getSum()) {
			return Integer.compare(stu1.getSnum(), stu2.getSnum()) * -1;
		} else {
			return 1;
		}
		
		//Wrapper클래스에서 제공하는 메서드를 이용하는 방법
		//return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		
		//Wrapper클래스에서 제공하는 메서드를 이용하는 방법2
		//return new Integer(stu1.getSnum()).compareTo(stu2.getSnum()) * -1;
	}
}

class Student implements Comparable<Student> {
	private int snum;  		// 번호
	private String name;	// 이름
	private int kook;		// 국어
	private int eng; 		// 영어
	private int math;		// 수학
	private int sum;		// 총점
	int rank;		// 순위
	
	public Student(int snum, String name, int kook, int eng, int math) {
		super();
		this.snum = snum;
		this.name = name;
		this.kook = kook;
		this.eng = eng;
		this.math = math;
		this.sum = kook + eng + math;
		this.rank = 1;
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
	
	public int getSnum() {
		return snum;
	}

	public void setSnum(int snum) {
		this.snum = snum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKook() {
		return kook;
	}

	public void setKook(int kook) {
		this.kook = kook;
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
	public int compareTo(Student stu) {
		return Integer.compare(getSum(), stu.getSum());
	}
	
	@Override
	public String toString() {
		return "Student[학번 = " + snum + ", 이름 = " + name + ", 국어 = " + kook +", 수학 = " + math +" 영어 = " + eng +" 총점 = "+ sum + " 등수 = "+rank+"]";
	}
}