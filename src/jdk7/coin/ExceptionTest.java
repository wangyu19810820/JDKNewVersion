package jdk7.coin;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;

public class ExceptionTest {

    /**
     * 多个异常可以合并到一个catch中
     */
    @Test
    public void test1() {
        try {
            readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重抛异常保留原异常类型
     */
    @Test
    public void test2() {
        try {
            try {
                readFile();
            } catch (Exception e) {
                throw e;
            }
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (ParseException e) {
            System.out.println("ParseException");
        }
    }

    /**
     * 自动关闭资源的try-with-resources
     */
    @Test
    public void test3() {
        try(FileInputStream fin = new FileInputStream("src/jdk7/ExceptionTest.java");
                ObjectInputStream in = new ObjectInputStream(fin)) {
            //...
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile()
            throws FileNotFoundException, IOException, ParseException {
        // ...
        throw new FileNotFoundException();
    }
}
