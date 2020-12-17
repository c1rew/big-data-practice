/**
 * 通过实现Runnable接口来创建线程
 *
 *
 * 两种创建线程方式对比
 * 开发中，优先选择：实现Runnable接口
 * 原因：1.实现方式没有类的单继承局限性
 *      2.实现方式更适合处理多个线程有共享数据的情况
 *
 * 联系：public class Thread implements Runnable
 * 相同点：都需要重写run，将线程要执行的逻辑声明在run中
 *
 * @author c1rew
 * @create 2020-07-07 22:28
 */
class MThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadTest1 {
    public static void main(String[] args) {
        MThread mThread = new MThread();

        Thread t1 = new Thread(mThread);
        t1.start();
    }
}
