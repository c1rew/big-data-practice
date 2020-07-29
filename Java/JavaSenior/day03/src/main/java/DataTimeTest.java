import org.junit.Test;

import java.util.Date;

/**
 * Java 8 之前
 *
 * @author c1rew
 * @create 2020-07-29 21:45
 */
public class DataTimeTest {

    /**
     * java.utils.Date
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
