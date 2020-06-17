package jdbc;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementQueryTest {

    @Test
    public void testGetInstance() throws Exception {
        String sql1 = "select id, order_name orderName, order_date orderDate from `order` where id = ?";
        Order order = getInstance(Order.class, sql1, 1);
        System.out.println(order);

        String sql2 = "select id, name, email, birth from customers where name = ?";
        Customer customer = getInstance(Customer.class, sql2, "哪吒");
        System.out.println(customer);
    }

    @Test
    public void testGetForList() throws Exception {
        String sql1 = "select id, order_name orderName, order_date orderDate from `order`";
        List<Order> orderList = getForList(Order.class, sql1);
        System.out.println(orderList);

        String sql2 = "select id, name, email, birth from customers where name = ?";
        List<Customer> customerList = getForList(Customer.class, sql2, "哪吒");
        System.out.println(customerList);
    }

    public <T> List<T> getForList(Class<T> tClass, String sql, Object...args) throws Exception {
        try (Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
            }
            try (ResultSet rs = statement.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    T result = tClass.getDeclaredConstructor().newInstance();
                    for (int i = 0; i < columnCount; i++) {
                        Object value = rs.getObject(i + 1);
                        String columnLabel = rsmd.getColumnLabel(i + 1);
                        Field field = tClass.getDeclaredField(columnLabel);
                        field.setAccessible(true);
                        field.set(result, value);
                    }
                    list.add(result);
                }
                return list;
            }
        }
    }

    public <T> T getInstance(Class<T> tClass, String sql, Object...args) throws Exception {
        try (Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
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
