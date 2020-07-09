/**
 * 1. 重票、错票的问题
 * 2. 原因：同一个变量同时被多个线程操作
 * 3. 解决：通过同步机制，解决线程安全问题
 * <p>
 * 方式一：同步代码块
 * <p>
 * synchronized(同步监视器 俗称 锁 任何一个类的对象都可以充当锁) {
 * // 需要被同步的代码（操作共享数据的代码）
 * }
 * 要求：多个线程必须要共用同一把锁
 * <p>
 * 方式二：同步方法
 *
 * @author c1rew
 * @create 2020-07-07 22:44
 */
class Window1 implements Runnable {

    // 三个线程使用的同一个w对象，此时不需要声明static也不会有问题
    private int ticket = 100;
    private Object obj = new Object();

    public void run() {
        while (true) {
             //synchronized (new Object()) { //错误
            synchronized (obj) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": sell ticket, ticket number: " + ticket--);
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");

        t1.start();
        t2.start();
        t3.start();
    }
}
