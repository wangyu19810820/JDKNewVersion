package reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OtherTest {

    /**
     * 获取父类
     */
    @Test
    public void test1() {
        Class<Person> clazz = Person.class;
        Class<? super Person> superclass = clazz.getSuperclass();
        System.out.println(superclass);
    }

    /**
     * 获取带泛型的父类
     * ParameterizedType和Class，都是Type的子类型
     */
    @Test
    public void test2() {
        Class<Person> clazz = Person.class;
        Type superclass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(parameterizedType.getRawType());
        System.out.println(((Class)typeArguments[0]).getName());
    }

    /**
     * 获取接口
     */
    @Test
    public void test3() {
        Class<Person> clazz = Person.class;
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }
    }

    /**
     * 获取包
     */
    @Test
    public void test4() {
        Class<Person> clazz = Person.class;
        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

    /**
     * 获取注解
     */
    @Test
    public void test5() {
        Class<Person> clazz = Person.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }
}
