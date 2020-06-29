package reflection;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class FieldTest {

    @Test
    public void test1() {
        Class clazz = Person.class;
        // 运行时类和父类的public属性
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        // 运行时类声明的所有属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }
    }

    @Test
    public void test2() {
        Class clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            // 获取修饰符
            int modifiers = field.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");

            // 获取类型
            Class type = field.getType();
            System.out.print(type.getName() + " ");

            // 获取属性名
            String name = field.getName();
            System.out.print(name + ";");

            System.out.println();
        }
    }
}
