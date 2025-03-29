package encryption.assymmetric;

import org.junit.Test;

public class TestRsa {
    @Test
    public void testRsa() throws Exception {
        SimpleRsa rsa = new SimpleRsa();
        String plainText = "He防护和23一幅画恢复2";
        String enp=rsa.encrypt(plainText);
        System.out.println(enp);
        String decrypt=rsa.decrypt(enp);
        System.out.println(decrypt);
    }
}
