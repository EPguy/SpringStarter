package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private DataSource ds;

    @Test
    public void testDataSource() throws Exception {
        System.out.println("DS=" + ds);

        try (Connection conn = ds.getConnection()) {
            System.out.println("Cooooooooonn=" + conn);
            assertThat(conn).isInstanceOf(Connection.class);

            assertEquals(100, getLong(conn, "select 100"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Long getLong(Connection conn, String sql) {

        long result = 0;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                result = rs.getLong(1);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
