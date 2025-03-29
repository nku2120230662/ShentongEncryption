package demo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

// jmh基准测试
public class BenchmarkExample {

    // 基准测试方法1：StringBuilder 拼接
    @Benchmark
    @Warmup(iterations = 3)  // 预热阶段运行3次
    @Measurement(iterations = 5)  // 测量阶段运行5次
    @Fork(1)  // 启动1个JVM实例进行基准测试
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("test");
        }
        sb.toString();
    }

    // 基准测试方法2：String 拼接
    @Benchmark
    @Warmup(iterations = 3)  // 预热阶段运行3次
    @Measurement(iterations = 5)  // 测量阶段运行5次
    @Fork(1)  // 启动1个JVM实例进行基准测试
    public void testString() {
        String str = "";
        for (int i = 0; i < 1000; i++) {
            str += "test";
        }
    }

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(BenchmarkExample.class.getSimpleName())  // 运行 BenchmarkExample 类中的基准测试
                .forks(1)  // 启动1个JVM实例进行测试
                .build();

        new Runner(options).run();  // 执行基准测试
    }
}
