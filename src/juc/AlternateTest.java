package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印线程名，比如有三个线程A、B、C，打印：ABCABCABCABCABC
 */
public class AlternateTest {
    public static void main(String[] args) {
        Alternate alternate = new Alternate();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                alternate.loopA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                alternate.loopB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                alternate.loopC();
            }
        }, "C").start();
    }
}

class Alternate {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            System.out.print(Thread.currentThread().getName());
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
