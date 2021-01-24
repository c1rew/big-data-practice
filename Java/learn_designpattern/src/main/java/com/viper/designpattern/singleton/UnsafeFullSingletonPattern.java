package com.viper.designpattern.singleton;

/**
 * 线程不安全的饱汉模式
 *
 * @author c1rew
 * @create 2021-01-24 21:41
 */
public class UnsafeFullSingletonPattern {

    public static class Singleton {
        private static Singleton instance;

        private Singleton() {

        }

        public static Singleton getInstance() {
            if (instance == null) {
                // 多线程在这里切换cpu时间片，可能导致 instance 非单实例
                instance = new Singleton();
            }
            return instance;
        }
    }
}
