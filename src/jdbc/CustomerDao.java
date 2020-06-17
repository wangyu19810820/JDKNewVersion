package jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public interface CustomerDao {

    void insert(Connection connection, Customer customer) throws Exception;
    void deleteById(Connection connection, int id) throws Exception;
    void update(Connection connection, Customer customer) throws Exception;
    Customer getCustomerById(Connection connection, int id) throws Exception;
    List<Customer> getAll(Connection connection) throws Exception;
    Long getCount(Connection connection) throws Exception;
    Date getMaxBirth(Connection connection) throws Exception;
}
