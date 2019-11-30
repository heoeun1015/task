package aes128;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES128Test {
	
	private static final String key = "key1234567890123"; // 암복호화에 사용할 키 값(16, 24, 32 Byte)" 
	
	/**
	 * 암호화
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String message) throws Exception{
		
	    if(message == null){
	        return null;
	    }else{
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
	         
	        byte[] encrypted = cipher.doFinal(message.getBytes());
	         
	        return byteArrayToHex(encrypted);
	    }
	}
	
	
	
	/**
	 * 복호화
	 * @param encrypted
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encrypted) throws Exception{
	     
	    if(encrypted == null){
	        return null;
	    }else{
	        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
	         
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
	         
	        byte[] original = cipher.doFinal(hexToByteArray(encrypted));
	         
	        String originalStr = new String(original);
	         
	        return originalStr;
	    }
	}
	
	/**
	 * Byte배열을 16진수 문자열로 변환
	 * @param encrypted
	 * @return
	 */
	private static String byteArrayToHex(byte[] encrypted) {
	     
	    if(encrypted == null || encrypted.length ==0){
	        return null;
	    }
	     
	    StringBuffer sb = new StringBuffer(encrypted.length * 2);
	    String hexNumber;
	     
	    for(int x=0; x<encrypted.length; x++){
	        hexNumber = "0" + Integer.toHexString(0xff & encrypted[x]);
	        sb.append(hexNumber.substring(hexNumber.length() - 2));
	    }
	     
	    return sb.toString();
	}
	
	
	/**
	 * 16진수 문자열을 Byte배열로 변경
	 * @param hex
	 * @return
	 */
	private static byte[] hexToByteArray(String hex) {
	     
	    if(hex == null || hex.length() == 0){
	        return null;
	    }
	     
	    //16진수 문자열을 byte로 변환
	    byte[] byteArray = new byte[hex.length() /2 ];
	     
	    for(int i=0; i<byteArray.length; i++){
	        byteArray[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2*i+2), 16);
	    }
	    return byteArray;
	}
	
	
	public static void main(String[] args) throws Exception {
		String encStr = encrypt("안녕하세요");
		System.out.println("암호화된 문자열: " + encStr);
		
		String decStr = decrypt(encStr);
		System.out.println("복호화된 문자열: " + decStr);
	}

}
