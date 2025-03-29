package demo;

import java.math.BigDecimal;

// 练习输入流
enum Weekday {
    SUN, MON, TUE, WED, THU, FRI, SAT;

}

public class EnumDemo {
    public static void main(String[] args) {
        BigDecimal d3 = new BigDecimal("1234500");
        BigDecimal d4 = d3.stripTrailingZeros();
        System.out.println(d3.scale()); // 0
        System.out.println(d4); // -2
    }
}
