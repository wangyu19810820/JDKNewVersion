package jdbc;

import org.junit.Test;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;

public class BlobTest {

    @Test
    public void testInsert() throws Exception {
        String sql = "insert into customers(name, email, birth, photo) values (?, ?, ?, ?)";
        try (Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, "wy2");
            statement.setObject(2, "wy2@sina.com");
            statement.setObject(3, "1981-08-20");
            statement.setBlob(4, new FileInputStream(new File("src/jdbc/wy2.jpg")));
            statement.execute();
        }
    }

    @Test
    public void testQuery() throws Exception {
        String sql = "select id, name, email, birth, photo from customers where id = ?";
        try(Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 3);
            try(ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String name = rs.getString("name");
                    LocalDate date = rs.getObject("birth", LocalDate.class);
                    Customer customer = new Customer(id, name, email, date);
                    System.out.println(customer);
                    Blob photo = rs.getBlob("photo");
                    try (InputStream is = photo.getBinaryStream();
                            OutputStream os = new FileOutputStream("src/jdbc/wy1.jpg")) {
//                        is.transferTo(os);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = is.read(buffer)) != -1) {
                            os.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }
}
