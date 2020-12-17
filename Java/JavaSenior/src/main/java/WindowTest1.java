/**
 * @author c1rew
 * @create 2020-07-07 22:44
 */
class Window1 implements Runnable {

    // 三个线程使用的同一个w对象，此时不需要声明static也不会有问题
    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ": sell ticket, ticket number: " + ticket--);
            } else {
                break;
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
