package jdbc;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPTest {

    @Test
    public void test1() throws SQLException {
        BasicDataSource dataSources = new BasicDataSource();
        dataSources.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSources.setUrl("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&rewriteBatchedStatements=true");
        dataSources.setUsername("root");
        dataSources.setPassword("root");
        dataSources.setInitialSize(10);

        Connection connection = dataSources.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test2() throws Exception {
        Properties properties = new Properties();
        FileInputStream is = new FileInputStream(new File("src/jdbc/dbcp.properties"));
        properties.load(is);
        BasicDataSource dataSources = BasicDataSourceFactory.createDataSource(properties);
        Connection connection = dataSources.getConnection();
        System.out.println(connection);
    }
}
