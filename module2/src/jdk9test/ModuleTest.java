package jdk9test;

import jdk9demo.Person;
import org.junit.Test;

import java.util.logging.Logger;

public class ModuleTest {

    public static void main(String[] args) {
        Person p = new Person("aaa", 18);
        System.out.println(p);

        Logger logger = Logger.getLogger("jdk9test");
        logger.info("haha");
    }

    @Test
    public void test() {
        Person p = new Person("aaa", 18);
        System.out.println(p);
    }
}
