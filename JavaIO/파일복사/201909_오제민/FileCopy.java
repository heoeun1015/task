package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {

	public static void main(String[] args) {
		try {
			FileInputStream fi = new FileInputStream("d:/D_Other/Tulips.jpg");
			FileOutputStream fo = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			BufferedOutputStream bo = new BufferedOutputStream(fo);
			
			int c;
			while((c = fi.read()) != -1) {
				bo.write(c);
			}
			System.out.println("작업 끝...");
			
			fi.close();
			bo.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
