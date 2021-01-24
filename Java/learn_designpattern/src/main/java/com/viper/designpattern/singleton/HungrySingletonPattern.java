package com.viper.designpattern.singleton;

/**
 * 饿汉模式
 *
 * @author c1rew
 * @create 2021-01-24 21:34
 */
public class HungrySingletonPattern {

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.run();
    }

    public static class Singleton {

        /**
         * 1. 创建实例，用static final修饰
         * 2. 构造函数私有化
         * 3. 一个static方法，返回唯一一个实例
         */

        private static final Singleton instance = new Singleton();

        private Singleton() {

        }

        public static Singleton getInstance() {
            return instance;
        }

        public void run() {
            System.out.println("singleton method...");
        }
    }

}
