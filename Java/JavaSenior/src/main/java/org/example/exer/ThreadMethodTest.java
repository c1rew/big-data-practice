package org.example.exer;

/**
 * Thread中的常用方法
 * 1. start，启动线程并执行run方法
 * 2. run 通常需要重写该方法，线程要自行的操作在该方法中
 * 3. currentThread 静态方法，返回执行当前代码的线程
 * 4. setName 设置当前线程名
 * 5. getName 获取当前线程名
 * 6. yield 释放当前cpu的执行权
 * 7. join 在线程a中调用线程b的join，此时a线程阻塞，直到b执行完以后，a才结束阻塞状态
 * 8. stop 已过时，结束当前线程
 * 9. sleep(long millitime) 线程睡眠（毫秒），指定时间内，线程阻塞
 *
 * @author c1rew
 * @create 2020-07-01 22:37
 */
class HelloThread extends Thread {
    public HelloThread(String s) {
        super(s);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }

//            if (i % 20 == 0) {
//                yield();
//            }
        }
    }
}


public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread h1 = new HelloThread("Thread: 1");

        h1.start();

        Thread.currentThread().setName("main thread");

        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }

            if (i==20) {
                try {
                    h1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(h1.isAlive());
    }


}
