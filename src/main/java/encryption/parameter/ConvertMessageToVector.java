package encryption.parameter;

//
public class ConvertMessageToVector {
    public static int[] convertMessageToVector(String message) {
        // 将每个字符的 ASCII 值存储到整数数组中
        int[] vector = new int[message.length()];
        for (int i = 0; i < message.length(); i++) {
            vector[i] = (int) message.charAt(i); // 获取字符的 ASCII 值
        }
        return vector;
    }

    /**
     * 将向量反映射为明文字符串
     * @param vector 整数向量
     * @return 反映射后的明文字符串
     */
    public static String convertVectorToMessage(int[] vector) {
        // 使用 StringBuilder 将整数向量转换回字符串
        StringBuilder message = new StringBuilder();
        for (int value : vector) {
            message.append((char) value); // 根据 ASCII 值还原字符
        }
        return message.toString();
    }
}
