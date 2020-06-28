package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLockRunnable r = new ReentrantLockRunnable();
        for (int i = 0; i < 3; i++) {
            new Thread(r, "窗口" + (i + 1)).start();
        }
    }

}

class ReentrantLockRunnable implements Runnable {
    private int ticket = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                if (ticket > 0) {
                    ticket--;
                    System.out.println(Thread.currentThread().getName() + "售出了一张票，余票为" + ticket);
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
