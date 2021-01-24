package com.viper.designpattern.singleton;

/**
 * @author c1rew
 * @create 2021-01-24 21:41
 */
public class SafeFullSingletonPattern {

    public static class Singleton {
        private static Singleton instance;

        private Singleton() {

        }

        public static Singleton getInstance() {
            // double check 通过加锁实现安全的单实例
            // 不是完美的，因为不同JVM编译器问题，可能还是线程不安全的
            // 基于内部类实现才是真正安全的。
            if (instance == null) {
                synchronized (SafeFullSingletonPattern.class) {
                    if (instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
