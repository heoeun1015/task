package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Tulips {
	public static void main(String[] args) {
		
		//파일이 없으면 새로 만들기
		File f = new File("d:/D_Other/복사본_Tulips.jpg");
		
		if(f.exists()) {
			System.out.println(f.getAbsolutePath() + "은 존재합니다.");
		}else {
			System.out.println(f.getAbsolutePath() + "은 없는 파일입니다.");
			try {
				if(f.createNewFile()) {
					System.out.println(f.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//복사하기
		FileInputStream fin = null;
		FileOutputStream fout = null;
		try {
			fin = new FileInputStream("d:/D_Other/Tulips.jpg");
			fout = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			
			int c;
			
			while((c = fin.read()) != -1) {
				fout.write(c);
			}
			
			fin.close();
			fout.close();
			
		}catch(FileNotFoundException e) {
			System.out.println("지정한 파일이 없습니다.");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
