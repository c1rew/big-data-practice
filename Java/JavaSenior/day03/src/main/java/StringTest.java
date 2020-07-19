import org.junit.Test;

/**
 * @author c1rew
 * @create 2020-07-14 22:57
 */
public class StringTest {
    /**
     * 使用""引用起来
     * 1. String声明为final的，不可被继承
     * 2. String实现了Serializable接口，表示字符串是支持序列化的
     * 实现了Comparable接口，表示String是可以比较大小的
     * 3. String内部定义了final char[] value用于存储字符串数据
     * 4. String代表不可变的字符序列。  简称：不可变性
     * 体现：1. 当字符串重新赋值，需要重新制定内存区域赋值，不能使用原有的value进行赋值
     *      2. 当对现有字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
     *      3. 当调用String 的replace方法时，修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
     * <p>
     * 5. 通过字面量的方式给一个字符串赋值，此时的字符串值生命在字符串常量池中；
     * 6. 字符串常量池中不会存储相同内容的字符串
     */
    @Test
    public void test1() {
        String s1 = "abc"; // 字面量的定义方式
        String s2 = "abc";

        System.out.println(s1 == s2);
        s1 = "hello";

        System.out.println(s1);
        System.out.println(s2);

        String s3 = "abc";
        s3 += "def";
        System.out.println(s3);
        System.out.println(s2);

        String s4 = "abc";
        String s5 = s4.replace("a", "m");
        System.out.println(s4);
        System.out.println(s5);
    }

    /**
     * String 实例化方式：
     * 1. 通过字面量定义
     * 2. 通过new+构造器
     *
     * 面试题：String s = new String("abc"); 方式创建对象，在内存中创建了几个对象
     *    两个：一个是堆空间中new结构，另一个是char[]对应常量池中的数据 "abc"
     */
    @Test
    public  void test2() {
        // s1 s2 声明在方法去中的字符串常量池中
        String s1 = "JavaEE";
        String s2 = "JavaEE";

        // s3,s4的地址值，是数据在堆空间中开辟空间以后对应的地址
        String s3 = new String("JavaEE");
        String s4 = new String("JavaEE");

        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s1 == s4); // false
        System.out.println(s3 == s4); // false;


        Person p1 = new Person("Tom", 12);
        Person p2 = new Person("Tom", 12);

        System.out.println(p1.name.equals(p2.name));  // true
        System.out.println(p1.name == p2.name);       // true

        p1.name = "Jerry";
        System.out.println(p2.name); // Tom
    }
}
