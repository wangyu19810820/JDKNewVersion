package jdk12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringTest {

    /**
     * String类的transform方法，生成变换后的新字符串
     */
    @Test
    public void testTransform1() {
        String s = "hello".transform(info -> info + " world");
        System.out.println(s);
    }

    @Test
    public void testTransform2() {
        String s = "  hello".transform(info -> info + " world  ")
                .transform(String::toUpperCase)
                .transform(String::trim);
        System.out.println(s);
    }

    @Test
    public void testTransform3() {
        List<String> list1 = List.of("Java", " Python", "  C++  ");
        List<String> list2 = list1.stream()
                .map(String::strip)
                .map(String::toUpperCase)
                .map(e -> "Hi," + e)
                .collect(Collectors.toList());
        list2.forEach(System.out::println);
    }

    @Test
    public void testTransform4() {
        List<String> list1 = List.of("Java", " Python", "  C++  ");
        List<String> list2 = new ArrayList<>();
        list1.forEach(e -> list2.add(e.transform(String::trim)
                .transform(String::toUpperCase)
                .transform(e1 -> "Hi," + e1)));
        list2.forEach(System.out::println);
    }

    /**
     * String类的indent方法，每行的开头添加指定数量的空格
     */
    @Test
    public void testIndent() {
        var result = "Java\n Python\nC++".indent(3);
        System.out.println(result);
    }
}
