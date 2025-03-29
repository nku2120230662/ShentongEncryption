package encryption.parameter;

public class GeneratePolynomial {
    // 根据零点生成一个多项式,该多项式最后由客户端发送给服务器，服务器逐行解密将匹配多项式的记录存储到内存中
    public static int[] function(int... zeroPoint) {
        // 初始多项式系数为 [1]（即 1x^0）
        int[] result = {1};

        // 遍历零点，生成多项式的系数
        for (int j : zeroPoint) {
            result = multiplyPolynomial(result, new int[]{-j, 1});
        }

        return result;
    }

    // 乘法：将两个多项式相乘，返回新的多项式系数
    private static int[] multiplyPolynomial(int[] poly1, int[] poly2) {
        int[] result = new int[poly1.length + poly2.length - 1]; // 乘法结果的长度

        // 进行多项式乘法
        for (int i = 0; i < poly1.length; i++) {
            for (int j = 0; j < poly2.length; j++) {
                result[i + j] += poly1[i] * poly2[j];
            }
        }
        return result;
    }

    // 判断一个整数是否为多项式的零点
    public static boolean isZeroPoint(int[] polynomial, int x) {
        int result = 0;
        int power = 1; // 当前 x 的幂次，初始为 x^0 = 1

        // 计算多项式值：result = c0 + c1 * x + c2 * x^2 + ...
        for (int coefficient : polynomial) {
            result += coefficient * power;
            power *= x; // 更新 x 的幂次
        }

        // 如果结果为 0，则 x 是多项式的零点
        return result == 0;
    }
}
