package demo;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

import static java.lang.Thread.sleep;

public class MeasureTimeDemo {
    public static void main(String[] args) {
        // 记录程序开始的时间
        LocalTime startTime = LocalTime.now();
        System.out.println("程序开始时间: " + startTime);

        // 模拟程序执行（这里使用一个简单的循环作为示例）
        long sum = 0;
        for (int i = 0; i < 1000000; i++) {
            sum += i;
        }

        // 记录程序结束的时间
        LocalTime endTime = LocalTime.now();
        System.out.println("程序结束时间: " + endTime);

        // 计算程序运行时间
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("程序运行时间: " + duration.toMillis() + " 毫秒");
    }
}
