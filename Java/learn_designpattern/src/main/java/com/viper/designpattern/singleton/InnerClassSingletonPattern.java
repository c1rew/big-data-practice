package com.viper.designpattern.singleton;

/**
 * 基于内部类的单实例
 *
 * @author c1rew
 * @create 2021-01-24 21:41
 */
public class InnerClassSingletonPattern {
    /**
     * 可以做到饱汉
     * 内部类，只要没有被使用，就不会初始化，Singleton实例不会创建
     * 第一次调用，内部类初始化，创建唯一一个实例
     * java能确保，类静态初始化过程一定只会执行一次
     */
    public static class Singleton {
        private Singleton() {

        }

        public static class InnerHolder {
            public static final Singleton instance = new Singleton();
        }

        public static Singleton getInstance() {
            return InnerHolder.instance;
        }
    }
}
