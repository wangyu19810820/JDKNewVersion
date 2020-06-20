package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest3 {

    private List<Employee> list = new ArrayList<>(List.of(new Employee("张三", 18, 9999),
            new Employee("赵六", 18, 7777),
            new Employee("李四", 18, 5555),
            new Employee("王五", 8, 4444),
            new Employee("田七", 65, 6666)));

    @Test
    public void test1() {
        Collections.sort(list, Comparator.comparing(Employee::age).thenComparing(Employee::name));
        list.forEach(System.out::println);
    }

    @Test
    public void test2() {
        System.out.println(stringHandler("aBc", s -> s.toUpperCase()));
        System.out.println(stringHandler("abcdefghijklmn", s -> s.substring(2, 5)));
    }

    @Test
    public void test3() {
        op(123L, 456L, (l1, l2) -> l1 + l2);
        op(100L, 200L, (l1, l2) -> l1 * l2);
    }

    public String stringHandler(String s, MyFunction1 function1) {
        return function1.getValue(s);
    }

    public void op(long l1, long l2, MyFunction2<Long, Long> myFunction2) {
        System.out.println(myFunction2.getValue(l1, l2));
    }
}
