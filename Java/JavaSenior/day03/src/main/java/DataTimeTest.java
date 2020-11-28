import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Java 8 之前
 *
 * @author c1rew
 * @create 2020-07-29 21:45
 */
public class DataTimeTest {


    /**
     * 4. Calender 日历类
     */
    @Test
    public void testCalendar() {
        // 1. 实例化
        // 方式一  创建子类对象 GregorianCalendar
        // 方式二  调用静态方法  getInstance
        Calendar calendar = Calendar.getInstance();

        // 2.常用方法

    }


    /**
     * 3. SimpleDataFormat
     *
     * 两个操作
     * 1. 格式化 日期--->字符串
     * 2. 解析  格式化逆过程  字符串 --->日期
     *
     * SimpleDataFormat的实例化
     */
    @Test
    public void test3() throws ParseException {
        // 实例化,默认构造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        Date date = new Date();

        String format = sdf.format(date);
        System.out.println(format);

        // 解析，字符串必须符合默认构造器对应的结构
        String str = "11/23/20 6:52 AM";
        Date date1 = sdf.parse(str);
        System.out.println(date1);

        // 自定义构造
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = sdf1.format(date);
        System.out.println(format1);

        // 解析，字符串必须符合自定义构造中指定的格式
        Date date2 = sdf1.parse("2020-11-23 07:45:54");
        System.out.println(date2);

        // 练习：字符串“2020-11-23”转换为java.sql.Date
        String birth = "2020-11-23";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 = sdf2.parse(birth);
        java.sql.Date birthDate = new java.sql.Date(date3.getTime());
        System.out.println(birthDate);
    }

    /**
     * 2. java.utils.Date
     * ----java.sql.Date
     * <p>
     * 1. 两个构造器的使用
     *
     *
     * 2. 两个方法的使用
     * >toString() 显示当牛叉你年月日时分秒
     * >getTime() 获取当前Date对象的毫秒数，时间戳
     *
     * 3. java.sql.Date 对应数据库日期类型的变量
     *    如何实例化
     *    java.utils.Date --->java.sql.Date 父类转成子类？？
     */
    @Test
    public void test2() {
        // 构造器一 Date() 当前时间的Date对象
        Date date1 = new Date();
        System.out.println(date1.toString()); // Wed Jul 29 21:50:15 CST 2020
        System.out.println(date1.getTime()); // 1596030615723

        // 构造器二 指定毫秒数
        Date date2 = new Date(1596030615723L);
        System.out.println(date2);

        java.sql.Date date3 = new java.sql.Date(1596030615723L);
        System.out.println(date3);

        // 子类转父类，多态
        java.sql.Date date4 = new java.sql.Date(1596030615723L);
        Date date5 = (Date) date4;
        System.out.println(date5);


        Date date6 = new Date(1596030615723L);
        // 父类转子类，运行报错
//        java.sql.Date date7 = (java.sql.Date) date6;

        // 这个操作ok
        java.sql.Date date8 = new java.sql.Date(date6.getTime());

    }


    // 1 System类 currentTimeMillis()方法
    @Test
    public void test1() {
        // 返回当前时间与1970年1月日0时0分0秒之间以毫秒为单位的时间差，称为时间戳
        long time = System.currentTimeMillis();
        System.out.println(time);
    }


}
