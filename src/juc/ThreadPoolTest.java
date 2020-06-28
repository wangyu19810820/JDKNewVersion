package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
//        ThreadPoolDemo r = new ThreadPoolDemo();
//        for (int i = 0; i < 10; i++) {
//            pool.submit(r);
//        }

        List<Future<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 0; i <= 100; i++) {
                        sum += i;
                    }
                    return sum;
                }
            });
            list.add(future);
        }
        pool.shutdown();

        try {
            for (Future<Integer> future : list) {
                Integer sum = future.get();
                System.out.println(sum);
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class ThreadPoolDemo implements Runnable {

    private int num = 0;

    @Override
    public void run() {
        while (num < 100) {
            System.out.println(Thread.currentThread().getName() + ":" + num++);
        }
    }
}