package proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

    @Test
    public void testDynamicProxy() {
        ClothFactory clothFactory = new NikeClothFactory();
        ClothFactory proxyInstance = (ClothFactory)ProxyFactory.getProxyInstance(clothFactory);
        proxyInstance.productCloth();
    }
}

class ProxyFactory {
    public static Object getProxyInstance(Object obj) {
//        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
//                obj.getClass().getInterfaces(),
//                (proxy, method, args) -> {
//                    System.out.println("准备工作");
//                    Object returnVal = method.invoke(obj, args);
//                    System.out.println("收尾工作");
//                    return returnVal;
//                });
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
            obj.getClass().getInterfaces(),
            new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    ClothFactoryUtil.method1();
                    Object returnVal = method.invoke(obj, args);
                    ClothFactoryUtil.method2();
                    return returnVal;
                }
            });
    }
}

class ClothFactoryUtil {
    public static void method1() {
        System.out.println("准备工作");
    }

    public static void method2() {
        System.out.println("收尾工作");
    }
}