package jdbc;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobTest {

    @Test
    public void testInsert() throws Exception {
        String sql = "insert into customers(name, email, birth, photo) values (?, ?, ?, ?)";
        try (Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, "wy");
            statement.setObject(2, "wy@sina.com");
            statement.setObject(3, "1981-08-20");
            statement.setBlob(4, new FileInputStream(new File("src/jdbc/wy.jpg")));
            statement.execute();
        }
    }
}
