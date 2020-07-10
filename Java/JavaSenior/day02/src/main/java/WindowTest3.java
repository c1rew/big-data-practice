/**
 * 使用同步方法就解决实现Runnable接口线程安全问题
 *
 * @author c1rew
 * @create 2020-07-10 23:04
 */
class Window3 implements Runnable {

    private int ticket = 100;

    private synchronized void show() { // 同步监视器 this
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": sell ticket, ticket number: " + ticket--);
        }

    }

    public void run() {
        while (true) {
            show();
        }
    }
}

public class WindowTest3 {
    public static void main(String[] args) {
        Window3 w = new Window3();

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
