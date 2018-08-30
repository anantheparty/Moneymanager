package utils;

import java.sql.*;

public class JDBCUtils {
	public static Connection getConnection(){
		final String url = "jdbc:mysql://localhost:3306/MoneyManager";
		final String user = "root";
		final String pass = "root";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con; 
	}
	public static void release(ResultSet rs, Statement st , Connection con) {
		try {
			if(rs!=null) rs.close();
			if(st!=null) st.close();
			if(con!=null) con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
