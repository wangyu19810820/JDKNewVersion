package jdk12;

import org.junit.Test;

import java.util.Optional;

public class StringConstantTest {

    @Test
    public void testDescribeConstable() {
        String name = "wangyu";
        Optional<String> optional = name.describeConstable();
        System.out.println(optional.get());
    }
}
