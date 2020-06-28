package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        CallableDemo callableDemo = new CallableDemo();
        FutureTask<Long> future = new FutureTask<>(callableDemo);
        new Thread(future).start();

        try {
            long sum = future.get();
            System.out.println(sum);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class CallableDemo implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (int i = 0; i < 2_000_000_000; i++) {
            sum += i;
        }
        return sum;
    }
}
