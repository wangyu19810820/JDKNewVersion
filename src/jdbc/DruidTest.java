package jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DruidTest {

    @Test
    public void test() throws Exception {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc/druid.properties"));
        properties.load(fileInputStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        System.out.println(dataSource.getConnection());
    }
}
