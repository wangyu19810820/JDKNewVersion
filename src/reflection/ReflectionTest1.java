package reflection;

import org.junit.Test;

import java.lang.annotation.ElementType;
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

    /**
     * 获取Class对象的四种方式
     */
    @Test
    public void test2() throws ClassNotFoundException {
        // 通过运行时类的属性.class
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1);

        // 通过运行时类的对象,调用getClass方法
         Person person = new Person();
         Class<Person> clazz2 = (Class<Person>)person.getClass();
         System.out.println(clazz2);

        // 通过Class的静态方法forName(String)
        Class<Person> clazz3 = (Class<Person>)Class.forName("reflection.Person");
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);

        // 通过ClassLoader的loadClass(String)方法获取
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<Person> clazz4 = (Class<Person>)classLoader.loadClass("reflection.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz4);
    }

    /**
     * Class实例涵盖的结构
     */
    @Test
    public void test3() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        // 数组的类型和维度相同，它们的Class实例也相同
        int[] a = new int[10];
        int[] b = new int[100];
        System.out.println(a.getClass() == b.getClass());
    }
}
