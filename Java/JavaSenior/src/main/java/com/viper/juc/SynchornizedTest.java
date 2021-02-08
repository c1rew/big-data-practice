package com.viper.juc;

/**
 * @author c1rew
 * @create 2021-02-07 06:52
 */
public class SynchornizedTest {
    public static void main(String[] args) {
        Ticket1 ticket = new Ticket1();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

class Ticket1 {
    private int number = 80;

    // synchronized 本质：队列，锁
    public synchronized void sale() {
        if (number > 0) {
            number--;
            System.out.println(Thread.currentThread().getName() + " 剩余票数：" + number);
        }
    }
}
