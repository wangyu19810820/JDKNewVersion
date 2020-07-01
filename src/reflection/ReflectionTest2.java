package reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest2 {

    @Test
    public void testField1() throws Exception {
        Class<Person> clazz = Person.class;
        Person person = clazz.getConstructor().newInstance();
        Field age = clazz.getField("age");
        age.set(person, 18);
        System.out.println(age.get(person));
        System.out.println(person);
    }

    @Test
    public void testField2() throws Exception {
        Class<Person> clazz = Person.class;
        Person person = clazz.getConstructor().newInstance();
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "wangyu");
        System.out.println(name.get(person));
    }
    
    @Test
    public void testMethod() throws Exception {
        Class<Person> clazz = Person.class;
        Person person = clazz.getConstructor().newInstance();
        Method showNation = clazz.getDeclaredMethod("showNation", String.class, String[].class);
        showNation.setAccessible(true);
        String nation = (String)showNation.invoke(person, "japan", new String[]{"english", "chinese"});
        System.out.println(nation);

        Method display = clazz.getDeclaredMethod("display");
        Object returnVal = display.invoke(null);
        System.out.println(returnVal);
    }

    @Test
    public void testConstructor() throws Exception {
        Class<Person> clazz = Person.class;
        Constructor<Person> constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person person = constructor.newInstance("wangyu");
        System.out.println(person);
    }
}
