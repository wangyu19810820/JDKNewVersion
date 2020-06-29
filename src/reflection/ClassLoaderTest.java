package reflection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {

    @Test
    public void test1() throws IOException {
        Properties properties = new Properties();
//        InputStream is = new FileInputStream("src/reflection/demo.properties");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("reflection/demo.properties");
        properties.load(is);
        System.out.println(properties.getProperty("user"));
    }
}
