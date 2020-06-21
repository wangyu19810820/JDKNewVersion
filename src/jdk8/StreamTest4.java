package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTest4 {

    private List<Employee> employeeList = new ArrayList<>(List.of(
            new Employee("张三", 18, 9999, Employee.Status.BUSY),
            new Employee("赵六", 18, 7777, Employee.Status.FREE),
            new Employee("李四", 44, 5555, Employee.Status.VOCATION),
            new Employee("王五", 8, 4444, Employee.Status.BUSY),
            new Employee("田七", 65, 6666, Employee.Status.FREE)));

    Trader raol = new Trader("Raol", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = List.of(
            new Transaction(brian, 2011, 300),
            new Transaction(raol, 2012, 1000),
            new Transaction(raol, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950));

    @Test
    public void test1() {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        List<Integer> list1 = list.stream().map(x -> x * x).collect(Collectors.toList());
        System.out.println(list1);
    }

    @Test
    public void test2() {
        Optional<Integer> optional = employeeList.stream()
                .map(employee -> 1)
                .reduce(Integer::sum);
        System.out.println(optional.get());
    }

    @Test
    public void test3() {
        transactions.stream()
                .filter(transaction -> transaction.year() == 2011)
                .sorted(Comparator.comparing(Transaction::value))
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        transactions.stream()
                .map(Transaction::trader)
                .map(Trader::city)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test5() {
        transactions.stream()
                .map(Transaction::trader)
                .distinct()
                .filter(trader -> trader.city().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::name))
                .forEach(System.out::println);
    }

    @Test
    public void test6() {
        transactions.stream()
                .map(Transaction::trader)
                .map(Trader::name)
                .distinct()
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }

    @Test
    public void test7() {
        boolean result = transactions.stream()
                .map(Transaction::trader)
                .anyMatch(trader -> trader.city().equals("Milan"));
        System.out.println(result);
    }

    @Test
    public void test8() {
        Integer result = transactions.stream()
                .filter(transaction -> transaction.trader().city().equals("Cambridge"))
                .collect(Collectors.summingInt(Transaction::value));
        System.out.println(result);
    }

    @Test
    public void test9() {
        Optional<Integer> result = transactions.stream()
                .map(Transaction::value)
                .collect(Collectors.maxBy(Integer::compareTo));
        System.out.println(result.get());
    }

    @Test
    public void test10() {
        Optional<Transaction> result = transactions.stream()
                .collect(Collectors.minBy(Comparator.comparing(Transaction::value)));
        System.out.println(result.get());
    }
}
