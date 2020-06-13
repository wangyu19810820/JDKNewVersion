package jdk9;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class DiamondOperatorTest {

    @Test
    public void test() {
        // 钻石操作符可以应用在匿名内部类上
        Set<String> set = new HashSet<>(){};
        set.add("aa");
        System.out.println(set);
    }
}
