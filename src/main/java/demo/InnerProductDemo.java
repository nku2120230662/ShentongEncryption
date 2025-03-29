package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InnerProductDemo {
    // 定义用于存储密文的数据库表
    static class EncryptedRecord {
        int[] encryptedValue;  // 加密列
        int id;                // 记录 ID

        public EncryptedRecord(int id, int[] encryptedValue) {
            this.id = id;
            this.encryptedValue = encryptedValue;
        }
    }

    // 模拟的数据库表(内存中的数组操作)
    static class DatabaseTable {
        List<EncryptedRecord> records;
        int[] publicKey;

        public DatabaseTable(int[] publicKey) {
            this.records = new ArrayList<>();
            this.publicKey = publicKey;
        }

        // 插入加密记录
        public void insert(int id, int[] value) {
            int[] encryptedValue = encrypt(publicKey, value);
            records.add(new EncryptedRecord(id, encryptedValue));
        }
    }

    // 内积加密：加密一个向量
    public static int[] encrypt(int[] publicKey, int[] message) {
        int[] encryptedValue = new int[message.length];
        for (int i = 0; i < message.length; i++) {
            encryptedValue[i] = publicKey[i] * message[i];
        }
        return encryptedValue;
    }

    // 等值连接操作：在两个表上执行加密等值连接
    public static List<int[]> innerJoin(DatabaseTable table1, DatabaseTable table2) {
        List<int[]> result = new ArrayList<>();

        for (EncryptedRecord record1 : table1.records) {
            for (EncryptedRecord record2 : table2.records) {
                // 检查加密值是否相等
                if (isEqual(record1.encryptedValue, record2.encryptedValue)) {
                    result.add(new int[]{record1.id, record2.id});
                }
            }
        }

        return result;
    }

    // 检查两个向量的密文是否相等
    private static boolean isEqual(int[] encryptedValue1, int[] encryptedValue2) {
        if (encryptedValue1.length != encryptedValue2.length) return false;
        for (int i = 0; i < encryptedValue1.length; i++) {
            if (encryptedValue1[i] != encryptedValue2[i]) {
                return false;
            }
        }
        return true;
    }

    // 测试示例
    public static void main(String[] args) {
        int n = 5; // 向量大小
        int[] publicKey = generatePublicKey(n); // 生成公钥

        DatabaseTable table1 = new DatabaseTable(publicKey);
        DatabaseTable table2 = new DatabaseTable(publicKey);

        // 插入加密数据到表1
        table1.insert(1, new int[]{1, 2, 3, 4, 5});
        table1.insert(2, new int[]{2, 3, 4, 5, 6});

        // 插入加密数据到表2
        table2.insert(3, new int[]{1, 2, 3, 4, 5});
        table2.insert(4, new int[]{5, 6, 7, 8, 9});

        // 执行加密等值连接
        List<int[]> joinResult = innerJoin(table1, table2);

        System.out.println("等值连接结果 (table1.id, table2.id):");
        for (int[] pair : joinResult) {
            System.out.println("[" + pair[0] + ", " + pair[1] + "]");
        }
    }

    // 生成公钥的辅助方法
    public static int[] generatePublicKey(int n) {
        int[] publicKey = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            publicKey[i] = rand.nextInt(10) + 1; // 1 到 10 的随机整数
        }
        return publicKey;
    }
}
