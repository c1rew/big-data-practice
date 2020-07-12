import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.sleep;

/**
 * 使用volatile变量去停止线程，并不一定能停下
 *
 * @author c1rew
 * @create 2020-07-12 13:34
 */

class Producer1 implements Runnable {

    public volatile boolean isCancel = false;

    BlockingQueue queue;

    public Producer1(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        int count = 0;
        try {
            while (true) {
                if (count <= 10000 && !isCancel) {
                    if (count % 50 == 0) {
                        queue.put(count);    // 队列满了后，这句就阻塞了
                        System.out.println("put goods to storage...");
                    }
                    count++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("producer over...");
        }
    }
}

class Consumer1 {
    BlockingQueue queue;

    public Consumer1(BlockingQueue queue) {
        this.queue = queue;
    }

    public boolean needMore() {
        if (Math.random() > 0.97) {
            return false;
        }
        return true;
    }

}


public class VolatileCannotStopThread {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(8);

        Producer1 producer = new Producer1(storage);
        Thread thread = new Thread(producer);
        thread.start();

        sleep(500);

        Consumer1 consumer = new Consumer1(storage);
        while (consumer.needMore()) {
            storage.take();
            System.out.println("take goods from storage");
            sleep(100);
        }
        //producer.isCancel = true;   // 使用这种方法无法停止线程，生产者线程阻塞在put方法中，无法进入下一个循环
        //System.out.println(producer.isCancel);

        thread.interrupt();
    }
}
