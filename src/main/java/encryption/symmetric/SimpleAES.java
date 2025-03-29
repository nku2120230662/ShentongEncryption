package encryption.symmetric;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SimpleAES {
    // 固定密钥和初始化向量
    final static String key = "1234567890123456"; // 16 字节密钥
    final static String iv = "1234567890123456";  // 16 字节初始向量

    public static String encrypt(String data) throws Exception {
//        public static String encrypt(String data, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encrypted = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedData) throws Exception {
//    public static String decrypt(String encryptedData, String key, String iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decrypted);
    }
}
