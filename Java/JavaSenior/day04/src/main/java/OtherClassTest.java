import org.junit.Test;

import java.math.BigDecimal;

/**
 * 1. System
 * 2. Math
 * 3. BigInteger BigDecimal
 *
 * @author c1rew
 * @create 2020-12-02 22:00
 */
public class OtherClassTest {

    @Test
    public void test1() {
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
    }

    @Test
    public void test2() {
        BigDecimal bi0 = new BigDecimal("12121234345266874573516616375666666241");
        System.out.println(bi0);
        BigDecimal bi1 = new BigDecimal("3345.212");
        BigDecimal bi2 = new BigDecimal("11");
        // System.out.println(bi1.divide(bi2));   //没有指定规则，报错；
        System.out.println(bi1.divide(bi2,BigDecimal.ROUND_HALF_UP));
        System.out.println(bi1.divide(bi2,15,BigDecimal.ROUND_HALF_UP)); // 保留15位小数
    }

    @Test
    public void test3() {

    }
}
