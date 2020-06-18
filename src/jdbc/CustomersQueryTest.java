package jdbc;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class CustomersQueryTest {

    @Test
    public void testQuery1() throws SQLException, IOException, ClassNotFoundException {
        String sql = "select id, name, email, birth from customers where id = ?";
        try (Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, 2);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String email = rs.getString(3);
                    Date birth = (Date) rs.getObject(4);
                    LocalDate birthDay = rs.getObject(4, LocalDate.class);
                    Customer customer = new Customer(id, name, email, birthDay);
                    System.out.println(customer);
                }
            }
        }
    }

    @Test
    public void testQuery2() throws Exception {
//        String sql = "select id, name, email, birth from customers where id = ?";
//        Customer customer = executeQuery(sql, 2);
//        System.out.println(customer);

        String sql = "select name, email, birth from customers where name = ?";
        Customer customer = executeQuery(sql, "哪吒");
        System.out.println(customer);
    }

    public Customer executeQuery(String sql, Object... args) throws Exception {
        try (Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            for(int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            try (ResultSet rs = statement.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                if (rs.next()) {
                    Customer customer = new Customer();
                    for (int i = 0; i < columnCount; i++) {
                        Object value = rs.getObject(i + 1);
                        LocalDate date = null;
                        int type = rsmd.getColumnType(i + 1);
                        if (type == Types.DATE) {
                            date = rs.getObject(i + 1, LocalDate.class);
                        }
                        String columnLabel = rsmd.getColumnLabel(i + 1);
                        Field field = Customer.class.getDeclaredField(columnLabel);
                        Class fieldType = field.getType();
                        field.setAccessible(true);
                        if (fieldType == LocalDate.class){
                            field.set(customer, date);
                        } else {
                            field.set(customer, value);
                        }
                    }
                    return customer;
                } else {
                    return null;
                }
            }
        }
    }
}
