package com.viper.exer;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author c1rew
 * @date 2020-12-06 17:12
 */

public class EmployeeTest {

    /**
     * 按照姓名排序
     */
    @Test
    public void test1() {
        TreeSet set = new TreeSet();
        set.add(new Employee("Jack",42,new MyDate(1978,2,8)));
        set.add(new Employee("Mick",21,new MyDate(1999,11,22)));
        set.add(new Employee("Jerry",33,new MyDate(1987,9,13)));
        set.add(new Employee("Candy",42,new MyDate(1978,2,9)));
        set.add(new Employee("Tom",20,new MyDate(2000,1,8)));

        set.forEach(System.out::println);
    }

    /**
     * 按照出生年月日排序
     */
    @Test
    public void test2(){

        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Employee && o2 instanceof Employee) {
                    Employee e1 = (Employee) o1;
                    Employee e2 = (Employee) o2;
                    MyDate d1 = e1.getBirthday();
                    MyDate d2 = e2.getBirthday();

                    int ret = d1.compareTo(d2);
                    // 年月日一样，比较名字顺序
                    if (ret == 0) {
                        return e1.getName().compareTo(e2.getName());
                    }
                    return  ret;
                }
                throw new RuntimeException("can not match type Employee");
            }
        });

        set.add(new Employee("Jack",42,new MyDate(1978,2,8)));
        set.add(new Employee("Mick",21,new MyDate(1999,11,22)));
        set.add(new Employee("Jerry",33,new MyDate(1987,9,13)));
        set.add(new Employee("Candy",42,new MyDate(1978,2,8)));
        set.add(new Employee("Tom",20,new MyDate(2000,1,8)));

        set.forEach(System.out::println);
    }
}
