package clerk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import function.ConnectorDB;
import unitfunc.QueryDB;

public class ViewAllRoom {
	
	private QueryDB queryDB;
	private Connection conn;
	
	public void viewAllRoom() {
		
		conn = ConnectorDB.getServerConnection();
		queryDB = new QueryDB();
		
		String sql = "SELECT RoomID, RoomType, RoomPrice, RoomStatus FROM Room";
		ResultSet rs = queryDB.selectData(sql, conn);
		System.out.println("List of rooms");
		System.out.println("-------------");
		System.out.printf("%6s |%15s |%15s |%15s%n", "RoomID", "Room type", "Price", "Status");
		System.out.println("----------------------------------------------------------");
		try {
			while(rs.next()) {
				System.out.printf("%6d |", rs.getInt("RoomID"));
				System.out.printf("%15s |", rs.getString("RoomType"));
				System.out.printf("%15s |", rs.getString("RoomPrice"));
				System.out.printf("%15s%n", rs.getString("RoomStatus"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
