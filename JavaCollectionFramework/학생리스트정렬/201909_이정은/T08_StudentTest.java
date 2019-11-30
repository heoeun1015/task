package kt.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
	문제 ) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 
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
		
		studentList.add(new Student("1", "이", 50, 60, 70));
		studentList.add(new Student("2", "최", 70, 40, 80));
		studentList.add(new Student("3", "김",40, 50, 40));
		studentList.add(new Student("4", "황",60, 80, 50));
		
		Collections.sort(studentList); 
		
		System.out.println("학번의 오름차순 정렬 : ");
		for(Student stu : studentList) {
			System.out.println(stu);
		}
		System.out.println("======================================");
		
		int sum = 0;
		int rank = 0;
		
		for(int i = 0; i < studentList.size(); i++) {
			rank = 1;
			for (int j = 0; j < studentList.size(); j++) {
			if(studentList.get(i).getSum() < studentList.get(j).getSum()) {
				rank++;
				studentList.get(i).setRank(rank); //해당 첫번째 i를 가져온후 set rank를 가져온다. rank를 정해줌
			}else {
				studentList.get(i).setRank(rank); // 작지않은경우
			}
					
			}
		}
		
		Collections.sort(studentList, new SortSumDesc());
		
		System.out.println("총점의 내림차순으로 정렬 : ");
		for(Student stu : studentList) {
			System.out.println(stu);
		}
		System.out.println("======================================");
				
	}
}

class SortSumDesc implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getSum() > stu2.getSum()) {
			return -1;
		}else if(stu1.getSum() == stu2.getSum()) {
			return stu1.compareTo(stu2)*-1 ;	// 총점이 같을때 내림차순해주는 부분
		}else {
			return Integer.compare(stu1.getSum(),stu2.getSum())*-1;
		}
	}
	
}

class Student implements Comparable<Student>{
	
	private String num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;
	
	

	public Student(String num, String name, int kor, int eng, int math) { //생성자로 받아올 매개변수
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		sum = kor+eng+math;
		this.rank = 0;
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
		return "Student [학번=" + num + ", 이름=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
				+ sum + ", rank=" + rank + "]";
	}


	@Override
	public int compareTo(Student stu) {
		
		return getNum().compareTo(stu.getNum());
	}
	
}

		


		
	

		

