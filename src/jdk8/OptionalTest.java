package jdk8;

import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

    @Test
    public void test1() {
        Optional<Employee> optional = Optional.of(new Employee());
        Employee employee = optional.get();
        System.out.println(employee);
    }

    @Test
    public void test2() {
        Optional<Employee> optional = Optional.empty();
        optional.get();
    }

    @Test
    public void test3() {
        Optional<Employee> optional = Optional.ofNullable(null);
        // 判断optional内是否有值
//        if (optional.isPresent()) {
//            System.out.println(optional.get());
//        }

//        Employee employee = optional.orElse(new Employee("wy"));
//        System.out.println(employee);

        Employee employee = optional.orElseGet(Employee::new);
        System.out.println(employee);
    }

    @Test
    public void test4() {
        Optional<Employee> optional = Optional.ofNullable(new Employee("wy"));
//        Optional<String> optional1 = optional.map(Employee::name);
//        System.out.println(optional1.get());

        Optional<String> optional1 = optional.flatMap(employee -> Optional.of(employee.name()));
        System.out.println(optional1.get());
    }
}
