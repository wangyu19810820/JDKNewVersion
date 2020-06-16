package jdbc;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

    @Test
    public void test() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = GetConnectionDemo.getConnection();
                Statement stat = connection.createStatement()) {
            stat.executeUpdate("CREATE TABLE greeting (Message CHAR(20))");
            stat.executeUpdate("INSERT INTO greeting VALUES ('Hello World')");
            try(ResultSet rs = stat.executeQuery("SELECT * FROM greeting")) {
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }
            stat.executeUpdate("DROP TABLE greeting");
        }
    }
}
