package jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

public class CustomerDaoImplTest {

    private CustomerDao dao = new CustomerDaoImpl();

    @Test
    public void insert() throws Exception {
        Connection connection = GetConnectionDemo.getConnection3();
        try (connection) {
            Customer customer = new Customer("abc", "abc@sina.com", new Date());
            dao.insert(connection, customer);
        }
    }

    @Test
    public void deleteById() throws Exception {
        Connection connection = GetConnectionDemo.getConnection3();
        try (connection) {
            dao.deleteById(connection, 5);
        }
    }

    @Test
    public void update() throws Exception {
        Connection connection = GetConnectionDemo.getConnection3();
        try (connection) {
            Customer customer = new Customer(6,"abcd", "abcd@sina.com", new Date());
            dao.update(connection, customer);
        }
    }

    @Test
    public void getCustomerById() throws Exception {
        Connection connection = GetConnectionDemo.getConnection3();
        try (connection) {
            Customer customer = dao.getCustomerById(connection, 6);
            System.out.println(customer);
        }
    }

    @Test
    public void getAll() throws Exception {
        Connection connection = GetConnectionDemo.getConnection3();
        try (connection) {
            List<Customer> customerList = dao.getAll(connection);
            System.out.println(customerList);
        }
    }

    @Test
    public void getCount() throws Exception {
        Connection connection = GetConnectionDemo.getConnection3();
        try (connection) {
            Long count = dao.getCount(connection);
            System.out.println(count);
        }
    }

    @Test
    public void getMaxBirth() throws Exception {
        Connection connection = GetConnectionDemo.getConnection3();
        try (connection) {
            Date date = dao.getMaxBirth(connection);
            System.out.println(date);
        }
    }

}
