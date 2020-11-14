

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import org.apache.commons.io.FileUtils;
import java.io.*;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.io.FileUtils;

public class DES{
   
    public static void main(String[] args) {
        File  uploadedfile = new File("/Applications/MAMP/htdocs/test/enc.txt");
        try{
            try {
                Cipher c = Cipher.getInstance("DESede");
            } catch (Exception e) {               
                System.err.println("Installing SunJCE provider.");
                Provider sunjce = new com.sun.crypto.provider.SunJCE();
                Security.addProvider(sunjce);
            }
            FileReader fileReader  = new FileReader(uploadedfile);
        BufferedReader reader = new BufferedReader (fileReader);
         System.out.println("Attempting to read from file in: "+uploadedfile.getCanonicalPath());

        String line = null;
        while ((line = reader.readLine())!= null){
            System.out.println(line);
        }reader.close();
            FileUtils fu = new FileUtils();
            String teststring = fu.readFileToString(uploadedfile);
            // This is where we'll read the key from or write it to
            File keyfile = new File(teststring);
            
                System.out.print("Generating key. This may take some time...");
                System.out.flush();
                
                SecretKey key = generateKey();
                writeKey(key, keyfile);
                System.out.println("done.");
            //    System.out.println("Secret key written to "+uploadedfile+ ". Protect that file carefully!");
           
                key = readKey(keyfile);
                encrypt(key, System.in, System.out);
                 key = readKey(keyfile);
                decrypt(key, System.in, System.out);
            }
catch(Exception e) {
            System.err.println(e);
        }
}
    
    /** Generate a secret TripleDES encryption/decryption key */
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        // Get a key generator for Triple DES (a.k.a DESede)
        KeyGenerator keygen = KeyGenerator.getInstance("DESede");
        // Use it to generate a key
        return keygen.generateKey();
    }
   
    public static void writeKey(SecretKey key, File f) throws IOException,
    NoSuchAlgorithmException, InvalidKeySpecException {
        // Convert the secret key to an array of bytes like this
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        DESedeKeySpec keyspec = (DESedeKeySpec) keyfactory.getKeySpec(key,DESedeKeySpec.class);
        byte[] rawkey = keyspec.getKey();
        
        // Write the raw key to the file
        FileOutputStream out = new FileOutputStream(f);
        out.write(rawkey);
        out.close();
    }
    
    /** Read a TripleDES secret key from the specified file */
    public static SecretKey readKey(File f) throws IOException,NoSuchAlgorithmException, InvalidKeyException,InvalidKeySpecException {
        // Read the raw bytes from the keyfile
        DataInputStream in = new DataInputStream(new FileInputStream(f));
        byte[] rawkey = new byte[(int) f.length()];
        in.readFully(rawkey);
        in.close();
        
        DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyfactory.generateSecret(keyspec);
        return key;
    }
    
    public static void encrypt(SecretKey key, InputStream in, OutputStream out)throws NoSuchAlgorithmException, InvalidKeyException,NoSuchPaddingException, IOException {
        // Create and initialize the encryption engine
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        
        // Create a special output stream to do the work for us
        CipherOutputStream cos = new CipherOutputStream(out, cipher);
        
        // Read from the input and write to the encrypting output stream
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            cos.write(buffer, 0, bytesRead);
        }
        cos.close();
        
        // For extra security, don't leave any plaintext hanging around memory.
        java.util.Arrays.fill(buffer, (byte) 0);
    }
    public static void decrypt(SecretKey key, InputStream in, OutputStream out)throws NoSuchAlgorithmException, InvalidKeyException, IOException,IllegalBlockSizeException, NoSuchPaddingException,
    BadPaddingException {
        // Create and initialize the decryption engine
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        // Read bytes, decrypt, and write them out.
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(cipher.update(buffer, 0, bytesRead));
        }
        out.write(cipher.doFinal());
        out.flush();
    }
}