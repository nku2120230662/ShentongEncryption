package encryption.function;

import java.util.Random;

public class SimpleFunctionalEncryption {
    // 生成公钥和私钥
    public static class KeyPair {
        public int[] publicKey;
        public int[] privateKey;

        public KeyPair(int n) {
            publicKey = new int[n];
            privateKey = new int[n];

            // 随机生成公钥和私钥
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                publicKey[i] = rand.nextInt(10);  // 公钥元素为0到9之间的整数
                privateKey[i] = rand.nextInt(10); // 私钥元素为0到9之间的整数
            }
        }
    }

    // 加密函数：将消息向量与公钥矩阵进行内积
    public static int encrypt(int[] publicKey, int[] message) {
        int ciphertext = 0;
        for (int i = 0; i < publicKey.length; i++) {
            ciphertext += publicKey[i] * message[i]; // 内积计算
        }
        return ciphertext;
    }

    // 解密函数：将密文与私钥向量进行内积
    public static int decrypt(int[] privateKey, int ciphertext) {
        int message = 0;
        for (int i = 0; i < privateKey.length; i++) {
            message += privateKey[i] * ciphertext; // 内积计算
        }
        return message;
    }
}
