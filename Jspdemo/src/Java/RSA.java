package Java;
import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
public class RSA {
	static GenerateKeys key ;
  private String mass;
  private String encode;
  private String decode;
 
  

    public String getEncode() throws Exception{
    	GenerateKeys.run();
        PrivateKey privateKey = getPrivateKey();
        PublicKey publicKey = getPublicKey();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        
        byte[] byteEncrypted = cipher.doFinal(mass.getBytes());
        String encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
        
        
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
        String decrypted = new String(byteDecrypted);
            setEncode(encrypted);
            setMass(decrypted);
            return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }
    
    public void setDecode(String decode) {
        this.decode = decode;
    }
    
    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }
  public static PrivateKey getPrivateKey() throws Exception {
    byte[] keyBytes = Files.readAllBytes(new File(GenerateKeys.PRIVATE_KEY_FILE).toPath());
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePrivate(spec);
  }
  public static PublicKey getPublicKey() throws Exception {
    byte[] keyBytes = Files.readAllBytes(new File(GenerateKeys.PUBLIC_KEY_FILE).toPath());
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(spec);
  }
  
  public String getDecode() throws Exception {
		GenerateKeys.run();
	    PrivateKey privateKey = getPrivateKey();
	    PublicKey publicKey = getPublicKey();
	    Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    
	    byte[] byteEncrypted = cipher.doFinal(mass.getBytes());
	    String encrypted =  Base64.getEncoder().encodeToString(byteEncrypted);
	    
	    
	    cipher.init(Cipher.DECRYPT_MODE, privateKey);
	    byte[] byteDecrypted = cipher.doFinal(byteEncrypted);
	    String decrypted = new String(byteDecrypted);
	        setEncode(encrypted);
	        setDecode(decrypted);
	        return decode;
	  }
}