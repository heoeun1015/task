package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Filecopy {
	public static void main(String[] args) {
		
		FileInputStream fin = null; // 
		FileOutputStream fout = null;
		
		try {
			fin = new FileInputStream("d:/D_Other/Tulips.jpg");
			fout = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			
			byte[]buffer = new byte[50]; 
			int data = 0;
			
			while((data=fin.read(buffer)) != -1) {
				fout.write(buffer, 0, data); // 파일 복사
			}
			fin.close();
			fout.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
