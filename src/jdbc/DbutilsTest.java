package jdbc;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DbutilsTest {

    @Test
    public void testInsert() throws Exception {
        try(Connection connection = GetConnectionDemo.getConnection3()) {
            QueryRunner runner = new QueryRunner();
            String sql = "insert into customers(name, email, birth) values (?, ?, ?)";
            int insertCount = runner.update(connection, sql, "wy3", "wy3@sina.com", LocalDate.now());
            System.out.println("添加了" + insertCount + "条记录");
        }
    }

    @Test
    public void testQuery() throws Exception {
        try(Connection connection = GetConnectionDemo.getConnection3()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select id, name, email, birth from customers where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(connection, sql, handler, "2");
            System.out.println(customer);
        }
    }

    @Test
    public void testQuery1() throws Exception {
        try(Connection connection = GetConnectionDemo.getConnection3()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select id, name, email, birth from customers where id > ?";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> customerList = runner.query(connection, sql, handler, "2");
            System.out.println(customerList);
        }
    }

    @Test
    public void testQuery2() throws Exception {
        try(Connection connection = GetConnectionDemo.getConnection3()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select id, name, email, birth from customers where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(connection, sql, handler, "2");
            System.out.println(map);
        }
    }

    @Test
    public void testQuery3() throws Exception {
        try(Connection connection = GetConnectionDemo.getConnection3()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select id, name, email, birth from customers where id > ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> mapList = runner.query(connection, sql, handler, "2");
            System.out.println(mapList);
        }
    }

    @Test
    public void testQuery4() throws Exception {
        try(Connection connection = GetConnectionDemo.getConnection3()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select count(*) from customers";
            ScalarHandler handler = new ScalarHandler();
            Long count = (Long) runner.query(connection, sql, handler);
            System.out.println(count);
        }
    }

    @Test
    public void testQuery5() throws Exception {
        try(Connection connection = GetConnectionDemo.getConnection3()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select max(birth) from customers";
            ScalarHandler handler = new ScalarHandler();
            Date maxBirth = (Date) runner.query(connection, sql, handler);
            System.out.println(maxBirth);
        }
    }

    @Test
    public void testQuery6() throws Exception {
        try(Connection connection = GetConnectionDemo.getConnection3()) {
            QueryRunner runner = new QueryRunner();
            String sql = "select id, name, email, birth from customers where id = ?";
            Customer customer = runner.query(connection, sql, (ResultSet rs) -> {
                Customer customer1 = new Customer();
                if (rs.next()) {
                    customer1.setId(rs.getInt("id"));
                    customer1.setName(rs.getString("name"));
                    customer1.setEmail(rs.getString("email"));
                    customer1.setBirth(rs.getDate("birth"));
                }
                return customer1;
            }, 2);
            System.out.println(customer);
        }
    }
}
