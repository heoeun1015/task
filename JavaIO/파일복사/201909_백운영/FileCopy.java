package homework;

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

			fos = new FileOutputStream(new File("d:/D_Other/복사본_Tulips.jpg"));

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
