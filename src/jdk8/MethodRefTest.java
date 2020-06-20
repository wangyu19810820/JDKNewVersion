package jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

public class MethodRefTest {

    @Test
    public void test1() {
        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Consumer<String> consumer2 = x -> System.out.println(x);
        Consumer<String> consumer3 = System.out::println;
        consumer2.accept("aaaa");
    }

    @Test
    public void test2() {
        Employee employee = new Employee("wy", 38, 3000);
        Supplier<String> supplier1 = () -> employee.name();
        Supplier<String> supplier2 = employee::name;
        System.out.println(supplier2.get());
    }

    @Test
    public void test3() {
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator2 = Integer::compare;
        System.out.println(comparator2.compare(1, 2));
    }

    /**
     * 若Lambda表达式第一个参数是Lambda体的隐式参数，第二个参数是Lambda体的显式参数，可以使用className::method
     */
    @Test
    public void test4() {
        BiPredicate<String, String> biPredicate1 = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate2 = String::equals;
        System.out.println(biPredicate2.test("aa", "aa"));
    }

    /**
     * 构造器引用，注意lambda参数类型和构造器参数类型相同
     */
    @Test
    public void test5() {
        Supplier<Employee> supplier = Employee::new;
        Function<String, Employee> function = Employee::new;
        System.out.println(function.apply("wy"));

        BiFunction<String, Integer, Employee> biFunction = Employee::new;
        System.out.println(biFunction.apply("wy", 38));
    }

    /**
     * 数组引用，Type[]::new对应Function接口
     */
    @Test
    public void test6() {
        Function<Integer, String[]> function1 = x -> new String[x];
        Function<Integer, String[]> function2 = String[]::new;
        String[] array = function2.apply(5);
        System.out.println(Arrays.toString(array));
    }
}
