package reflection;

import java.io.IOException;
import java.util.Arrays;

@MyAnnotation
public class Person extends Creature<String> implements Comparable<Person> {
    private String name;
    public int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @MyAnnotation()
    @Override
    public void show() {
        System.out.println("我是一个人");
    }

    public static void display() {
        System.out.println("我是一个可爱的人");
    }

    @MyAnnotation("abc")
    private String showNation(String nation, String... language)
            throws IOException, RuntimeException {
        System.out.println("我的国籍是：" + nation + ",语言是：" + Arrays.toString(language));
        return nation;
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }
}
