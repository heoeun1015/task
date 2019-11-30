package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
 * Student클래스를 만든다.
 * 생성자는 학번,이름,국어,영어,수학 점수만 매개변수로 받아서 처리한다.
 * 이 Student객체들은 List에 저장하여 관리한다.
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 * 총점의 역순으로 정렬하는 부분을 프로그램하시오.
 * (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 * (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 
 * 총점 정렬기준은 외부클래스에서 제공하도록한다.)
 */
public class T08_StudentTest {

	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<>();
		stuList.add(new Student("1111","김혜정",70,80,90));
		stuList.add(new Student("5555","조건희",9,80,70));
		stuList.add(new Student("3333","안정현",60,90,90));
		stuList.add(new Student("2222","신유진",90,8,90));
		stuList.add(new Student("4444","박신규",74,88,90));
		
		System.out.println("정렬 전");
		for(int i=0;i<stuList.size();i++) {
			for(int j=0;j<stuList.size();j++) {
				if(stuList.get(i).sumscore<stuList.get(j).sumscore) {
					stuList.get(i).rank++;
				}
			}
			
		}
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("-----------------------------------");
		Collections.sort(stuList);
		System.out.println("학번의 오름차순 정렬 후...");
		
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
		Collections.sort(stuList, new SumScore());
		System.out.println("총점의 역순으로 정렬 후...");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		
	}
static class SumScore implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getSumscore()>stu2.getSumscore()) {
			return -1; //내림차순일때는 앞에가 더 크면 음수
		}else if(stu1.getSumscore() == stu2.getSumscore()) {
			return stu1.getStunum().compareTo(stu2.getStunum())* -1;
		}else {
			return 1;
		}
	}
}

static class Student implements Comparable<Student>{
	private String stunum;
	private String name;
	private int kscore;
	private int escore;
	private int mscore;
	private int sumscore;
	private int rank;
	
	public int getSumscore() {
		return sumscore;
	}
	public void setSumscore(int sumscore) {
		this.sumscore = sumscore;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getStunum() {
		return stunum;
	}
	public void setStunum(String stunum) {
		this.stunum = stunum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKscore() {
		return kscore;
	}
	public void setKscore(int kscore) {
		this.kscore = kscore;
	}
	public int getEscore() {
		return escore;
	}
	public void setEscore(int escore) {
		this.escore = escore;
	}
	public int getMscore() {
		return mscore;
	}
	public void setMscore(int mscore) {
		this.mscore = mscore;
	}
	

	
	public Student(String stunum, String name, int kscore, int escore, int mscore) {
		super();
		this.stunum = stunum;
		this.name = name;
		this.kscore = kscore;
		this.escore = escore;
		this.mscore = mscore;
		this.sumscore = (kscore+escore+mscore);
		this.rank = 1;
	}
	public String toString() {
		return "Student[stunum="+stunum+", name="+name+", 국어점수 ="+kscore+", 영어점수="+escore+", 수학점수="+mscore+",총점 ="+ sumscore+",등수 ="+rank;
	}
	
	@Override
	public int compareTo(Student stu) {
		return getStunum().compareTo(stu.getStunum());
	}
	
	
}
}
