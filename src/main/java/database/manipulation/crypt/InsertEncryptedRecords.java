package database.manipulation.crypt;
/*
 * 对插入语句进行处理并完成插入操作
 * 实现逻辑
 * 逻辑1：本类只进行加密字段重写，最后由普通插入语句调用
 * 逻辑2：接收原始插入语句，经过处理后执行插入操作
 * */

import database.config.Connector;
import encryption.symmetric.SymmetricEncryption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

public class InsertEncryptedRecords {

    public static boolean InsertEncryptedStudent(Connection conn, Map<String, Object> data)throws Exception{
        String en_id= SymmetricEncryption.Encrypt(data.get("id"));
        String en_name= SymmetricEncryption.Encrypt(data.get("name"));
        String sql="insert into encrypted_students values (" +
                "'" +en_id+ "'"+
                ",'" +en_name+ "'"+
                ")";
        System.out.println(sql);
        Statement stmt=conn.createStatement();
        int result=stmt.executeUpdate(sql);
        if (result>0){
            stmt.close();
            System.out.println("Record Inserted");
            return true;
        }
        System.out.println("insert failed");

        stmt.close();
        return false;
    }

    public static boolean InsertEncryptedCourse(Connection conn, Map<String, Object> data)throws Exception{
        String sql = "INSERT INTO encrypted_courses (en_course_id, en_grade, en_student_id) VALUES (?, ?, ?)";
        String en_course_id= SymmetricEncryption.Encrypt(data.get("courseId"));
        String en_grade= SymmetricEncryption.Encrypt(data.get("grade"));
        String en_student_id= SymmetricEncryption.Encrypt(data.get("studentId"));


        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(1, en_course_id);
            preparedStatement.setObject(2, en_grade);
            preparedStatement.setObject(3, en_student_id);
            preparedStatement.executeUpdate();
        }

        return false;
    }

}

