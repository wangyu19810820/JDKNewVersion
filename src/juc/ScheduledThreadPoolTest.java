package juc;

import java.util.concurrent.*;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.schedule(() -> {
                int r = (int)(Math.random() * 100);
                System.out.println(Thread.currentThread().getName() + ":" + r);
                return r;
            }, 1, TimeUnit.SECONDS);
            System.out.println(future.get());
        }
        pool.shutdown();
    }
}
