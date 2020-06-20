package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaTest4 {

    @Test
    public void test1() {
        happy(1000, c -> System.out.println("每次消费" + c + "元"));
    }

    @Test
    public void test2() {
        List list = getNumList(5, () -> (int) (Math.random() * 100));
        System.out.println(list);
    }

    @Test
    public void test3() {
        System.out.println(strHandler("abcdefg", s -> s.substring(2, 5)));
    }

    @Test
    public void test4() {
        List<String> list = List.of("abcdefg", "hello", "lambda", "ok", "www");
        List<String> result = filter(list, s -> s.length() > 3);
        System.out.println(result);
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    public String strHandler(String s, Function<String, String> function) {
        return function.apply(s);
    }

    public List<String> filter(List<String> list, Predicate<String> predicate) {
        List<String> result = new ArrayList<>();
        for(String str : list) {
            if (predicate.test(str)) {
                result.add(str);
            }
        }
        return result;
    }
}
