package jdbc;

import java.io.IOException;
import java.io.InputStream;
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
}
