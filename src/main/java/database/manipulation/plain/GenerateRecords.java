package database.manipulation.plain;

import database.parameter.RandomString;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Random;

public class GenerateRecords {
    public static boolean GenerateCourseRecords(Connection conn) throws Exception{
        Random rand = new Random();
        Statement stmt=conn.createStatement();

        for(int i=0;i<5;i++){
            int s_id=rand.nextInt(100);
            int grades=rand.nextInt(100);
            int t_id=rand.nextInt(100);

            String sql="insert into courses values(" +
                    s_id + "," +
                    grades + "," +
                    t_id +
                    ")";
            int result=stmt.executeUpdate(sql);
            if(result==1){
                System.out.println("Record " + i + " Inserted");
            }
        }

        stmt.close();
        return true;
    }

    // 构造map对象将字段插入数据表
    public static void InsertCourseRecord(Connection conn, Map<String, Object> data) throws SQLException {
        String sql = "INSERT INTO courses (course_id, grade, student_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1, data.get("courseId"));
            preparedStatement.setObject(2, data.get("grade"));
            preparedStatement.setObject(3, data.get("studentId"));
            preparedStatement.executeUpdate();
        }
    }

    public static void GenerateStudentRecords(Connection conn) throws Exception {
        Random rand = new Random();
        Statement stmt=conn.createStatement();

        for(int i=0;i<5;i++){
            int id=i+1;
            String name= RandomString.generateRandomString(5);

            String sql="insert into students(id,name) values(" +
                    id + "," +
                    "'"+ name  + "'"+
                    ")";
            System.out.println(sql);
            int result=stmt.executeUpdate(sql);
            if(result==1){
                System.out.println("Record " + i + " Inserted");
            }
        }
        stmt.close();
    }

    // 构造map对象将字段插入数据表
    public static void InsertStudentRecord(Connection conn, Map<String, Object> data) throws SQLException {
        String sql = "INSERT INTO students (id, name) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1, data.get("id"));
            preparedStatement.setObject(2, data.get("name"));
            preparedStatement.executeUpdate();
        }
    }
}