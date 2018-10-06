package function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorDBb {
	public static void main(String[] args) throws ClassNotFoundException {
		
		try {
			String dbURL = "jdbc:sqlserver://HIEN-PC:1433;instance=SQLEXPRESS;databaseName=HotelMan";
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			Connection conn = DriverManager.getConnection(dbURL, "sa", "1234");
			if (conn != null) {
				System.out.println("Connected");
			}
			
			
			
		} catch (SQLException e) {
			System.out.println("Failed");
		}
		
		
	}
}
