package jdk8;

import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTest1 {

    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(02, 01);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(com);
        treeSet.addAll(Set.of(1, 2, 3));
        System.out.println(treeSet);
    }

    @Test
    public void test2() {
        Comparator<Integer> com = (o1, o2) -> Integer.compare(o2, o1);
        TreeSet<Integer> treeSet = new TreeSet<>(com);
        treeSet.addAll(Set.of(1, 2, 3));
        System.out.println(treeSet);
    }

    private List<Employee> list = List.of(new Employee("张三", 18, 9999),
            new Employee("李四", 38, 5555),
            new Employee("王五", 8, 4444),
            new Employee("赵六", 45, 7777),
            new Employee("田七", 65, 6666));

    public static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for(T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    @Test
    public void test3() {
        List<Employee> result = filter(list, employee -> employee.age() > 35);
        System.out.println(result);
    }

    @Test
    public void test4() {
        List<Employee> result = filter(list, employee -> employee.salary() > 6000);
        System.out.println(result);
    }

    @Test
    public void test5() {
        List<Employee> result = list.stream()
                                    .filter(employee -> employee.salary() < 7000)
                                    .collect(Collectors.toList());
        System.out.println(result);

        list.stream().map(Employee::name).forEach(System.out::println);
    }
}
