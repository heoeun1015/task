package studymyself;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Tulips {

	public static void main(String[] args) throws IOException  {

		FileInputStream fi = new FileInputStream("D:/D_Other/Tulips.jpg");
		FileOutputStream fo = new FileOutputStream("d:/D_Other/copyTulips.jpg");

		int a;
		
		while(true) {
			a = fi.read();
			if(a == -1) break;
			fo.write(a);
		}
		
		fo.close();
		fi.close();
	}
}