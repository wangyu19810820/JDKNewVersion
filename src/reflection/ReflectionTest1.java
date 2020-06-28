package reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest1 {

    @Test
    public void test1() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchFieldException {
        // 通过反射，构建Person对象
        Class<Person> clazz = Person.class;
        Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);
        Person person = constructor.newInstance("Tom", 12);
        System.out.println(person);
        // 通过反射调用属性和方法
        Field age = clazz.getField("age");
        age.set(person, 10);
        System.out.println(person);
        Method show = clazz.getMethod("show");
        show.invoke(person);

        // 通过反射，调用Person的私有结构
        // 私有构造器
        Constructor<Person> constructor1 = clazz.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        Person person1 = constructor1.newInstance("Jerry");
        System.out.println(person1);
        // 私有字段
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person1, "Tom");
        System.out.println(person1);
        // 私有方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String)showNation.invoke(person1, "china");
        System.out.println(nation);
    }
}
