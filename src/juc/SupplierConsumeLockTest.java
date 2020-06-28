package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SupplierConsumeLockTest {
    public static void main(String[] args) {
        Shop1 shop = new Shop1();
        Supplier1 supplier = new Supplier1(shop);
        Consume1 consume = new Consume1(shop);

        new Thread(supplier, "生产者A").start();
        new Thread(supplier, "生产者B").start();
        new Thread(consume, "消费者C").start();
        new Thread(consume, "消费者D").start();
    }
}

class Shop1 {
    private int product = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void buy() {
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println("货满");
                try {
                    condition.await();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }

            product++;
            System.out.println(Thread.currentThread().getName() + "制造了货物，存货为：" + product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void sell() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("缺货");
                try {
                    condition.await();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }

            product--;
            System.out.println(Thread.currentThread().getName() + "消费了货物，存货为：" + product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class Supplier1 implements Runnable {

    private Shop1 shop = new Shop1();

    public Supplier1(Shop1 shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            shop.buy();
        }
    }
}

class Consume1 implements Runnable {

    private Shop1 shop = new Shop1();

    public Consume1(Shop1 shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            shop.sell();
        }
    }
}