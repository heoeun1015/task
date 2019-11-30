package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class hw {

	public static void main(String[] args) throws IOException {
		
		/*String ori = "D:/D_Other/.65.jpg";
		String copy = "D:/D_Other/copy_.65.jpg";*/
		
		/*File orifile = new File(ori);
		File copyfile = new File(copy);*/
		
		try {
			FileInputStream fis= new FileInputStream("D:/D_Other/.65.jpg");
			FileOutputStream fos= new FileOutputStream("D:/D_Other/copy_.65.jpg");
			
			int c=0;
			while((c=fis.read())!=-1){
				fos.write(c);
			}
			fis.close();
			fos.close();
			System.out.println("복사되었습니다.");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}

}
