package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy_Tulips {
	public static void main(String[] args) throws IOException {
		
		FileInputStream tulipFIS = new FileInputStream("d:/D_Other/Tulips.jpg");
		FileOutputStream copyTulipFOS = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
		//input에 값을 읽어올 원본의 경로/이름을 넣고, output에는 복사본을 저장할 이름/경로 정해주기
		
		int c;
		while((c = tulipFIS.read()) != -1) {
			copyTulipFOS.write(c);
			//원본input에서 정보를 하나씩 가져와서 복사본output에 집어 넣기
		}
			
		tulipFIS.close();
		copyTulipFOS.close();
		
	}
}
