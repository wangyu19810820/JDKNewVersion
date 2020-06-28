package juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：闭锁，等其他线程都执行完毕后，再执行本线程
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);
        CountDownLatchRunnable r = new CountDownLatchRunnable(latch);
        Instant start = Instant.now();

        for (int i = 0; i < 10; i++) {
            new Thread(r).start();
        }

        try {
            latch.await();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        Instant end = Instant.now();
        System.out.println("共耗时：" + Duration.between(start, end).toMillis() + "毫秒");
    }
}

class CountDownLatchRunnable implements Runnable {
    private CountDownLatch latch;
    public CountDownLatchRunnable(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } finally {
            latch.countDown();
        }
    }
}
