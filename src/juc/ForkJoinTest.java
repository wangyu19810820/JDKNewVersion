package juc;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinTest {

    /**
     * forkjoin框架，执行求和运算
     * @param args
     */
    public static void main(String[] args) {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 100_0000_0000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());//314-6263
    }

    /**
     * 单线程
     */
    @Test
    public void calc() {
        Instant start = Instant.now();

        long sum = 0;
        for (long i = 0L; i <= 100_0000_0000L; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());//153-7108
    }

    /**
     * java8流，执行相同计算
     */
    @Test
    public void calc1() {
        Instant start = Instant.now();

        long sum = LongStream.rangeClosed(0L, 100_0000_0000L)
                .parallel()
                .reduce(0L, Long::sum);
        System.out.println(sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());//80-4726
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD = 10000L;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);

            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}
