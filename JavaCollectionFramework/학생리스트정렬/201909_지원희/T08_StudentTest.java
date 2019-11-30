package kr.or.ddit.basic;
import java.util.ArrayList;


/*
   문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
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
			
			
	/*		List<Student> scoreList = new ArrayList<>();
			
			scoreList.add(new Student("1", "김칸쵸", 50, 60, 70));
			scoreList.add(new Student("3", "김쟈키", 60, 70, 80));
			scoreList.add(new Student("2", "홀롤롤", 70, 50, 90));
			scoreList.add(new Student("4", "모나카", 80, 65, 90));
			scoreList.add(new Student("6", "김꿀빵", 80, 65, 50));
			scoreList.add(new Student("5", "백설기", 60, 55, 70));
			
	
		int i = 1;
		Collections.sort(scoreList, new totalScoreSort());
		System.out.println("\n 총점 기준으로 정렬(총점이 같으면 학번의 내림차순으로 정렬)===========");
			
			for(Student temp : scoreList) {
				temp.setRank(i);
				System.out.println(temp);
				i++;
			}
			
			System.out.println("=============================================");
			System.out.println("=============================================");
			
		Collection.sort(scoreList);
		System.out.println("학번기준으로 정렬=========================");
			for(Student temp : scoreList) {
				System.out.println(temp);
			}
			System.out.println("=======================================");
		
			
			
		}

	class totalScoreSort implements Comparator<Student>{
		//sort(파라미터2개, 내림차순) == 총점 정렬
		@Override
		public int compare(Student str1, Student str2) {
			if(str1.getTotalScore() > str2.getTotalScore()) {
				return -1;
			}else if(str1.getTotalScore() == str2.getTotalScore()) {
				//총점이 같으면 학번의 내림차순으로 정렬
				if(Integer.parseInt(str1.getNumber()) > Integer.parseInt(str2.getNumber())) {
					return -1;
				}else if(Integer.parseInt(str1.getNumber()) == Integer.parseInt(str2.getNumber())) {
					return 0;
				}else {
					return 1;
				}
			}
		}
	//sort(파라미터1개, 오름차순) == 학번정렬
		class Student implements Comparable<Student>{
			
			String number;
			String name;
			int lanScore;
			int engScore;
			int mathScore;
			int totalScore;
			int rank;
			
			public Student(String number, String name, int lanScore, int engScore, int mathScore) {
				super();
				this.number = number;
				this.name = name;
				this.lanScore = lanScore;
				this.engScore = engScore;
				this.mathScore = mathScore;
				this.mathScore = mathScore;
				this.totalScore = lanScore + engScore + mathScore;
			}
		
			public String getNumber() {
				return number;
			}
			public String getName() {
				return name;
			}
		
			public void setName(String name) {
				this.name = name;
			}
		public int getLanScore() {
			return lanScore;
		}
		public void setLanScore(int lanScore) {
			this.lanScore = lanScore;
		}
		public int getEngScore() {
			return engScore;
		}
		public int getEngScore() {
			return engScore;
		}
		public void setEngScore(int engScore) {
			this.engScore = engScore;
		}
		public int getMathScore() {
			return mathScore;
		}
		public void setMathScore(int mathScore) {
			this.mathScore = mathScore;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public int getTotalScore() {
			return totalScore;
		}
		public void setTotalScore(int totalScore) {
			this.totalScore = totalScore;
		}
		public int getRank() {
			return rank;
		}
		public void setRank(int rank) {
			this.rank = rank;
		}


		public String toString() {
			return "[ 학번= "+number +", 이름= "+name+", 국어점수= "+lanScore+", 영어점수= "+ engScore +", 수학점수= "+mathScore+", 총점 = "+totalScore+", 등수 = "+rank+" ]";
		}
		
		
		//이름으로 오름차순 정렬
		@Override
		public int compareTo(Student stud) {
			return getNumber().compareTo(stud.getNumber());
		}
		
		*/
		
		
		}
	
	}


















