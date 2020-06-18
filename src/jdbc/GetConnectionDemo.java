package jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = getConnection();
        System.out.println(connection);
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
//        InputStream is = jdbc.GetConnectionDemo.class.getClassLoader().getResourceAsStream("jdbc/db.properties");
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc/db.properties");

        Properties properties = new Properties();
        properties.load(is);

        Class.forName(properties.getProperty("driverClass"));
        Connection con = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("user"), properties.getProperty("password"));
        return con;
    }

    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");

    public static Connection getConnection1() throws SQLException {
        return cpds.getConnection();
    }

    private static BasicDataSource bds;
    static {
        Properties properties = new Properties();
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File("src/jdbc/dbcp.properties"));
            properties.load(is);
            bds = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection2() throws Exception {
        return bds.getConnection();
    }

    public static DataSource dataSource;
    static {
        Properties properties = new Properties();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("src/jdbc/druid.properties"));
            properties.load(fileInputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection3() throws Exception {
        return dataSource.getConnection();
    }
}
