package database;

import database.config.Connector;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static database.config.Connector.ModelName1;

public class TestDeleteTable {

    @Test
    public void testDeleteAllTables() throws Exception{
        Connector connector = new Connector();
        Connection connection = connector.getConnection();
        Statement stmt=connection.createStatement();
        stmt.executeUpdate("DROP TABLE " + ModelName1+ "."+ "students");
        stmt.executeUpdate("DROP TABLE " + ModelName1+ "."+ "courses");
        stmt.executeUpdate("DROP TABLE " + ModelName1+ "."+ "encrypted_students");
        stmt.executeUpdate("DROP TABLE " + ModelName1+ "."+ "encrypted_courses");
        stmt.close();
        connection.close();
    }
}
