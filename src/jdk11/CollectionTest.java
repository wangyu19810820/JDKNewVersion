package jdk11;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class CollectionTest {

    @Test
    public void testSetOf() {
        Set<Integer> set = Set.of(100, 20, 30);
        // 抛异常IllegalArgumentException: duplicate element: 100
        // Set.of(100, 20, 100);
        System.out.println(set);
    }

    @Test
    public void testListOf() {
        List<String> list1 = Arrays.asList("aa", "bb", "cc");
        // 抛异常UnsupportedOperationException
        // list1.add("dd");
        System.out.println(list1);

        List<String> list2 = List.of("aa", "bb", "cc");
        // 抛异常UnsupportedOperationException
        // list2.add("dd");
        System.out.println(list2);
    }
}
