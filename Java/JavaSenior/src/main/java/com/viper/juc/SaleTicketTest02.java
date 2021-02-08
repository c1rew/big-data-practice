package com.viper.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author c1rew
 * @create 2021-02-07 06:52
 */
public class SaleTicketTest02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i < 40; i++) ticket.sale();
        }, "C").start();
    }
}


/**
 * Lock三部
 * 1. new ReentrantLock()
 * 2. lock
 * 3. unlock
 */
class Ticket2 {
    private int number = 80;

    // 默认非公平锁，可以插队
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();

        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + " 剩余票数：" + number--);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
