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
     * 2. 当对现有字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
     * 3. 当调用String 的replace方法时，修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
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
     * <p>
     * 面试题：String s = new String("abc"); 方式创建对象，在内存中创建了几个对象
     * 两个：一个是堆空间中new结构，另一个是char[]对应常量池中的数据 "abc"
     */
    @Test
    public void test2() {
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

    /**
     * 1. 常量与常量的拼接结果在常量池，且常量池不会存在相同内容的常量
     * 2. 只要有其中一个是变量，结果就在堆中
     * 3. 如果拼接的结果调用了intern方法，返回值就在常量池中
     */
    @Test
    public void test3() {
        String s1 = "JavaEE";
        String s2 = "hadoop";

        String s3 = "JavaEEhadoop";
        String s4 = "JavaEE" + "hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "JavaEE" + s2;
        String s7 = s1 + s2;

        System.out.println(s3 == s4); // true
        System.out.println(s3 == s5); // false
        System.out.println(s3 == s6); // false
        System.out.println(s3 == s7); // false
        System.out.println(s5 == s6); // false
        System.out.println(s5 == s7); // false
        System.out.println(s6 == s7); // false

        // intern JDK 1.6 及 JDK 1.7的有差异， 1.7及其之后常量池在堆区
        String s8 = s6.intern();
        System.out.println(s3 == s8); // true
        // JavaEEhadoop 已经在s3定义时，存在于常量池中，s6指向的仍然是堆内存
        // 如果常量池中没有该常量，则当下创建后返回的地址就是常量池对应的地址
        System.out.println(s6 == s8); // false
        System.out.println(System.identityHashCode(s3));
        System.out.println(System.identityHashCode(s6));
        System.out.println(System.identityHashCode(s8));

        System.out.println("---------------------------");
        // str位于常量池
        String str = "abcdef";
        System.out.println("str:"+ System.identityHashCode(str));
        // str1 及str2均指向 堆，但是 常量池中有 abc  和  def 两个常量
        String str1 = new String("abc") + "def";
        String str2 = new String("abc") + new String("def");
        System.out.println("str1:"+ System.identityHashCode(str1));
        System.out.println("str2:"+ System.identityHashCode(str2));

        // str3 判断常量池中是否有abcdef，发现已经有了，则直接返回
        String str3 = str2.intern();
        System.out.println("str1:"+ System.identityHashCode(str1));
        System.out.println("str2:"+ System.identityHashCode(str2));
        System.out.println("str3:"+ System.identityHashCode(str3));

        System.out.println(str2 == str1);
        System.out.println(str1 == str3);
        System.out.println(str2 == str3);
        System.out.println(str == str3);
    }


}
