package unitfunc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryDB {
	
	public ResultSet selectData(String sql, Connection conn) {
		
		ResultSet rs = null;
		try {
			Statement statement = conn.createStatement();
			rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void updateData(String sql, Connection conn) {
		
		try {
			Statement statement = conn.createStatement();
			int rowCount = statement.executeUpdate(sql);
			System.out.println("Row count affected:" + rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
