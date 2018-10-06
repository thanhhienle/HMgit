package admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import function.ConnectorDB;
import unitfunc.QueryDB;

public class ViewAllUser {

	private QueryDB queryDB;
	private Connection conn;
	
	public void viewAllUser() {
		
		conn = ConnectorDB.getServerConnection();
		queryDB = new QueryDB();
		
		String sql = "SELECT UserName, Pass, _Role.RoleName AS LevelUser FROM Account"
				+ " INNER JOIN _Role ON Account.RoleID = _Role.RoleID";
		ResultSet rs = queryDB.selectData(sql, conn);
		System.out.println("List of users");
		System.out.println("-------------");
		System.out.printf("%20s |%20s |%15s%n", "UserName", "Pass", "LevelUser");
		System.out.println("----------------------------------------------------------");
		try {
			while(rs.next()) {
				System.out.printf("%20s |", rs.getString("UserName"));
				System.out.printf("%20s |", rs.getString("Pass"));
				System.out.printf("%15s%n", rs.getString("LevelUser"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
