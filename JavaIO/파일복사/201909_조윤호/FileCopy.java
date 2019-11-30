/*package kr.or.ddit.basic;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

	public static void main(String[] args) {

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			File file = new File("d:/D_Other/Tulips.jpg");

			fis = new FileInputStream(file);
			
			fos = new FileOutputStream(new File("d:/D_Other/TulipsCopy.jpg"));
			
			int c;
			
			while ((c = fis.read()) != -1) {
				fos.write(c);
			}
			System.out.println("파일이 복사 되었습니다.");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
*/
package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args)  {
		FileInputStream tulip; 
		FileOutputStream copy;
		
		try {
			tulip = new FileInputStream("d:/D_Other/Tulips.jpg"); // 읽어올 파일 경로
			copy =  new FileOutputStream("d:/D_Other/TulipsCopy.jpg"); // 복사할 파일 경로
			
			int c; // 읽어온 데이터를 저장할 변수

			//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미이다.
			while((c=tulip.read()) != -1) {
				copy.write(c);
			}
			System.out.println("파일이 복사 되었습니다.");
			
		}catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
