package jdk8;

public record Employee(String name, int age, double salary) {

    public Employee() {
        this(null, 0, 0.0);
    }

    public Employee(String name) {
        this(name, 0, 0.0);
    }

    public Employee(String name, int age) {
        this(name, age, 0.0);
    }
}
