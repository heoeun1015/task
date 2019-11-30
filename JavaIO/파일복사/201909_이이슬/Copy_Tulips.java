package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy_Tulips {

	public static void main(String[] args) {
		try {
		FileInputStream tulips = new FileInputStream("D:/D_Other/Tulips.jpg");
		FileOutputStream copy_tulips = new FileOutputStream("D:/D_Other/복사본_Tulips.jpg");
		//읽어온 데이터를 저장할 변수
		int c;
		
		while((c=tulips.read()) != -1) {
			copy_tulips.write(c);
		}
		//작업이 끝난 후 스트림 닫아주기		
		tulips.close();
		copy_tulips.close();
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
