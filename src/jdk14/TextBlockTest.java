package jdk14;

public class TextBlockTest {

    public static void main(String[] args) {
        // 反斜杠表示去除换行符
        // \s转义成空格
        var str1 = """
                abc\s\
                efg\
                """;
        System.out.println(str1);
    }
}
