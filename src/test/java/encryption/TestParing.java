package encryption;

import encryption.parameter.BilinearGroup;
import org.junit.Test;

public class TestParing {
    @Test
    public void test() {
        BilinearGroup bg = new BilinearGroup();
        bg.MyParing();
    }
}
