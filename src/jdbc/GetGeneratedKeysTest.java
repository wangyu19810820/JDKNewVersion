package jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * 获取自增主键
 * PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
 * ResultSet rs = stat.getGeneratedKeys()
 * rs.getInt(1)
 */
public class GetGeneratedKeysTest {

    @Test
    public void test() throws Exception {
        String sql = "insert into `order` (order_name, order_date) values (?, ?)";
        try (Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement stat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stat.setString(1, "aa");
            stat.setDate(2, new Date(new java.util.Date().getTime()));
            stat.executeUpdate();
            try(ResultSet rs = stat.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println(rs.getInt(1));
                }
            }
        }
    }
}
