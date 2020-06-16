/**
 * 创建多线程方式一
 * 1. 创建一个继承于Thread的子类
 * 2. 重写run()方法
 * 3. 创建Thread子类对象
 * 4. 调用对象start方法
 *
 * <p>
 * 遍历100以内所有偶数
 *
 * @author c1rew
 * @create 2020-06-16 23:50
 */

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();

        // 1. 启动线程，2. 调用线程的run方法
        t1.start();

        // Q1:以下调用没有启动线程，只是调用方法
        //t1.run();
        
        // Q2: 已经start的线程不能再start
        // t1.start()

        // 重新创建一个线程对象
        MyThread t2 = new MyThread();
        t2.start();


        System.out.println(Thread.currentThread().getName() + ": hello");
    }

}
