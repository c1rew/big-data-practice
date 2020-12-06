package com.viper;

import org.junit.Test;

import java.util.HashSet;

/**
 *
 * 集合框架
 * 1. Collection接口：单列集合，用来存储一个一个对象
 *      List接口：存储有序、可重复的数据
 *          ArrayList、LinkedList、Vector
 *
 *      Set接口：无序、不可重复的数据
 *          HashSet、LinkedHashSet、TreeSet
 *
 * 2. Map接口：双列集合，一对一对的数据
 *          HashMap、LinkedHashMap、TreeMap、HashTable、Properties
 *
 * @author c1rew
 * @create 2020-12-04 21:02
 */
public class CollectionTest {

    @Test
    public void test1(){
        HashSet set = new HashSet();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");
        Person p3 = new Person(1001, "CC");

        set.add(p1);
        set.add(p2);
        System.out.println(set); // 两个元素 AA CC
        p1.name = "CC";
        set.remove(p1);
        System.out.println(set); // p1 remove失败，因为内容变了，hashCode和原先AA的索引位置不同

        set.add(p3);
        System.out.println(set); // p3 add成功，因为p1的hashCode是AA对应的hashCode，hashCode不同，添加成功

        set.add(new Person(1001, "AA"));
        System.out.println(set); // 添加成功，虽然hashCode和修改前的p1一致，但是内容已经不同

    }
}
