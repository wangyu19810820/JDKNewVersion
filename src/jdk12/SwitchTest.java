package jdk12;

import org.junit.Test;

public class SwitchTest {

    /**
     * switch表达式，不需要break，且一行可以匹配多个值，用逗号分隔。
     */
    @Test
    public void testSwitch1() {
        Fruit fruit = Fruit.APPLE;
        switch (fruit) {
            case PEAR -> System.out.println(4);
            case APPLE, GRAPE, MANGO -> System.out.println(5);
            case ORANGE -> System.out.println(6);
            case PAPAYA -> System.out.println(7);
            default -> throw new IllegalStateException("No such fruit" + fruit);
        }
    }

    /**
     * switch表达式可以表示成一个值
     */
    @Test
    public void testSwitch2() {
        Fruit fruit = Fruit.APPLE;
        int number;
        number = switch (fruit) {
            case PEAR -> 4;
            case APPLE, GRAPE, MANGO -> 5;
            case ORANGE -> 6;
            case PAPAYA -> 7;
            default -> throw new IllegalStateException("No such fruit" + fruit);
        };
        System.out.println(number);
    }

}

enum Fruit {
    PEAR, APPLE, GRAPE, MANGO, ORANGE, PAPAYA;
}
