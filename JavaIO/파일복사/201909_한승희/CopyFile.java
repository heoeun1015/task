package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyFile {
	public static void main(String[] args) {
		
		File file1 = new File("d:/D_Other/Tulips.jpg");
		File file2 = new File("d:/D_Other/복사본_Tulips.jpg");
		
		try {
			FileInputStream fin = new FileInputStream(file1);
			FileOutputStream fout = new FileOutputStream(file2);
			
			int c;	//읽어온 데이터를 저장할 변수
			
			while((c=fin.read())!=-1)
			{
				fout.write(c);
			}
			
			fin.close();
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
