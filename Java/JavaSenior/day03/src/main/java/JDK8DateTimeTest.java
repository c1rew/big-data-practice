import org.junit.Test;

import java.time.*;
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


    @Test
    public void test1() {
        // now 获取当前日期，时间，日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        /**
         * 2020-11-29
         * 15:51:02.931
         * 2020-11-29T15:51:02.932
         */
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        // of 指定年月日时分秒
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 12, 3, 0, 0, 0, 0);
        System.out.println(localDateTime1);
    }

    /**
     * Instant的使用
     */
    @Test
    public void test2() {
        Instant instant = Instant.now();
        System.out.println(instant); // 本初子午线标准时间

        // 东八区
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        // 获取自 1970年1月1日0时0分0秒（UTC）以来的毫秒数
        long milli = instant.toEpochMilli();
        System.out.println(milli);
    }

    /**
     * DateTimeFormatter 格式化解析日期，时间
     * 类似SimpleDateFormat
     */
}
