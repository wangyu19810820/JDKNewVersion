package jdk7.coin;

import java.util.ArrayList;
import java.util.Collection;

public class SafeVarargsTest {

    public static void main(String[] args) {
        doSomething("aa", "bb");
    }

    /*
     * 抑制警告
     */
    @SafeVarargs
    public static <T> Collection<T> doSomething(T... args) {
        return new ArrayList<T>();
    }
}
