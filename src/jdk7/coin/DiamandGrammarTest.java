package jdk7.coin;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DiamandGrammarTest {

    /**
     * 泛型的类型推断，称为钻石语法
     */
    @Test
    public void test1() {
        Map<Integer, String> map = new HashMap<>();
    }
}
