package jdbc;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionTest {

    @Test
    public void test1() throws SQLException, IOException, ClassNotFoundException {
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        executeUpdate(sql1, "aa");

        System.out.println(10 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        executeUpdate(sql2, "bb");
        System.out.println("转账成功");
    }

    @Test
    public void test2() {
        Connection connection = null;
        try {
            connection = GetConnectionDemo.getConnection();

            System.out.println(connection.getAutoCommit());
            connection.setAutoCommit(false);

            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            executeUpdate(connection, sql1, "aa");

            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            executeUpdate(connection, sql2, "bb");

            connection.commit();
            System.out.println("转账成功");
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test3() {
        Connection connection = null;
        try {
            connection = GetConnectionDemo.getConnection();

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            String sql1 = "select user, password, balance from user_table where user = ?";
            User user = getInstance(connection, User.class, sql1, "aa");

            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test4() {
        Connection connection = null;
        try {
            connection = GetConnectionDemo.getConnection();

            System.out.println(connection.getAutoCommit());
            connection.setAutoCommit(false);

            String sql1 = "update user_table set balance = 2000 where user = ?";
            executeUpdate(connection, sql1, "aa");

            Thread.sleep(15000);
            connection.rollback();
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public int executeUpdate(String sql, Object... args)
            throws SQLException, IOException, ClassNotFoundException {
        try (Connection connection = GetConnectionDemo.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                for(int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
                return statement.executeUpdate();
            }
        }
    }

    public int executeUpdate(Connection connection, String sql, Object... args)
            throws SQLException, IOException, ClassNotFoundException {
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            for(int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            return statement.executeUpdate();
        }
    }

    public <T> T getInstance(Connection connection, Class<T> tClass, String sql, Object...args) throws Exception {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = statement.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                if (rs.next()) {
                    T result = tClass.getDeclaredConstructor().newInstance();
                    for (int i = 0; i < columnCount; i++) {
                        Object value = rs.getObject(i + 1);
                        String columnLabel = rsmd.getColumnLabel(i + 1);
                        Field field = tClass.getDeclaredField(columnLabel);
                        field.setAccessible(true);
                        field.set(result, value);
                    }
                    return result;
                } else {
                    return null;
                }
            }
        }
    }
}
