package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy {
	public static void main(String[] args) throws IOException {
		File file = null;
		File file2 = null;
		FileInputStream fin = null;
		FileOutputStream fos = null;

		file = new File("d:/D_Other/Tulips.jpg");
		file2 = new File("d:/D_Other/", "복사본_" + file.getName());
		fin = new FileInputStream(file);
		fos = new FileOutputStream(file2);

		BufferedOutputStream bout = new BufferedOutputStream(fos, 60);
		int c;
		while ((c = fin.read()) != -1) {
			bout.write(c);
		}
		System.out.println("복사 완료....");
		
		fin.close();
		fos.close();
	}
}
