package jdk12;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesTest {

    /**
     * 比较两个文件是否相同，如果文件不同，返回第一个不同点的索引，如果文件相同，返回-1
     * @throws IOException
     */
    @Test
    public void testMismatch() throws IOException {
        try (var fileWriter1 = new FileWriter("tmp\\a.txt");
                var fileWriter2 = new FileWriter("tmp\\b.txt")) {
            fileWriter1.write("a");
            fileWriter1.write("b");
            fileWriter1.write("c");
            fileWriter2.write("a");
            fileWriter2.write("1");
            fileWriter2.write("c");
        }
        System.out.println(Files.mismatch(Path.of("tmp\\a.txt"), Path.of("tmp\\b.txt")));
    }
}
