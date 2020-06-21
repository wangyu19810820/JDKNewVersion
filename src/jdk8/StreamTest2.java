package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest2 {

    private List<Employee> employeeList = new ArrayList<>(List.of(
            new Employee("张三", 18, 9999),
            new Employee("张三", 18, 9999),
            new Employee("赵六", 18, 7777),
            new Employee("李四", 44, 5555),
            new Employee("王五", 8, 4444),
            new Employee("田七", 65, 6666),
            new Employee("田七", 65, 6666),
            new Employee("田七", 65, 6666)));

    @Test
    public void test1() {
        Stream<Employee> stream = employeeList.stream().filter(employee -> {
            System.out.println("中间操作");
            return employee.age() > 35;
        });
        stream.forEach(System.out::println);
    }

    @Test
    public void test3() {
        Stream<Employee> stream = employeeList.stream()
                                      .filter(employee -> {
                                          System.out.println("短路");
                                          return employee.salary() > 5000;
                                      })
                                      .limit(2);
        stream.forEach(System.out::println);
    }

    @Test
    public void test4() {
        employeeList.stream()
                .filter(employee -> {
                    System.out.println("没有短路，先过滤，后skip");
                    return employee.salary() > 5000;
                })
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        employeeList.stream()
                .filter(employee -> {
                    System.out.println("没有短路，先过滤，后skip,后去重复");
                    return employee.salary() > 5000;
                })
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test6() {
        List<String> list1 = List.of("aaa", "bbb", "ccc");
        list1.stream().map(String::toUpperCase).forEach(System.out::println);
        employeeList.stream().map(Employee::name).forEach(System.out::println);
        list1.stream().flatMap(StreamTest2::stringToCharacter).forEach(System.out::println);
    }

    @Test
    public void test7() {
        List<String> list1 = List.of("aaa", "bbb", "ccc");
        list1.stream().sorted().forEach(System.out::println);
        employeeList.stream().sorted((e1, e2) -> e1.age() == e2.age() ? e1.name().compareTo(e2.name()) : Integer.compare(e1.age(), e2.age()))
                .forEach(System.out::println);
    }

    public static Stream<Character> stringToCharacter(String str) {
        char[] chars = str.toCharArray();
        List<Character> list1 = new ArrayList<>();
        for(char c : chars) {
            list1.add(c);
        }
        return list1.stream();
    }
}
