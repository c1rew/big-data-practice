package com.viper.designpattern.template;

/**
 * @author c1rew
 * @date 2021-01-24 14:22
 */

public class TemplateMethodPattern {

    public static void main(String[] args) {
        AbstractDiscountCalculator calculator1 = new DiscountCalculator1();
        calculator1.calculate();

        AbstractDiscountCalculator calculator2 = new DiscountCalculator2();
        calculator2.calculate();

        AbstractDiscountCalculator calculator3 = new DiscountCalculator3();
        calculator3.calculate();
    }

    /**
     * 计算接口
     */
    public interface DiscountCalculator {
        /**
         * 计算接口方法
         */
        void calculate();
    }

    public static abstract class AbstractDiscountCalculator implements DiscountCalculator {
        @Override
        public void calculate() {
            commonCalculate();
            specificCalculate();
        }

        private void commonCalculate() {
            System.out.println("通用计算方法修改");
        }

        /**
         * 特殊计算抽象接口
         */
        protected abstract void specificCalculate();
    }

    public static class DiscountCalculator1 extends AbstractDiscountCalculator {
        @Override
        protected void specificCalculate() {
            System.out.println("特殊计算方法 1...");
        }
    }

    public static class DiscountCalculator2 extends AbstractDiscountCalculator {
        @Override
        protected void specificCalculate() {
            System.out.println("特殊计算方法 2...");
        }
    }

    public static class DiscountCalculator3 extends AbstractDiscountCalculator {
        @Override
        protected void specificCalculate() {
            System.out.println("特殊计算方法 3...");
        }
    }
}
