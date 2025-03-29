package encryption.parameter;

//参考文档：https://blog.csdn.net/qq_41359358/article/details/111158239?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7EPaidSort-1-111158239-blog-107325591.235%5Ev43%5Epc_blog_bottom_relevance_base8&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7EPaidSort-1-111158239-blog-107325591.235%5Ev43%5Epc_blog_bottom_relevance_base8&utm_relevant_index=1

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

import java.io.*;
import java.util.Base64;

// 作用：返回一个群
public class BilinearGroup {
    Field G1;
    Field G2;
    Field Gt;

    /**
     * AutoParing : 自动生成配对参数，即时配对
     */
    public void AutoParing() {
//         一、定义椭圆曲线参数
         int rBits = 32;
         int qBits = 36;
         TypeACurveGenerator pg = new TypeACurveGenerator(rBits, qBits);
         PairingParameters pp = pg.generate();
         Pairing bp = PairingFactory.getPairing(pp);

        // 二、选择群上的元素
        Field G1 = bp.getG1();
        Field G2 = bp.getG2();
        Field Zr = bp.getZr();
        //获取G1/G2的一个元素
        Element g1 = G1.newRandomElement().getImmutable();
        Element g2 = G2.newRandomElement().getImmutable();
        Element a = Zr.newRandomElement().getImmutable();
        Element b = Zr.newRandomElement().getImmutable();

        // 三、计算等式e(g^a,g^b)
        Element ga = g1.powZn(a);
        Element gb = g1.powZn(b);
        Element egg_a_b = bp.pairing(ga, gb);

        // 四、计算等式e(g,g)^(ab)
        Element egg = bp.pairing(g1, g1).getImmutable();
        Element ab = a.mul(b);
        Element egg_ab = egg.powZn(ab);

        if (egg_a_b.isEqual(egg_ab)) {
            System.out.println(egg_a_b);
            System.out.println("yes");
        } else {
            System.out.println("No");
        }
    }

    /**
     * MyParing: 从文件中读取配对参数，即时配对
     */
    public void MyParing() {
        String filePath = "a.properties";

        File paramFile = new File(filePath);
        if (!paramFile.exists()) {
            // 文件不存在，调用生成参数的方法
            System.out.println("参数文件不存在，正在生成...");
            SavingParams(filePath);
            System.out.println("生成完毕");
        }

        Pairing bp = PairingFactory.getPairing(filePath);

        // 二、选择群上的元素
        Field G1 = bp.getG1();
        Field G2 = bp.getG2();
        Field Zr = bp.getZr();
        //获取G1/G2的一个元素
        Element g1 = G1.newRandomElement().getImmutable();
        Element g2 = G2.newRandomElement().getImmutable();
        Element a = Zr.newRandomElement().getImmutable();
        Element b = Zr.newRandomElement().getImmutable();

        // 三、计算等式e(g^a,g^b)
        Element ga = g1.powZn(a);
        Element gb = g2.powZn(b);
        Element egg_a_b = bp.pairing(ga, gb);

        // 四、计算等式e(g,g)^(ab)
        Element egg = bp.pairing(g1, g2).getImmutable();
        Element ab = a.mul(b);
        Element egg_ab = egg.powZn(ab);

        if (egg_a_b.isEqual(egg_ab)) {
            System.out.println(egg_a_b);
            System.out.println("yes");
        } else {
            System.out.println("No");
        }
    }

    /**
     * 生成并保存配对参数
     * @return
     */
    public boolean SavingParams(String filePath) {
        // 一、定义椭圆曲线参数
        int rBits = 32;
        int qBits = 36;
//        String filePath = "a.properties";
        // 使用 TypeACurveGenerator 生成配对参数
        TypeACurveGenerator generator = new TypeACurveGenerator(rBits, qBits);
        PairingParameters params = generator.generate();

        // 获取参数字符串
        String paramsString = params.toString();

        // 将参数写入文件
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println(paramsString);
            System.out.println("参数已成功保存到文件：" + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
            return false;
        }
    }
}
