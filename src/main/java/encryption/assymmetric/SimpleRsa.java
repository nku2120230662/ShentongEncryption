package encryption.assymmetric;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

// 当前：公钥和私钥是确定的
// 未来：公钥确定(取决于服务器)，私钥取决于客户端，保存在数据表中；当前测试环境可将私钥绑定数据表，保存在数据表中
public class SimpleRsa {
    // RSA参数
    static String Base64PrivateKey="MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAknspjOY+XubdoDo1yw8PLTPIGVwmrOTPxDNM3ajISM4dBz5uIxps/kYtM3fgIOeE1C0+M/AjL2/FfyUOoMneUwIDAQABAkAnRYrxwib1pZrEouaaLsroWQosNmcOEnhbh09z3BT6Y5NQzt2oNfO0nz47qy4BvLJu3FiClFPCMz49a2ynzmBlAiEAqIU6sEG/X/6DaHae3nxU0nt7l6LESjBrHlzVkctjcW0CIQDehR2H6ZBvnxkEWd5WIKPMmbyyTeAZ6W7RMAaQdpB2vwIgOyzVZ/EeZ3Hy6OrGbK/SBTsxMhUIwlhwNPA6WoDGAZ0CIFRMoc9nkKx43YVkdnKykttkMrRqBEhMwwrE4ve/syMJAiAhsDJCbsBSEu+D5EWi2lVmkov/kNjRXHfFaMJ2TIHEaA==";
    static String Base64PublicKey ="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJJ7KYzmPl7m3aA6NcsPDy0zyBlcJqzkz8QzTN2oyEjOHQc+biMabP5GLTN34CDnhNQtPjPwIy9vxX8lDqDJ3lMCAwEAAQ==";

    //
    public static String encrypt(String data) throws Exception {
        // 获取公钥
        byte[] publicKeyBytes = Base64.getDecoder().decode(Base64PublicKey);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(Base64PrivateKey);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] myEncryptedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = decryptCipher.doFinal(myEncryptedData);
        return new String(decryptedBytes);
    }

}
