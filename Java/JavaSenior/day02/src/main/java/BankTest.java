/**
 * 同步机制将单例模式中的懒汉式改为线程安全的
 *
 * @author c1rew
 * @create 2020-07-10 23:34
 */
public class BankTest {
}

class Bank {
    private Bank() {
    }

    private static Bank instance = null;

    public static Bank getInstance() {
        // 方式一：效率稍差
//        synchronized (Bank.class) {
//            if (instance == null) {
//                instance = new Bank();
//            }
//            return instance;
//        }

        // 方式二： 效率较高，后来的线程不需要再进入同步代码块
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}