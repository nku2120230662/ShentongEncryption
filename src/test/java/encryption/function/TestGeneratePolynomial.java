package encryption.function;

import encryption.parameter.GeneratePolynomial;
import org.junit.Test;

public class TestGeneratePolynomial {
    @Test
    public void testGeneratePolynomial() {
        int[] nums = GeneratePolynomial.function(1,2,3);
        // 打印多项式的系数
        System.out.print("多项式系数：");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
