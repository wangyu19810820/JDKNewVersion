package jdk14;

public record Item(String desc, int partNum) {
    // 还可以声明静态属性、静态方法、构造器、实例方法
    // 不能声明实例属性、abstract、继承的父类
    private static int id;
    public static int getId() {
        return id++;
    }

    public Item(String desc) {
        this(desc, getId());
    }

    public String getUpperCaseDesc() {
        return desc.toUpperCase();
    }
}
