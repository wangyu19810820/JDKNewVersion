package thread;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        Window1 w = new Window1();

        Thread t1 = new Thread(w);
        t1.setName("窗口1");
        Thread t2 = new Thread(w);
        t2.setName("窗口2");
        Thread t3 = new Thread(w);
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Window1 implements Runnable {

    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();   // 实例化锁

    @Override
    public void run() {
        while (true) {
            lock.lock();    // 锁定
            try {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出了票，票号：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                lock.unlock();  // 解锁
            }
        }
    }
}