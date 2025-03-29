package database.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    // 配置连接信息
    Connection conn = null;

    //构造函数配置信息
    public Connector() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        conn = (Connection) context.getBean("connection");
        // 如果连接失败无法创造正确的bean
        System.out.println("Connection succeed.");
    }

    public Connection getConnection() {
        if (conn != null) {
            return conn;
        }
        System.out.println("Connection not succeed.");
        return null;
    }

    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            // 处理关闭连接时的异常
            System.out.println("Connection close failed.");
            throw new RuntimeException(e);
        }
    }
}