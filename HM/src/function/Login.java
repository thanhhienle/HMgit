package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import entity.Account;
import unitfunc.QueryDB;

public class Login {

	private static Scanner scan = new Scanner(System.in);
	private Connection conn;
	private QueryDB queryDB;
	private Account acc;
	
	public Account getLogin() {
		conn = ConnectorDB.getServerConnection();
		queryDB = new QueryDB();

		String user = null;
		String pass = null;
		int roleId;
		ResultSet rs = null;
		boolean isExisted = false;
		do {
			System.out.println("Input your username and password");
			System.out.print("UserName: ");
			user = scan.nextLine();
			System.out.print("Password: ");
			pass = scan.nextLine();
			try {
				
				String sql = "Select UserName, Pass, RoleID From Account Where UserName = '"+user+"' And Pass = '"+pass+"'";
				rs = queryDB.selectData(sql, conn);
				
				if (!rs.next()) {
					System.out.println("Wrong user or password, try again!");
					isExisted = true;
				} else {
					
					//Check are UserName and Password are right?
					if (user.equalsIgnoreCase(rs.getString("UserName")) && pass.equalsIgnoreCase(rs.getString("Pass"))) {
						System.out.println("Login successful");
						roleId = rs.getInt("RoleID");
						acc = new Account(user, pass, roleId);
						isExisted = false;
					}
				}			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("error");
			}
		} while(isExisted);	
		
		//Return Account that was signed in: user, pass, roleId
		return acc;
	}
}
