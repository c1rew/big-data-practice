/**
 * 使用同步方法解决继承Thread的方法中的线程安全问题
 *
 * @author c1rew
 * @create 2020-07-01 23:17
 */

class Window4 extends Thread {

    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    private static synchronized void show() { // 同步监视器 Window4.class
    //private synchronized void show() { // 错误，同步监视器 t1,t2,t3
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": sell ticket, ticket number: " + ticket--);
        }
    }
}

public class WindowTest4 {
    public static void main(String[] args) {
        Window4 w1 = new Window4();
        w1.setName("w1");
        Window4 w2 = new Window4();
        w2.setName("w2");
        Window4 w3 = new Window4();
        w3.setName("w3");

        w1.start();
        w2.start();
        w3.start();
    }
}
