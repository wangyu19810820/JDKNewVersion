package jdk13;

import org.junit.Test;

public class SwitchYieldTest {

    /**
     * yield用于返回switch的值
     */
    @Test
    public void testYield1() {
        String x = "3";
        var i = switch (x) {
            case "1" -> 5;
            case "2" -> 6;
            default -> {
                yield 7;
            }
        };
        System.out.println(i);
    }

    /**
     * yield也可以用于老式的switch中
     */
    @Test
    public void testYield2() {
        String x = "2";
        var i = switch (x) {
            case "1" : yield 5;
            case "2" : yield 6;
            default : yield 7;
        };
        System.out.println(i);
    }
}
