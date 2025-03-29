package encryption.function;

import encryption.parameter.MasterKey;

public class InnerProductEncryption {

    /*
    *
    * // 安全参数λ——密钥和密文空间大小
    // n(客户端数量）
    * private int lambda;
    private int n;
    *
    *
    *
    * */
    // publicParameter
    private PublicParameter publicParameter;
    // msk参与形成密钥
    private MasterKey masterKey;

    class PublicParameter {
        // 可以读取文件设定固定的公共参数和密钥
        public boolean[] lambda1;
        public byte[] paramv;

    }

    // SetUp 生成公共参数pp和msk
    public void SetUp(/*int lambda, int n*/){
        byte[] paramv;

    }

    // 为每个客户端生成私钥
    public void KeyGeneration(MasterKey masterKey, String x) {

    }

    public Object Encrypt(PublicParameter publicParameter, String y) {
        return null;
    }

    public Object Decrypt(PublicParameter publicParameter, MasterKey masterKey, Object ctx) {
        return null;
    }
}
