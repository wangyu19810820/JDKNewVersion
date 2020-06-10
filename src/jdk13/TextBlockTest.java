package jdk13;

import org.junit.Test;

public class TextBlockTest {

    /**
     * 用三个双引号包裹文本块
     */
    @Test
    public void test1() {
        String html = """
    <html>
        <body>
            <p>Hello World</p>
        </body>
    </html>
""";
        System.out.println(html);
    }

    /**
     * 文本块以第二行第一个字符开始，以结束符结束。
     * 文本块和字符串，无法区分。
     */
    @Test
    public void test2() {
        String text1 = """
abc""";
        String text2 = "abc";
        System.out.println(text1 == text2);

        String text3 = """
abc            
""";
        String text4 = "abc\n";
        System.out.println(text3 == text4);
    }

    /**
     * 文本块的空字符串需要另起一行
     */
    @Test
    public void test3() {
        String text1 = "";
        System.out.println(text1.length());
        String text2 = """
""";
        System.out.println(text2.length());
    }

    /**
     * 文本块错误的用法
     */
    @Test
    public void test4() {
        // 文本块的内容需要在新的一行中表示
        // String text1 = """""";
        // String text2 = """   """;

        // 没有结束符
        // String text3 = """
            // abc
//";

        // 含有未转义的反斜线
        // String text4 = """
        // abc \ def
        // """;
    }

    /**
     * 文本块会删除掉行后多余的空格，会比照结束符删除前导空格
     */
    @Test
    public void test5() {
        String text1 = """
    abc                
    """;
        System.out.println(text1.length());
        System.out.println(text1);
    }

    /**
     * 可在文本块内包含转义字符
     */
    @Test
    public void test6() {
        String html = """
            <html>
                <body>\n
                    <p>Hello World</p>\n
                </body>
            </html>
        """;
        System.out.println(html);
    }

    /**
     * 文本块内可以自由使用双引号，需要对三个双引号进行反斜杠转义
     */
    @Test
    public void test7() {
        String str = """
        Jone said "Hello".
        I said "Hi".    
        """;
        System.out.println(str);

        String str1 = """
             text block inside \"""   
         """;
        System.out.println(str1);
    }

    /**
     * 文本块可以和普通字符串，用+号拼接
     */
    @Test
    public void test8() {
        var str = "abc" + """
        xyz        
        """;
        System.out.println(str);
    }

    /**
     * 文本块的拼接技巧
     */
    @Test
    public void test9() {
        String type = "String";
        String code1 = """
                public void print(""" + type + """
                 o) {
                    System.out.println(Objects.toString(o));
                }
                """;
        System.out.println(code1);

        String code2 = """
                public void print($type o) {
                    System.out.println(Objects.toString(o));
                }
                """.replace("$type", type);
        System.out.println(code2);

        String code3 = String.format("""
                public void print(%s o) {
                    System.out.println(Objects.toString(o));
                }
                """, type);
        System.out.println(code3);

        String code4 = """
                public void print(%s o) {
                    System.out.println(Objects.toString(o));
                }
                """.formatted(type);
        System.out.println(code4);
    }
}
