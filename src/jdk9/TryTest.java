package jdk9;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TryTest {

    // 自动关闭的try写法
    @Test
    public void testTry() {
        InputStreamReader inputStream = new InputStreamReader(System.in);
        OutputStreamWriter outputStream = new OutputStreamWriter(System.out);
        try(inputStream; outputStream) {
            // ...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
