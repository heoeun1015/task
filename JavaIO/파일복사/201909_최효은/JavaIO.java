package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class JavaIO {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			
		fis = new FileInputStream("D:/D_Other/Tulips.jpg");
		fos = new FileOutputStream("D:/D_Other/복사본_Tulips.jpg");
		   
		byte[] buffer = new byte[1024];
		int c = 0;
		  
		while((c = fis.read(buffer)) != -1) {
			fos.write(buffer, 0, c);    // 파일 복사
		}
		
		fis.close();
		fos.close();
		
		} catch(Exception e) {
		e.printStackTrace();
		}

	}
}