package jdk11;

import java.io.FileInputStream;

public class StringTest {

    public static void main(String[] args) {
        var str = " \t  \r\n ";
        System.out.println(str.isBlank());

        var str1 = str + "abc" + str + "　"; // 尾部有中文全角空格
        System.out.println(str1.strip());   // 去除所有空格
        System.out.println(str1.trim());    // 去除英文空格
        System.out.println("-------------------------");
        System.out.println(str1.stripLeading());    // 去除前导空格
        System.out.println("-------------------------");
        System.out.println(str1.stripTrailing());   // 去除尾部空格
        System.out.println("-------------------------");

        // 新字符串，重复5次abc
        System.out.println("abc".repeat(5));

        // 生成一个流，没个元素是一行
        "a\nb\nc".lines().forEach(System.out::println);

        try(FileInputStream fis = new FileInputStream("src/jdk11/StringTest.java")) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String str2 = new String(buffer);
            str2.lines().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
