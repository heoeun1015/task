package kr.or.ddit.basic;
/*
 'd:/D_Other/'에 있는 'Tulips.jpg'파일을

'복사본_Tulips.jpg'로 복사하는 프로그램을

작성하시오. 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T06_H파일복사{
	public static void main(String[] args) {
		
		try {
		FileInputStream fi = new FileInputStream("D:/D_Other/Tulips.jpg");
		FileOutputStream fo = new FileOutputStream("d:/D_Other/copyTulips.jpg");
		
		
		int c;	//읽어온 데이터를 저장할 변수
		
		//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미이다.
		while((c=fi.read())!=-1) {
			//읽어온 자료 출력하기
			fo.write(c);
		}
		
		//작업 완료 후 스트림 닫기
		fi.close(); 
		fo.close();
		
		System.out.println();
		System.out.println("복사완료");
		
		}catch (IOException e) {
			System.out.println("알 수 없는 오류입니다.");
			e.printStackTrace();
		}
	}
}