/**
 * @author c1rew
 * @create 2020-07-28 22:52
 */
public class StringBufferBuilderTest {
    /*
    String, StringBuffer, StringBuilder 三者异同
    String：不可变的字符序列，底层使用char[] 存储
    StringBuffer：可变的字符序列；线程安全的，效率低；底层使用char[] 存储
    StringBuilder：可变的字符序列；JDK 5.0新增，线程不安全的，效率高；底层使用char[] 存储

    从 JDK 9.0 开始，底层使用byte[]存储

    源码分析
    String str = new String(); // new char[0]
    String str1 = new String("abc"); // char[] value = new char[]{'a','b','c'}

    StringBuffer sb1 = new StringBuffer(); //char[] value = new char[16];初始化大小16
    sb1.append('a'); // value[0] = 'a';

    StringBuffer sb2 = new StringBuffer("abc"); // char[] value = new char["abc".length+16];

    问题1. System.out.println(sb2.length()); // 3
    问题2. 扩容问题 newCapacity = (value.length << 1) + 2 // 原来的2倍+2，并复制数据

    指导意义：开发中建议使用StringBuffer(int capacity) 或 StringBuilder(int capacity)指定大小，避免扩容
     */
}
