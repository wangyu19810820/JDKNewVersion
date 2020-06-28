package juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                demo.set((int)(Math.random() * 100));
            }).start();
        }

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                demo.get();
            }).start();
        }
    }
}

class ReadWriteLockDemo {
    private int number = 0;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void get() {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + number);
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void set(int number) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println("write:" + number);
            this.number = number;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
