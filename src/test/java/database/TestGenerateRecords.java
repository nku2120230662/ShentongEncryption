package database;

import database.config.Connector;
import database.manipulation.crypt.InsertEncryptedRecords;
import database.manipulation.plain.GenerateRecords;
import database.parameter.RandomString;
import org.junit.Test;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestGenerateRecords {
    @Test
    public void testInsertCourse() throws Exception {
        Map mp=new HashMap();
        mp.put("courseId","11");
        mp.put("studentId","22");
        mp.put("grade","33");

        Connector mc = new Connector();
        Connection conn=mc.getConnection();

        GenerateRecords.InsertCourseRecord(conn,mp);
        InsertEncryptedRecords.InsertEncryptedCourse(conn,mp);

        mc.closeConnection();
    }
    @Test
    public void testInsertEncryptedStudent() throws Exception {
        Map mp=new HashMap();
        mp.put("id","1");
        mp.put("name","duan");
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

        InsertEncryptedRecords.InsertEncryptedStudent(conn,mp);

        mc.closeConnection();
    }

    @Test
    public void testGenerateCourseRecords() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();
        GenerateRecords.GenerateCourseRecords(conn);
        mc.closeConnection();
    }

    @Test
    public void testGenerateStudentRecords() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();
        // 在students表中生成数据
        GenerateRecords.GenerateStudentRecords(conn);
        mc.closeConnection();
    }

    @Test
    // testGenerateStudentConnectedRecords 同步生成students和encrypted_students表中数据
    public void testGenerateStudentConnectedRecords() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();
        // 同时
        for(int i=0;i<5;i++){
            Map mp=new HashMap();
            mp.put("id",(Object)i );
            mp.put("name", RandomString.generateRandomString(5));
            GenerateRecords.InsertStudentRecord(conn,mp);
            InsertEncryptedRecords.InsertEncryptedStudent(conn,mp);
        }

        mc.closeConnection();
    }

    @Test
    // testGenerateCourseConnectedRecords 同步生成courses和encrypted_courses表中数据
    public void testGenerateCourseConnectedRecords() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();
        // 同时
        for(int i=0;i<5;i++){
            Map mp=new HashMap();
            mp.put("courseId",(Object)(10+i));
            mp.put("studentId",(Object)i);
            mp.put("grade", (Object)(20+i));
            GenerateRecords.InsertCourseRecord(conn,mp);
            InsertEncryptedRecords.InsertEncryptedCourse(conn,mp);
        }

        mc.closeConnection();
    }

}
