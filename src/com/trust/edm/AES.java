/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Akshay
 */
//package org.kodejava.example.commons.io;

import java.io.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.FileUtils;




public class AES {
    public StringBuffer sb;
    public static void main(String[] args) throws Exception {
    String key = "E1BB465D57CAE7ACDBBE8091F9CE83DF";
    String key1 = "ABCD0F1E2D3C4B5A6A12B11C10D9E8F7";
    File  uploadedfile = new File("/Applications/MAMP/htdocs/test/enc.txt");

  	String plaintext = "<xml><tpPago>C</tpPago><amount>3441.16</amount><urlResponse>www.catwebuat.com.mx/corpora/auto/resultadoMIT.asp?webpay=1</urlResponse><referencia>645331840000</referencia><moneda>MXN</moneda><date_hour> 2013-07-10T14:49:24-05:00<date_hour>";
  	//String mytext = "This is Akshay Jagtap";
        
      try {
        FileReader fileReader  = new FileReader(uploadedfile);
        BufferedReader reader = new BufferedReader (fileReader);
         System.out.println("Attempting to read from file in: "+uploadedfile.getCanonicalPath());

        String line = null;
        while ((line = reader.readLine())!= null){
            System.out.println(line);
        }
        reader.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
        FileUtils fu = new FileUtils();
        String mytext = fu.readFileToString(uploadedfile);
        String crp = encrypt(plaintext, key);
  	String dec = decrypt(crp, key);
        String crp1 = encrypt(mytext, key);
  	String dec1 = decrypt(crp1, key);
        String crp2 = encrypt(mytext, key1);
  	String dec2 = decrypt(crp2, key1);
        
  	
    //String key = "605bd70efed2c6374823b54bbc560b58";
  	//String plaintext = "5454545454545454";
  	//String crp = encrypt(plaintext, key);
  	//String dec = decrypt(crp, key);
  	System.out.println("Encrypt:" + crp );
  	System.out.println("Decrypt:" + dec);
        System.out.println("Encrypt:" + crp1 );
  	System.out.println("Decrypt:" + dec1);
        System.out.println("Encrypt:" + crp2 );
  	System.out.println("Decrypt:" + dec2 );
        
  }
  
  private static final String ALGORITMO = "AES/CBC/PKCS5Padding";
private static final String CODIFICACION = "UTF-8";

public static String encrypt(String plaintext, String key)throws NoSuchAlgorithmException, NoSuchPaddingException,InvalidKeyException, IllegalBlockSizeException,BadPaddingException, IOException{
	byte[] raw = DatatypeConverter.parseHexBinary(key);
	SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	Cipher cipher = Cipher.getInstance(ALGORITMO);
	cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	byte[] cipherText = cipher.doFinal(plaintext.getBytes(CODIFICACION));
	byte[] iv = cipher.getIV();
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	outputStream.write(iv);
	outputStream.write(cipherText);
	byte[] finalData = outputStream.toByteArray();
	String encodedFinalData = DatatypeConverter.printBase64Binary(finalData);
	return encodedFinalData;
}

public static String decrypt(String encodedInitialData, String key)throws InvalidKeyException, IllegalBlockSizeException,BadPaddingException, UnsupportedEncodingException,NoSuchAlgorithmException, NoSuchPaddingException,InvalidAlgorithmParameterException{
	byte[] encryptedData = DatatypeConverter.parseBase64Binary(encodedInitialData);
	byte[] raw = DatatypeConverter.parseHexBinary(key);
	SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
	Cipher cipher = Cipher.getInstance(ALGORITMO);
	byte[] iv = Arrays.copyOfRange(encryptedData, 0, 16);
	byte[] cipherText = Arrays.copyOfRange(encryptedData, 16, encryptedData.length);
	IvParameterSpec iv_specs = new IvParameterSpec(iv);
	cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv_specs);
	byte[] plainTextBytes = cipher.doFinal(cipherText);
	String plainText = new String(plainTextBytes);
	return plainText;
	}
}
 

