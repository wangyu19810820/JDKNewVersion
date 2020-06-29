package reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class ConstructorTest {

    @Test
    public void test1() {
        Class<Person> clazz = Person.class;
        // 获取运行时类的public构造器
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        System.out.println("----------------------------------------------");

        // 获取运行时类声明的所有构造器
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : declaredConstructors) {
            System.out.println(constructor);
        }
    }
}
