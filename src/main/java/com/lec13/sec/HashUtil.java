package com.lec13.sec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

	public static void main(String[] args) {
		String hashStr = getHashVal("111", "SHA-256");  
		System.out.println(hashStr);
		//f6e0a1e2ac41945a9aa7ff8a8aaa0cebc12a3bcc981a929ad5cf810a090e11ae
		
	}
	
	public static String getHashVal(String palntext, String algorithm){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance(algorithm); 
			sh.update(palntext.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch(NoSuchAlgorithmException e){

			e.printStackTrace(); 

			SHA = null; 

		}

		return SHA;

	}

}
