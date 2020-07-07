/**
 * 创建三个窗口卖票，总票数为100张
 *
 * @author c1rew
 * @create 2020-07-01 23:17
 */

class Window extends Thread {

    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket>0) {
                System.out.println(getName() + ": sell ticket, ticket number: "+ticket--);
            } else {
                break;
            }
        }

    }
}

public class WindowTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        w1.setName("w1");
        Window w2 = new Window();
        w2.setName("w2");
        Window w3 = new Window();
        w3.setName("w3");

        w1.start();
        w2.start();
        w3.start();
    }
}
