import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程方式三：实现Callable接口
 *
 * Callable比Runnable强大在哪
 * 1. call 可以有返回值
 * 2. call 可以抛出异常
 * 3. Callable支持泛型
 *
 * @author c1rew
 * @date 2020-07-12 15:23
 */

class NumberThread implements Callable {

    public Object call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}

public class CallableThread {
    public static void main(String[] args) {
        NumberThread numThread = new NumberThread();
        FutureTask futureTask = new FutureTask(numThread);
        new Thread(futureTask).start();

        try {
            System.out.println("sum:" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
