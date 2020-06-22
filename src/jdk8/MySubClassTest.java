package jdk8;

import org.junit.Test;

public class MySubClassTest {

    @Test
    public void test1() {
        MySubClass mySubClass = new MySubClass();
        System.out.println(mySubClass.getName());
    }
}
