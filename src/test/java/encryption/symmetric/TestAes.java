package encryption.symmetric;

import org.junit.Test;

import static encryption.symmetric.SimpleAES.decrypt;
import static encryption.symmetric.SimpleAES.encrypt;

public class TestAes {
    @Test
    public void testAesEncryption() throws Exception{
        String plaintext = "Hello, AES!";


        // 加密
        String encryptedText = encrypt(plaintext);
        System.out.println("加密后的文本: " + encryptedText);

        // 解密
        String decryptedText = decrypt(encryptedText);
        System.out.println("解密后的文本: " + decryptedText);
    }
}
