package jdbc;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class OrderQueryTest {

    @Test
    public void test1() throws Exception {
        String sql = "select id, order_name orderName, order_date orderDate from `order` where id = ?";
        Order order = executeQuery(sql, 1);
        System.out.println(order);
    }

    public Order executeQuery(String sql, Object...args) throws Exception {
        try (Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            for(int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = statement.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                if (rs.next()) {
                    Order order = new Order();
                    for (int i = 0; i < columnCount; i++) {
                        Object value = rs.getObject(i + 1);
                        String columnLabel = rsmd.getColumnLabel(i + 1);
                        Field field = Order.class.getDeclaredField(columnLabel);
                        field.setAccessible(true);
                        field.set(order, value);
                    }
                    return order;
                } else {
                    return null;
                }
            }
        }
    }
}
