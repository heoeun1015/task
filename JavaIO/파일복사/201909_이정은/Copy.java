package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
'd:/D_Other/'에 있는 'Tulips.jpg'파일을

'복사본_Tulips.jpg'로 복사하는 프로그램을

작성하시오.
*/
public class Copy {

	public static void main(String[] args) {	
		
		try {
			int c;
			FileOutputStream fout = new FileOutputStream("d:/D_Other/Tulips_복사본.jpg");
			FileInputStream fin = new FileInputStream("d:/D_Other/Tulips.jpg");
			while((c=fin.read()) != -1){
				fout.write(c);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
