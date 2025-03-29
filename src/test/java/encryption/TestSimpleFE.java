package encryption;

import encryption.function.SimpleFunctionalEncryption;
import org.junit.Test;

public class TestSimpleFE {
    @Test
    public void testSimpleFE() {
        int n = 5; // 向量的大小
        SimpleFunctionalEncryption.KeyPair keyPair = new SimpleFunctionalEncryption.KeyPair(n);

        // 构造一个简单的消息向量
        int[] message = {1, 2, 3, 4, 6};

        System.out.println("公钥: ");
        for (int i = 0; i < keyPair.publicKey.length; i++) {
            System.out.print(keyPair.publicKey[i] + " ");
        }
        System.out.println();

        System.out.println("私钥: ");
        for (int i = 0; i < keyPair.privateKey.length; i++) {
            System.out.print(keyPair.privateKey[i] + " ");
        }
        System.out.println();

        // 加密
        int ciphertext = SimpleFunctionalEncryption.encrypt(keyPair.publicKey, message);
        System.out.println("加密后的密文: " + ciphertext);

        // 解密
        int decryptedMessage = SimpleFunctionalEncryption.decrypt(keyPair.privateKey, ciphertext);
        System.out.println("解密后的消息: " + decryptedMessage);
    }
}
