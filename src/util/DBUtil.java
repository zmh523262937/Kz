package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static final String URL="jdbc:oracle:thin:@//localhost:1521/orcl";
	static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	static final String USER="scott";
	static final String PASSWORD="testdb";
	private static Connection conn;
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn=DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
