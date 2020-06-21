package jdk8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest3 {

    private List<Employee> employeeList = new ArrayList<>(List.of(
            new Employee("张三", 18, 9999, Employee.Status.BUSY),
            new Employee("赵六", 18, 7777, Employee.Status.FREE),
            new Employee("李四", 44, 5555, Employee.Status.VOCATION),
            new Employee("王五", 8, 4444, Employee.Status.BUSY),
            new Employee("田七", 65, 6666, Employee.Status.FREE)));

    @Test
    public void test1() {
        boolean b1 = employeeList.stream().allMatch(employee -> employee.status() == Employee.Status.BUSY);
        System.out.println(b1);

        boolean b2 = employeeList.stream().anyMatch(employee -> employee.status() == Employee.Status.BUSY);
        System.out.println(b2);

        boolean b3 = employeeList.stream().noneMatch(employee -> employee.status() == Employee.Status.BUSY);
        System.out.println(b3);

        Optional<Employee> op1 = employeeList.stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .findFirst();
        System.out.println(op1.get());

        Optional<Employee> op2 = employeeList.stream()
                .filter(employee -> employee.status() == Employee.Status.FREE)
                .findAny();
        System.out.println(op2.get());
    }

    @Test
    public void test2() {
        long count = employeeList.stream().count();
        System.out.println(count);

        Optional<Employee> op1 = employeeList.stream().max(Comparator.comparing(Employee::salary));
        System.out.println(op1.get());

        Optional<Double> op2 = employeeList.stream().map(Employee::salary).min(Double::compare);
        System.out.println(op2.get());
    }

    @Test
    public void test3() {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer result = list.stream().reduce(0, Integer::sum);
        System.out.println(result);

        Optional<Double> result1 = employeeList.stream()
                .map(Employee::salary)
                .reduce(Double::sum);
        System.out.println(result1.get());
    }

    @Test
    public void test4() {
        List<String> result1 = employeeList.stream()
                .map(Employee::name)
                .collect(Collectors.toList());
        System.out.println(result1);

        Set<String> result2 = employeeList.stream()
                .map(Employee::name)
                .collect(Collectors.toSet());
        System.out.println(result2);

        HashSet<String> result3 = employeeList.stream()
                .map(Employee::name)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(result3);
    }

    @Test
    public void test5() {
        Long count = employeeList.stream().collect(Collectors.counting());
        System.out.println(count);

        Double avgSalary = employeeList.stream().collect(Collectors.averagingDouble(Employee::salary));
        System.out.println(avgSalary);

        Double sumSalary = employeeList.stream().collect(Collectors.summingDouble(Employee::salary));
        System.out.println(sumSalary);

        Optional<Employee> max = employeeList.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Employee::salary)));
        System.out.println(max.get());

        Optional<Double> min = employeeList.stream()
                .map(Employee::salary).collect(Collectors.minBy(Double::compare));
        System.out.println(min.get());
    }

    @Test
    public void test6() {
        Map<Employee.Status, List<Employee>> result = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::status));
        for (Employee.Status status : result.keySet()) {
            System.out.println(status + ":" + result.get(status));
        }
    }

    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> result = employeeList.stream()
                .collect(Collectors.groupingBy(Employee::status, Collectors.groupingBy(employee -> {
                    if (employee.age() < 35) {
                        return "青年";
                    } else if (employee.age() < 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        for (Employee.Status status : result.keySet()) {
            System.out.println(status + ":" + result.get(status));
        }
    }

    @Test
    public void test8() {
        Map<Boolean, List<Employee>> result = employeeList.stream()
                .collect(Collectors.partitioningBy(employee -> employee.salary() > 6000));
        for (boolean b : result.keySet()) {
            System.out.println(b + ":" + result.get(b));
        }
    }

    @Test
    public void test9() {
        DoubleSummaryStatistics result = employeeList.stream()
                .collect(Collectors.summarizingDouble(Employee::salary));
        System.out.println(result.getSum());
        System.out.println(result.getCount());
        System.out.println(result.getAverage());
        System.out.println(result.getMax());
        System.out.println(result.getMin());
    }

    @Test
    public void test10() {
        String result = employeeList.stream()
                .map(Employee::name)
                .collect(Collectors.joining(",", "===", "==="));
        System.out.println(result);
    }
}
