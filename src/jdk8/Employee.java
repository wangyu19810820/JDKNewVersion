package jdk8;

public record Employee(String name, int age, double salary, Status status) {

    public Employee() {
        this(null, 0, 0.0, null);
    }

    public Employee(String name) {
        this(name, 0, 0.0, null);
    }

    public Employee(String name, int age) {
        this(name, age, 0.0, null);
    }

    public Employee(String name, int age, double salary) {
        this(name, age, salary, null);
    }

    public enum Status{FREE, BUSY, VOCATION}
}
