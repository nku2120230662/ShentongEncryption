package encryption.function;

import org.junit.Test;

import static encryption.parameter.ConvertMessageToVector.convertMessageToVector;
import static encryption.parameter.ConvertMessageToVector.convertVectorToMessage;

public class TestConvertMessageToVector {
    @Test
    public void test() {
        // 示例：明文 -> 向量
        String message = "Hello, World!";
        int[] vector = convertMessageToVector(message);
        System.out.println("Original Message: " + message);
        System.out.print("Converted Vector: ");
        for (int value : vector) {
            System.out.print(value + " ");
        }
        System.out.println();

        // 示例：向量 -> 明文
        String decodedMessage = convertVectorToMessage(vector);
        System.out.println("Decoded Message: " + decodedMessage);
    }
}
