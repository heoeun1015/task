package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T14_FileCopy {
	public static void main(String[] args) {
		//파일 경로
		File f1 = new File("d:/D_Other/Tulips.jpg");
		// 복사할 파일 경로
		File f2 = new File("d:/D_Other/복사본_Tulips.jpg");
		
		try {
			// 파일 가져오기
			FileInputStream fin = new FileInputStream(f1);
			// 파일 복사 
			FileOutputStream fos = new FileOutputStream(f2);
			
			int c;
			while((c=fin.read()) != -1) {
				fos.write(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
	
