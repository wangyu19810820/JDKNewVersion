package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest1 {

    @Test
    public void test1() {
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        Stream<Integer> stream4 = Stream.iterate(1, x -> x + 1);
        stream4.limit(10).forEach(System.out::println);

        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(10).forEach(System.out::println);
    }
}
