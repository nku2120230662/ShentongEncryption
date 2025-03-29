package database;

import database.config.Connector;
import database.manipulation.crypt.InsertEncryptedRecords;
import database.manipulation.plain.CreateTable;
import database.manipulation.plain.GenerateRecords;
import org.junit.Test;

import java.sql.Connection;

public class TestCreateTable {
    @Test
    public void testCreateTable() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

        CreateTable.CreateStudents(conn);
        CreateTable.CreateCourses(conn);
        CreateTable.CreateEncryptedStudents(conn);
        CreateTable.CreateEncryptedCourses(conn);

        mc.closeConnection();
    }
}
