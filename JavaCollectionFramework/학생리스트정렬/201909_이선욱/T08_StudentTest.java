package go_over;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
			문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
				 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
				 
				 이 Student객체들은 List에 저장하여 관리한다.
				 List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
				 총점의 역순으로 정렬하는 부분을 프로그래밍하라.
				 (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
				 (학번의 정렬기준은 Student클래스 자체에서 제공하도록 하고,
				 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
		*/
public class T08_StudentTest {

	public static void main(String[] args) {
		List<Student> stList = new ArrayList<>();
		
		stList.add(new Student(201900123, "펭수", 84, 98, 45));
		stList.add(new Student(201900521, "뽀로로", 77, 87, 32));
		stList.add(new Student(201900234, "우투리", 100, 79, 56));
		stList.add(new Student(201900111, "둘리", 90, 90, 55));
		stList.add(new Student(201900365, "어피치", 79, 98, 74));
		stList.add(new Student(201900623, "라이언", 85, 65, 30));
		stList.add(new Student(201900325, "뚜비", 56, 75, 36));
		
		//총점 기준으로 등수 설정하기
		for(int i = 0; i < stList.size(); i++) {
			int rank = 1;
			stList.get(i).setRank(rank);
			for(int j = 0; j < stList.size(); j++) {
				if(stList.get(i).getTotal() < stList.get(j).getTotal()) {
					stList.get(i).setRank(rank++);
				}else {
					stList.get(i).setRank(rank);
				}
			}
		}
		
		System.out.println("----------학번의 오름차순----------");
		Collections.sort(stList);
		for(Student student : stList) {
			System.out.println(student);
		}
		
		System.out.println("----------총점의 내림차순----------");
		System.out.println("----총점이 같을 경우 학번의 내림차순----");
		Collections.sort(stList, new SortTotalDesc());
		for(Student student : stList) {
			System.out.println(student);
		}
	}
}

class SortTotalDesc implements Comparator<Student> {
	@Override
	public int compare(Student std1, Student std2) {
		if(std1.getTotal() > std2.getTotal()) {
			return -1;
		}else if(std1.getTotal() == std2.getTotal()) {
			if(std1.getNum() > std2.getNum()) {
				return -1;
			}else if(std1.getNum() == std2.getNum()) {
				return 0;
			}else {
				return 1;
			}
		}else {
			return 1;
		}
	}
}

class Student implements Comparable<Student> {
	private int num;
	private String name;
	private int krScore;
	private int enScore;
	private int mtScore;
	private int total;
	private int rank;
	
	public Student(int num, String name, int krScore, int enScore, int mtScore) {
		super();
		this.num = num;
		this.name = name;
		this.krScore = krScore;
		this.enScore = enScore;
		this.mtScore = mtScore;
		this.total = krScore + enScore + mtScore;
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

	public int getKrScore() {
		return krScore;
	}

	public void setKrScore(int krScore) {
		this.krScore = krScore;
	}

	public int getEnScore() {
		return enScore;
	}

	public void setEnScore(int enScore) {
		this.enScore = enScore;
	}

	public int getMtScore() {
		return mtScore;
	}

	public void setMtScore(int mtScore) {
		this.mtScore = mtScore;
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
	
	public String toString () {
		return "[ 학번 : " + num 
				+ ", 이름 : " + name
				+ ", 국어점수 : " + krScore
				+ ", 영어점수 : " + enScore
				+ ", 수학점수 : " + mtScore
				+ ", 총점 : " + total 
				+ ", 등수 : " + rank + "]";
	}
	
	//학번의 오름차순
	@Override
	public int compareTo(Student std) {
		return new Integer(getNum()).compareTo(std.getNum());
	}
}