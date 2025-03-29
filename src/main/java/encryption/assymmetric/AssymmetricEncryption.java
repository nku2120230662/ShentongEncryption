package encryption.assymmetric;

import encryption.symmetric.SimpleAES;

public class AssymmetricEncryption {
    public static String Encrypt(Object obj) throws Exception {
        switch(obj.getClass().getName()) {
            case "java.lang.Integer":
                return SimpleAES.encrypt(String.valueOf((int)obj));
            case "java.lang.String":
                String cipherText = SimpleAES.encrypt((String) obj);
                return cipherText;
            default:
                String cipherText1 = SimpleAES.encrypt((String) obj);
                return cipherText1;
        }
    }

    public static String Decrypt(String CipherText)throws Exception {

        String DecryptedText = SimpleAES.decrypt(CipherText);
        return DecryptedText;
    }
}