package main;

public class main {
    public static void main(String[] args) {
        // 提供查询属性和字段执行精确查询

        // 对两个属性列执行连接查询
        int[]a={1,2,3};
        show(a);

    }
    public static void show(int...a){
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }
}
