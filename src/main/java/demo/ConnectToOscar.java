package demo;

import java.sql.*;
import javax.sql.*;
import com.oscar.Driver;

import static database.config.Connector.ModelName1;

public class ConnectToOscar {

	public static void main(String[] args) {
		Connection con = null;
		Statement stmt=null;
		String DBUSER = "sysdba";
		String DBPASSWD = "szoscar55";
		String DBURL = "jdbc:oscar://localhost:2003/MYDB";
		String DBDRIVER = "com.oscar.Driver";

		try {
			// 加载数据库驱动程序
			Class.forName("com.oscar.Driver");

			// 建立数据库连接
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASSWD);

			if (con != null) {
				System.out.println("Connected to the database!");
				// 进行数据库操作
				// 在这里执行您的数据库操作
			}
			//进行简单查询操作
			stmt= con.createStatement();
			String sql = "SELECT * FROM " +
					ModelName1 +
					"." +
					"students";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//处理
				System.out.println(rs.getInt(1));
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接
			try {
				if (con != null) {
					stmt.close();
					con.close();
					System.out.println("Connection closed.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
