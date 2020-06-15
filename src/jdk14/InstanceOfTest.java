package jdk14;

import org.junit.Test;

public class InstanceOfTest {

    @Test
    public void test1() {
        Object obj = "Hello Java";
        // 省去了强制类型转换
        if (obj instanceof String str) {
            System.out.println(str.contains("Java"));
        } else {
            System.out.println("不是字符串");
        }
    }

    @Test
    public void test2() {
        Monitor monitor1 = new Monitor("abc", 100.0);
        Monitor monitor2 = new Monitor("abc", 100.0);
        System.out.println(monitor1.equals(monitor2));
        System.out.println(monitor1.equals("abc"));
    }
}

class Monitor {
    private String model;
    private double price;

    public Monitor(String model, double price) {
        this.model = model;
        this.price = price;
    }

    // instanceof 类型 变量名的写法还可以应用到表达式中
    @Override
    public final boolean equals(Object o) {
        return o instanceof Monitor obj && obj.model.equals(model) && obj.price == price;
    }

}