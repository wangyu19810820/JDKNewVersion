package jdk8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinCalculateTest {

    @Test
    public void test1() {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 100_0000_0000L);
        Long sum = pool.invoke(task);

        Instant end = Instant.now();

        System.out.println(sum);
        System.out.println(Duration.between(start, end).toMillis());
    }

    @Test
    public void test2() {
        Instant start = Instant.now();
        long sum = 0;
        for (long i = 0; i <= 100_0000_0000L; i++) {
            sum += i;
        }
        Instant end = Instant.now();

        System.out.println(sum);
        System.out.println(Duration.between(start, end).toMillis());
    }

    @Test
    public void test3() {
        Instant start = Instant.now();
        long sum = LongStream.rangeClosed(0, 100_0000_0000L)
                .parallel()
                .reduce(0, Long::sum);
        Instant end = Instant.now();

        System.out.println(sum);
        System.out.println(Duration.between(start, end).toMillis());
    }
}
