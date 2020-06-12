package jdk11;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        // 抛异常，数据不可为空
//        Optional<String> optional = Optional.of(null);
        Optional<String> optional = Optional.ofNullable(null);  // 数据可为空
        System.out.println(optional.orElse("abc"));
        System.out.println(optional.orElseThrow());
    }
}
