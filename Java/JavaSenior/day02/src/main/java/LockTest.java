import java.util.concurrent.locks.ReentrantLock;

/**
 * 解决线程安全问题方式三： Lock锁   JDK 1.5新增
 *
 * 1. 面试题： synchronized 与Lock的异同？
 *  相同：二者都可以解决线程安全问题
 *  不同：synchronized机制在执行完相应的同步代码以后，自动释放同步监视器
 *       Lock需要手动启动同步（lock()）,手动结束同步（unlock()
 *
 * 优先使用顺序
 * Lock-->同步代码块-->同步方法
 *
 * 2. 解决线程安全有几种方式？
 *    synchronized(同步代码块，同步方法）
 *    Lock
 *
 * @author c1rew
 * @create 2020-07-11 13:30
 */
class Window implements Runnable {

    private int ticket = 100;

    // 1. 实例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();

    public void run() {
        while (true) {
            try {
                // 2. lock
                lock.lock();
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
            }finally {
                // 3. unlock
                lock.unlock();
            }

        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();

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
