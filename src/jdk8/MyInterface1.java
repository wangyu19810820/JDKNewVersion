package jdk8;

public interface MyInterface1 {

    default String getName() {
        return "MyInterface1 getName";
    }

    public static String show() {
        return "MyInterface1 show";
    }
}
