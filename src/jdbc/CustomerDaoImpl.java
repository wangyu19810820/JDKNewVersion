package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDaoImpl implements BaseDao, CustomerDao {

    @Override
    public void insert(Connection connection, Customer customer) throws Exception {
        String sql = "insert into customers (name, email, birth) values (?, ?, ?)";
        executeUpdate(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth());
    }

    @Override
    public void deleteById(Connection connection, int id) throws Exception {
        String sql = "delete from customers where id = ?";
        executeUpdate(connection, sql, id);
    }

    @Override
    public void update(Connection connection, Customer customer) throws Exception {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        executeUpdate(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
    }

    @Override
    public Customer getCustomerById(Connection connection, int id) throws Exception {
        String sql = "select id, name, email, birth from customers where id = ?";
        return getInstance(connection, Customer.class, sql, id);
    }

    @Override
    public List<Customer> getAll(Connection connection) throws Exception {
        String sql = "select id, name, email, birth from customers";
        return getForList(connection, Customer.class, sql);
    }

    @Override
    public Long getCount(Connection connection) throws Exception {
        String sql = "select count(*) from customers";
        return getValue(connection, sql);
    }

    @Override
    public Date getMaxBirth(Connection connection) throws Exception {
        String sql = "select max(birth) from customers";
        return getValue(connection, sql);
    }
}
