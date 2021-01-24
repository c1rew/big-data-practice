package com.viper.designpattern.template;

/**
 * 无模板方法的实现
 * @author c1rew
 * @date 2021-01-24 14:21
 */

public class WithoutTemplateMethodPattern {

    public static void main(String[] args) {
        DiscountCalculator1 calculator1 = new DiscountCalculator1();
        calculator1.calculate();

        DiscountCalculator2 calculator2 = new DiscountCalculator2();
        calculator2.calculate();

        DiscountCalculator3 calculator3 = new DiscountCalculator3();
        calculator3.calculate();

        /**
         * 问题：通用方法分散在多个地方，需要修改时需要修改多个地方，且后期无法保证每个地方都修改到了
         */
    }

    public static class DiscountCalculator1 {
        public void calculate() {
            System.out.println("通用计算方法");
            System.out.println("特殊计算方法 1...");
        }
    }

    public static class DiscountCalculator2 {
        public void calculate() {
            System.out.println("通用计算方法");
            System.out.println("特殊计算方法 2...");
        }
    }

    public static class DiscountCalculator3 {
        public void calculate() {
            System.out.println("通用计算方法");
            System.out.println("特殊计算方法 3...");
        }
    }
}
