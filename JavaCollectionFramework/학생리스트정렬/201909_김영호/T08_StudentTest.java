package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다
 * 
 * 이 Student객체들은 List에 저장하여 관리한다
 * List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램하시오.
 * (총점이 같으면 학번의 내림차순으로 정렬되도록 한다)
 * (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다)
*/

public class T08_StudentTest {

	public static void main(String[] args) {
		
		List<Students> list = new ArrayList<>();
		list.add(new Students(4, "홍길동", 80, 90, 99));
		list.add(new Students(3, "김명성", 86, 93, 77));
		list.add(new Students(1, "백운영", 61, 95, 78));
		list.add(new Students(5, "조윤호", 48, 96, 79));
		list.add(new Students(2, "최도혁", 86, 96, 74));
		list.add(new Students(6, "오동규", 86, 98, 72));
		
		int count;
		for(int i=0; i<list.size(); i++) {
			count=1;
			for(int j=0; j<list.size(); j++) {
				if(list.get(i).getSum() < list.get(j).getSum()) {
					count++;
				}
			}
			list.get(i).setRank(count);
		}
		
		Collections.sort(list);
		
		System.out.println("학번으로 오름차순 정렬");
		System.out.println("등수\t학번\t이름\t국어점수\t영어점수\t수학점수\t총점");
		System.out.println("-----------------------------------------------------");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getRank()+"\t"+list.get(i).getIdNum()+"\t"+list.get(i).getName()+"\t"+list.get(i).getKorScore()+"\t"+list.get(i).getEngScore()+"\t"+list.get(i).getMathScore()+"\t"+list.get(i).getSum());
		}
		System.out.println("=====================================================");;
		
		
		Collections.sort(list, new SortSumDesc());
		
		System.out.println("총점으로 내림차순 정렬/학번으로 내림차순정렬");
		System.out.println("등수\t학번\t이름\t국어점수\t영어점수\t수학점수\t총점");
		System.out.println("-----------------------------------------------------");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i).getRank()+"\t"+list.get(i).getIdNum()+"\t"+list.get(i).getName()+"\t"+list.get(i).getKorScore()+"\t"+list.get(i).getEngScore()+"\t"+list.get(i).getMathScore()+"\t"+list.get(i).getSum());
		}
		System.out.println("=====================================================");;
		
		
		
		
	}
	
}

class SortSumDesc implements Comparator<Students>{

	@Override
	public int compare(Students std1, Students std2) {
		if(std1.getSum() > std2.getSum()) {
			return -1;
		}else if(std1.getSum() == std2.getSum()) {
			if(std1.getIdNum() > std2.getIdNum()) {
				return -1;
			}else if(std1.getIdNum() == std2.getIdNum()) {
				return 0;
			}else {
				return 1;
			}
		}else {
			return 1;
		}
	}
}

	


class Students implements Comparable<Students>{
	private int idNum;
	private String name;
	private int korScore;
	private int engScore;
	private int mathScore;
	private int sum;
	private int rank;
	
	public Students(int idNum, String name, int korScore, int engScore, int mathScore){
		super();
		this.idNum = idNum;
		this.name = name;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		this.sum = korScore + engScore + mathScore;
	}

	public int getIdNum() {return idNum;}
	public void setIdNum(int idNum) {this.idNum = idNum;}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public int getKorScore() {return korScore;}
	public void setKorScore(int korScore) {this.korScore = korScore;}

	public int getEngScore() {return engScore;}
	public void setEngScore(int engScore) {this.engScore = engScore;}

	public int getMathScore() {return mathScore;}
	public void setMathScore(int mathScore) {this.mathScore = mathScore;}

	public int getSum() {return sum;}
	public void setSum(int sum) {this.sum = sum;}

	public int getRank() {return rank;}
	public void setRank(int rank) {this.rank = rank;}
	
	
	@Override
	public int compareTo(Students std) {
		return (getIdNum()+"").compareTo(std.getIdNum()+"");
	}
	
	public String toString() {
		return "studentsList[학번=" + idNum
				+ ", 이름=" + name 
				+ ", 국어점수=" + korScore
				+ ", 영어점수=" + engScore
				+ ", 수학점수=" + mathScore
				+ ", 총점=" + sum
				+ ", 등수=" + rank + "]";
	}
	
}
