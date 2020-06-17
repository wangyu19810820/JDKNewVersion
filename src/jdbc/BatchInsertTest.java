package jdbc;

import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchInsertTest {

    @Test
    public void testInsert1() throws Exception {
        String sql = "insert into goods(name) values (?)";
        try(Connection connection = GetConnectionDemo.getConnection();
                PreparedStatement stat = connection.prepareStatement(sql)) {
            long start = System.currentTimeMillis();
            for(int i = 0; i < 20000; i++) {
                stat.setString(1, "name_" + i);
                stat.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }

    @Test
    public void testInsert2() throws Exception {
        String sql = "insert into goods(name) values (?)";
        try(Connection connection = GetConnectionDemo.getConnection();
            PreparedStatement stat = connection.prepareStatement(sql)) {
            long start = System.currentTimeMillis();
            for(int i = 1; i <= 100_0000; i++) {
                stat.setString(1, "name_" + i);
                stat.addBatch();

                if (i % 500 == 0) {
                    stat.executeBatch();
                    stat.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);// 21649毫秒
        }
    }

    @Test
    public void testInsert3() throws Exception {
        String sql = "insert into goods(name) values (?)";
        try(Connection connection = GetConnectionDemo.getConnection();
            PreparedStatement stat = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);

            long start = System.currentTimeMillis();
            for(int i = 1; i <= 100_0000; i++) {
                stat.setString(1, "name_" + i);
                stat.addBatch();

                if (i % 500 == 0) {
                    stat.executeBatch();
                    stat.clearBatch();
                }
            }
            connection.commit();

            long end = System.currentTimeMillis();
            System.out.println(end - start);// 18340毫秒
        }
    }
}
