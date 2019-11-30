import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.lang.model.element.Element;

/*
 	문제) 학번 , 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 
 		Student클래스를 만든다.
 		1. 생성자는 학번, 이름, 국어, 영어, 수학점수만 매개변수로 받아서 처리한다.
 		
 		이 Student객체들은 List에 저장하여 관리한다.
 		List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
 		총점의 역순으로 정렬하는 부분을 프로그램 하시오.
 		(총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
 		(학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
 		총점 정렬기준은 외부클래스에서 제공하도록 한다.
*/

public class T08_StudentTest {
	//외부정렬 comparator compare 
	public static void main(String[] args) {
		List<Student> stlist = new ArrayList<Student>();
		
		//학번, 이름, 국어, 영어, 수학
		stlist.add(new Student(20110000, "홍길동", 30, 40, 50));
		stlist.add(new Student(20110002, "변학도", 30, 40, 60));
		stlist.add(new Student(20110001, "성춘향", 30, 60, 50));
		stlist.add(new Student(20110005, "강감찬", 30, 40, 80));
		stlist.add(new Student(20110004, "일지매", 30, 50, 50));
		
		//석차 구하기
		for(int i = 0; i < stlist.size(); i++) {
			for(int j = 0; j < stlist.size(); j++) {
				if(stlist.get(i).getTotal_sum() < stlist.get(j).getTotal_sum()) {
					stlist.get(i).setRank(stlist.get(i).getRank()+1);
				}				
			}			
		}
		
		//석차 오름차순으로 구하기
		Collections.sort(stlist, new Comparator<Student>() {				
			@Override
			public int compare(Student o1, Student o2) {
				if(o1.getRank() > o2.getRank()) {
					return 1;
				} else if(o1.getRank() < o2.getRank()) { 
					return -1;
				} else 	{
					return 0;
				}
				
			}
			
		});
		System.out.println("등수의 오름차순 출력");
		for(int i = 0; i < stlist.size(); i++) {
			System.out.println(stlist.get(i));
		}
						
		Collections.sort(stlist);
		System.out.println("학번의 오름차순 출력");
		for(int i = 0; i < stlist.size(); i++) {
			System.out.println(stlist.get(i));
		}
		
		//총점의 역순
		Collections.sort(stlist, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				if(o1.getTotal_sum() > o2.getTotal_sum()) {
					return -1;
				} else if(o1.getTotal_sum() == o2.getTotal_sum()) {
					return (o1.getStu_num() > o2.getStu_num()) ? -1 : 1;
				} else {
					return 0;	
				}				
			}			
		});
		
		System.out.println("");
		System.out.println("총점의 역순 출력");		
		for(int i = 0; i < stlist.size(); i++) {
			System.out.println(stlist.get(i));
		}
	}	
	
}

