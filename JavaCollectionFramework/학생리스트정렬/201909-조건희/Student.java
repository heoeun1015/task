
public class Student implements Comparable<Student>{
	int Stu_num;
	String name;
	int nation_grade;
	int eng_grade;
	int math_grade;
	int total_sum;
	int rank = 1;
	
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getTotal_sum() {
		return total_sum;
	}

	public void setTotal_sum(int total_sum) {
		this.total_sum = total_sum;
	}

		
	public Student(int stu_num, String name, int nation_grade, int eng_grade, int math_grade) {		
		this.Stu_num = stu_num;
		this.name = name;
		this.nation_grade = nation_grade;
		this.eng_grade = eng_grade;
		this.math_grade = math_grade;
		this.total_sum = nation_grade + eng_grade + math_grade;
	}
		
	public int getStu_num() {
		return Stu_num;
	}


	public void setStu_num(int stu_num) {
		Stu_num = stu_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNation_grade() {
		return nation_grade;
	}

	public void setNation_grade(int nation_grade) {
		this.nation_grade = nation_grade;
	}

	public int getEng_grade() {
		return eng_grade;
	}

	public void setEng_grade(int eng_grade) {
		this.eng_grade = eng_grade;
	}

	public int getMath_grade() {
		return math_grade;
	}

	public void setMath_grade(int math_grade) {
		this.math_grade = math_grade;
	}
	
//	@Override
	//	학번, 이름, 국어, 영어, 수학점수
	public String toString() {
	// TODO Auto-generated method stub
	return "Student[stnum=" + Stu_num + ", name=" + name
				  + "," + "nation_grade = " +nation_grade
				  + "," + "eng_grade" + eng_grade + "," + "math_grade"
				  + math_grade + "," + " totalsum" + total_sum   
				  + "rank" + rank + "]";
	}

	@Override
	public int compareTo(Student s) {
		if(this.Stu_num > s.getStu_num()) {
			return 1;
		} else if(this.Stu_num < s.getStu_num()) {
			return -1;
		}	
		return 0;
	}	

}



