import org.junit.Test;

import java.util.Date;

/**
 * @author c1rew
 * @create 2020-11-28 23:00
 */
public class JDK8DateTimeTest {

    @Test
    public void testDate() {
        // 减去偏移量
        Date date = new Date(2020 - 1900, 9 - 1, 8);
        System.out.println(date);
    }
}
