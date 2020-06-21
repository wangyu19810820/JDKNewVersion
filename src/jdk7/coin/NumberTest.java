package jdk7.coin;

import org.junit.Test;

public class NumberTest {

    /**
     * 用0b或0B开头的数字，直接表示二进制整数
     */
    @Test
    public void test1() {
        int x = Integer.parseInt("1100110", 2);
        System.out.println(x);
        int y = 0B1100110;
        int z = 0b1100110;
        System.out.println(x == y);
        System.out.println(z);
    }

    /**
     * 大数字用_分隔，增加可读性
     */
    @Test
    public void test2() {
        Long num = 1_000L;
        System.out.println(num);
    }
}
