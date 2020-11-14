import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class TripleDES{
    
    private static Cipher encryptCipher;
    private static Cipher decryptCipher;
    
    public static void main(String[] args) {
        try {
             File  uploadedfile = new File("/Applications/MAMP/htdocs/test/enc.txt");
         FileReader fileReader  = new FileReader(uploadedfile);
        BufferedReader reader = new BufferedReader (fileReader);
         System.out.println("Attempting to read from file in: "+uploadedfile.getCanonicalPath());

        String line = null;
        while ((line = reader.readLine())!= null){
            System.out.println(line);
        }
        reader.close();
        FileUtils fu = new FileUtils();
        String teststring = fu.readFileToString(uploadedfile);    
        
        
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keygenerator.generateKey();
            
            encryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = encryptData(teststring);
            
            decryptCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptData(encryptedData);
           
           } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
            catch(Exception e){
        e.printStackTrace();
    }
         
    }
    
    /**
     * Encrypt Data
     * @param data
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static byte[] encryptData(String data)
    throws IllegalBlockSizeException, BadPaddingException {
        System.out.println("Data Before Encryption :" + data);
        byte[] dataToEncrypt = data.getBytes();
        byte[] encryptedData = encryptCipher.doFinal(dataToEncrypt);
        System.out.println("Encryted Data: " + encryptedData);
        
        return encryptedData;
    }
    
    /**
     * Decrypt Data
     * @param data
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static void decryptData(byte[] data)
    throws IllegalBlockSizeException, BadPaddingException {
        byte[] textDecrypted = decryptCipher.doFinal(data);
        System.out.println("Decryted Data: " + new String(textDecrypted));
    }
}