package jdk11;

import org.junit.Test;

import java.io.FileOutputStream;

public class InputStreamTest {

    @Test
    public void test() {
        var cl = this.getClass().getClassLoader();
        var is = cl.getResourceAsStream("jdk11/file");
        try (var os = new FileOutputStream("src/jdk11/file1")) {
            is.transferTo(os);  // 把输入流中的所有数据自动复制到输出流中
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
