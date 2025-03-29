package database;

import database.config.Connector;
import database.manipulation.crypt.SearchEncryptedRecords;
import database.manipulation.plain.SearchRecord;
import org.junit.Test;

import java.sql.Connection;

public class TestSearchRecords {

    @Test
    public void testPlainSelection() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

        SearchRecord.SearchStudents(conn);

        mc.closeConnection();
    }

    @Test
    public void testPlainJoinQuery() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

        SearchRecord.SearchStudentsJoinCourses(conn);

        mc.closeConnection();
    }

    @Test
    public void testMyPlainJoinQuery() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

        String[] condition={"students","courses","students.id=courses.student_id","id in [2,3]"};

        SearchRecord.MyPlainJoinQueries(conn,condition);

        mc.closeConnection();
    }

    @Test
    public void testSearchEncryptedStudents() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

        SearchEncryptedRecords.SearchEncryptedStudents(conn);

        mc.closeConnection();
    }

    @Test
    public void testSearchStudentsJoinCoursesBySymmetric() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

//        String[] condition={"students","courses","students.id=courses.student_id","id = 2"};"id in [2,3]"
        String[] condition={"students","courses","students.id=courses.student_id","id in [2,3]"};

        SearchEncryptedRecords.SearchStudentsJoinCoursesBySymmetric(conn,condition);

        mc.closeConnection();
    }


    /**
     * 双表连接测试
     * @throws Exception
     */
    @Test
    public void testSearchStudentsJoinCoursesByIpe() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

//        String[] condition={"students","courses","students.id=courses.student_id","id = 2"};"id in [2,3]"
        String[] condition={"students","courses","students.id=courses.student_id","id in [2,3]"};

        SearchEncryptedRecords.SearchStudentsJoinCoursesByIpe(conn,condition);

        mc.closeConnection();
    }

    /**
     * todo : 多表连接实现与测试
     * @throws Exception
     */
    @Test
    public void testSearchStudentsJoinCoursesAndOtherByIpe() throws Exception {
        Connector mc = new Connector();
        Connection conn=mc.getConnection();

//        String[] condition={"students","courses","students.id=courses.student_id","id = 2"};"id in [2,3]"
        String[] condition={"students","courses","students.id=courses.student_id","id in [2,3]"};

        SearchEncryptedRecords.SearchStudentsJoinCoursesByIpe(conn,condition);

        mc.closeConnection();
    }

}
