package kr.or.ddit.basic;

/*
'd:/D_Other/'에 있는 'Tulips.jpg'파일을
'복사본_Tulips.jpg'로 복사하는 프로그램을 작성하시오.
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T05h_FileCopy {
	public static void main(String[] args) {
		//FileInputStream객체를 이용한 파일 내용 읽기

		try {
			FileInputStream fin = new FileInputStream("d:/D_Other/Tulips.jpg");
			FileOutputStream fout = new FileOutputStream("d:/D_Other/copy_Tulips.jpg");
			
			int c; //읽어온 데이터를 저장할 변수
			
			//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미이다.
			while((c=fin.read()) != -1) {
				//읽어온 자료 출력하기
				fout.write(c);
			}
			
			fin.close(); // 작업 완료 후 스트림 닫기
			fout.close(); // 작업 완료 후 스트림 닫기
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}