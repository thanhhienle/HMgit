package function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDB {
	public static Connection getServerConnection() {
		
		Connection conn = null;
		
		String dbUrl = "jdbc:sqlserver://HIEN-PC:1433;instance=SQLEXPRESS;databaseName=HotelMan";
//		String dbUrl = "jdbc:sqlserver://CPP00101811BBH:1433;instance=SQLEXPRESS;databaseName=HotelManagementDB";
//		String dbURL = "jdbc:sqlserver://HIEN-PC:1433;instance=SQLEXPRESS;databaseName=HotelMan";
		try {
			conn = DriverManager.getConnection(dbUrl, "sa", "1234");
//			conn = DriverManager.getConnection(dbUrl, "sa", "Fptsoft!23");	
			
		} catch (SQLException e) {
			System.out.println("error connect!");
			e.printStackTrace();
		}
		System.out.println("connect!");		
		return conn;
	}
}
