/**
 * 创建三个窗口卖票，总票数为100张
 *
 * @author c1rew
 * @create 2020-07-01 23:17
 */

class Window2 extends Thread {

    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            
            if (ticket > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(getName() + ": sell ticket, ticket number: " + ticket--);
            } else {
                break;
            }
        }

    }
}

public class WindowTest2 {
    public static void main(String[] args) {
        Window2 w1 = new Window2();
        w1.setName("w1");
        Window2 w2 = new Window2();
        w2.setName("w2");
        Window2 w3 = new Window2();
        w3.setName("w3");

        w1.start();
        w2.start();
        w3.start();
    }
}
