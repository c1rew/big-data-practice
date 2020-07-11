/**
 * 货架最多只能放20个产品
 *
 *
 * @author c1rew
 * @create 2020-07-11 23:39
 */
class Clerk {

    private int productNum = 0;

    public synchronized void product() {
        if (productNum < 20) {
            productNum++;
            System.out.println(Thread.currentThread().getName() + ": product " + productNum);
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void consume() {
        if (productNum > 0) {

            System.out.println(Thread.currentThread().getName() + ": consume " + productNum);
            productNum--;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {

    private Clerk clerk = null;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.product();
        }
    }
}

class Consumer extends Thread {

    private Clerk clerk = null;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consume();
        }
    }
}


public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Producer producer = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);
        Consumer c2 = new Consumer(clerk);

        producer.setName("p1");
        c1.setName("c1");
        c2.setName("c2");

        producer.start();
        c1.start();
        c2.start();
    }
}
