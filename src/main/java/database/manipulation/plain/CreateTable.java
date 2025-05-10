package database.manipulation.plain;

import database.config.Connector;

import java.sql.Connection;
import java.sql.Statement;

import static database.config.Connector.ModelName1;

public class CreateTable {

    public static boolean CreateCourses(Connection conn) throws Exception{
        //demo:CREATE TABLE TESTTABLES.STUDENTS
        //(
        //    "NAME" character varying(10) ,
        //    ID integer NOT NULL,
        //    CONSTRAINT TBL_STUDENTS_PKEY PRIMARY KEY (ID)
        //)
        String sql="CREATE TABLE IF NOT EXISTS " +
                ModelName1+
                "."+
                "courses " +
                "(" +
                "course_id INT," +
                "grade DECIMAL," +
                "student_id INT " +
                ")";
        Statement stmt=conn.createStatement();
        int result=stmt.executeUpdate(sql);
        if(result==0){
            System.out.println("Table Cources created");
            stmt.close();
            return true;
        }
        System.out.println("Table Cources failed");
        stmt.close();
        return false;
    }

    public static boolean CreateEncryptedCourses(Connection conn) throws Exception{
        String sql="CREATE TABLE IF NOT EXISTS " +
                ModelName1+
                "."+
                "encrypted_courses " +
                "(" +
                "en_course_id VARCHAR(255), " +
                "en_grade VARCHAR(255), " +
                "en_student_id VARCHAR(255)" +
                ")";
        Statement stmt=conn.createStatement();
        int result=stmt.executeUpdate(sql);//
        if(result==0){
            System.out.println("Table encrypted_courses created");
            stmt.close();
            return true;
        }
        System.out.println("Table encrypted_courses failed");
        stmt.close();
        return false;
    }

    public static boolean CreateStudents(Connection conn) throws Exception{
        String sql="CREATE TABLE IF NOT EXISTS " +
                ModelName1+
                "."+
                "students " +
                "(" +
                "id INT," +
                "name VARCHAR(255)"+
                ")";
        Statement stmt=conn.createStatement();
        int result=stmt.executeUpdate(sql);
        if(result==0){
            System.out.println("Table students created");
            stmt.close();
            return true;
        }
        System.out.println("Table students failed");
        stmt.close();
        return false;
    }

    public static boolean CreateEncryptedStudents(Connection conn) throws Exception{
        String sql="CREATE TABLE IF NOT EXISTS " +
                ModelName1+
                "."+
                "encrypted_students " +
                "(" +
                "en_id VARCHAR(255)," +
                "en_name VARCHAR(255)" +
                ")";
        Statement stmt=conn.createStatement();
        int result=stmt.executeUpdate(sql);//
        if(result==0){
            System.out.println("Table encrypted_students created");
            stmt.close();
            return true;
        }
        System.out.println("Table encrypted_students failed");
        stmt.close();
        return false;
    }
}
