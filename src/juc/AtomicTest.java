package juc;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    public static void main(String[] args) {
        AtomRunnable r = new AtomRunnable();
        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }
    }
}

/**
 * i++不是原子性操作，换为AtomicInteger保证原子性
 */
class AtomRunnable implements Runnable {

//    private int serialNumber = 0;
    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }

    public int getSerialNumber() {
//        return serialNumber++;
        return serialNumber.getAndIncrement();
    }
}