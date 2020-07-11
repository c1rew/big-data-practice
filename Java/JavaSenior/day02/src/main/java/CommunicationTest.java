/**
 * 1-100，两个线程交替打印
 *
 * 涉及到三个方法：
 * wait():  一旦执行此方法，当前线程进入阻塞状态，并释放同步监视器
 * notify(): 一旦执行此方法，就会唤醒被wait的一个线程，如有多个线程wait，则唤醒优先级高的
 * notifyAll(): 唤醒所有被wait的线程
 *
 *
 * 说明：
 * 1. wait, notify,notifyAll 三个方法必须使用在同步代码块或者同步方法中
 * 2. wait, notify,notifyAll 三个方法的调用者必须是同步代码块或者同步方法中对应的同步监视器
 * 否则，会出现IllegalMonitorStateException异常
 * 3. wait, notify,notifyAll 三个方法是定义在java.lang.Object类中的
 *
 * @author c1rew
 * @create 2020-07-11 21:55
 */

class Number implements Runnable {

    private int number = 1;

    private Object obj = new Object();

    public void run() {
        while (true) {
            //synchronized (obj) { //如果用obj，以下的wati和notify也必须是obj调用
            synchronized (this) {

                notify();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (number <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + number++);
                } else {
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();

        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.start();
        t2.start();
    }
}
