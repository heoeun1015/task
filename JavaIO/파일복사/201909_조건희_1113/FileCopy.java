package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T_Filetest2 {
	public static void main(String[] args) {       
//		FileInputStream input = null;
//		FileOutputStream output = null;
		try{
			// 복사할 대상 파일을 지정해준다.
			File file = new File("C:/D_Other/Tulips.jpg");
			File file2 = new File("C:/D_Other/복사본_Tulips.jpg");
			
			if(file2.exists()) {
				System.out.println("복사할 파일이 이미 존재합니다.");
			} else {
				System.out.println("없는 파일입니다.");
				
				// FileInputStream 는 File object를 생성자 인수로 받을 수 있다.         
				FileInputStream input = new FileInputStream(file);
				// 복사된 파일의 위치를 지정해준다.
				FileOutputStream output = new FileOutputStream(file2);

				//자료를 읽을때 사용할 배열
				byte [] buffer = new byte[30]; 
											
//				while((input.read(buffer)) != -1) {
//					output.write(buffer);
//				}
				
				while(input.available() > 0) {
					input.read(buffer); //buffer배열 크기만큼 읽어와 buffer에 저장
					output.write(buffer); // buffer배열 내용 출력
				}
				
				System.out.println("사진이 복사되었습니다.");
				try{
					// 생성된 InputStream Object를 닫아준다.
					input.close();
					// 생성된 OutputStream Object를 닫아준다.
					output.close();
				} catch(IOException io) {}
			}
		} catch (IOException e) {
			System.out.println(e);
		} 
	}
}

