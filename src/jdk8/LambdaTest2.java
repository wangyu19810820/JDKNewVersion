package jdk8;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class LambdaTest2 {

    @Test
    public void test1() {
        Runnable r = () -> System.out.println("Hello Lambda");
        Thread t = new Thread(r);
        t.start();
    }

    @Test
    public void test2() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Hello Lambda!");
    }

    @Test
    public void test3() {
        BiFunction<String, String, Integer> biFunction = (i, j) -> {
            return i.compareTo(j);
        };
        System.out.println(biFunction.apply("aa", "bb"));
    }

    @Test
    public void test4() {
        System.out.println(calc(100, x -> x * x));
        System.out.println(calc(200, x -> x + 20));
    }

    public Integer calc(Integer x, MyFunction myFunction) {
        return myFunction.accept(x);
    }
}
