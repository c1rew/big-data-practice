import org.junit.Test;

import java.util.Arrays;

/**
 * 对象比较，需要实现两个接口中的任何一个
 * Comparable 或 Comparator
 *
 * @author c1rew
 * @create 2020-11-29 19:42
 */
public class CompareTest {
    /**
     * Comparable接口使用示例
     * 1. 像String、包装类等实现了Comparable接口，重写了compareTo(obj)方法
     * 2. 像String、包装类重写compareTo方法，进行了从小到大的排列
     * 3. 重写compareTo(obj)规则
     * 当前对象this大于形参对象obj，返回正整数
     * 当前对象this小于形参对象obj，返回负整数
     * 当前对象this等于形参对象obj，返回零
     * <p>
     * 4. 对于自定义类，如果需要排序，需要实现Comparable接口，重写compareTo方法
     */
    @Test
    public void test1() {
        String[] arr = {"AA", "KK", "CC", "GG", "PP"};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    @Test
    public void test2() {
        Product[] products = new Product[4];
        products[0] = new Product("Lenovo", 45);
        products[1] = new Product("Xiaomi", 15);
        products[2] = new Product("Huawei", 30);
        products[3] = new Product("Dell", 35);

        Arrays.sort(products);
        System.out.println(Arrays.toString(products));
    }
}
