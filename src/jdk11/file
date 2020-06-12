package jdk11;

import org.junit.Test;

import java.util.stream.Stream;

public class StreamTest {

    /**
     * 迭代生成器，可以判定迭代条件，条件不满足时，终止生成新元素
     */
    @Test
    public void testIterate() {
        Stream.iterate(1, i -> i < 1000, i -> i * 2 + 1)
                .forEach(System.out::println);
    }

    /**
     * 从流中丢弃判定为真的元素，直到判定为假，终止处理
     */
    @Test
    public void testDropWhile() {
        Stream<Integer> stream = Stream.of(3, 9, 20, 40, 7);
        Stream<Integer> stream1 = stream.dropWhile(t -> t % 2 != 0);
        stream1.forEach(System.out::println);
    }

    /**
     * 从流中获取判定器为真的元素，一旦判定为假，终止处理。返回新流。
     */
    @Test
    public void testTakeWhile() {
        Stream<Integer> stream = Stream.of(3, 9, 20, 40, 7);
        Stream<Integer> stream1 = stream.takeWhile(t -> t % 2 != 0);
        stream1.forEach(System.out::println);
    }

    /**
     * 可接收null参数的流
     */
    @Test
    public void testOfNullable() {
        // 抛空指针异常
        // Stream<Object> stream = Stream.of(null);
        Stream<Object> stream = Stream.ofNullable(null);
    }
}
