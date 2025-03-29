package encryption.parameter;

// MapStringToInt : 作用于多项式函数映射，目的是把字符串映射到数域上
public class MapStringToInt {
    public static int function(String input) {
        // res后期为bigInt
        int res=0;
        for (int i = 0; i < input.length(); i++) {
            res=res*100+input.charAt(i);
        }
        return res;
    }
}
