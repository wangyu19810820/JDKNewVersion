package reflection;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodTest {

    @Test
    public void test1() {
        Class<Person> clazz = Person.class;
        // 获取运行时类及其父类的public方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("----------------------------------------------");

        // 获取运行时类声明的所有方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.println(method);
        }
    }

    @Test
    public void test2() {
        Class<Person> clazz = Person.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            // 获取注解
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

            // 获取修饰符
            int modifiers = method.getModifiers();
            System.out.print(Modifier.toString(modifiers) + " ");

            // 返回值类型
            Class returnType = method.getReturnType();
            System.out.print(returnType.getName() + " ");

            // 获取方法名
            String name = method.getName();
            System.out.print(name + " ");

            // 获取参数列表
            Class[] parameterTypes = method.getParameterTypes();
            System.out.print("(");
            for (int i = 0; i < parameterTypes.length; i++) {
                Class parameterType = parameterTypes[i];
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(parameterType.getName());
            }
            System.out.print(")");

            // 抛出的异常
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            for (int i = 0; i < exceptionTypes.length; i++) {
                Class<?> exceptionType = exceptionTypes[i];
                if (i == 0) {
                    System.out.print("\n\t\tthrows " + exceptionType.getName());
                } else {
                    System.out.print(", " + exceptionType.getName());
                }
            }

            System.out.println("\n");
        }
    }
}
