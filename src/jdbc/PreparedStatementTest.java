package jdbc;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PreparedStatementTest {

    @Test
    public void testInsert() throws SQLException, IOException, ClassNotFoundException, ParseException {
        try (Connection connection = GetConnectionDemo.getConnection()) {
            String sql = "insert into customers(name, email, birth) values (?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "哪吒");
                statement.setString(2, "nezha@sina.com");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = df.parse("2000-01-01");
                statement.setDate(3, new Date(date.getTime()));
                statement.execute();
            }
        }
    }

    @Test
    public void testUpdate() throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = GetConnectionDemo.getConnection()) {
            String sql = "update customers set name = ? where id = ?";
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, "莫扎特");
                statement.setObject(2, 1);
                statement.execute();
            }
        }
    }

    @Test
    public void testDelete() throws SQLException, IOException, ClassNotFoundException {
//        String sql = "delete from customers where id = ?";
//        executeUpdate(sql, 1);
        String sql = "update `order` set order_name = ? where id = ?";
        executeUpdate(sql, "bb", "1");
    }

    public void executeUpdate(String sql, Object... args)
            throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = GetConnectionDemo.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                for(int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
                statement.execute();
            }
        }
    }
}
