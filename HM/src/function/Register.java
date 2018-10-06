package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Account;
import unitfunc.QueryDB;

public class Register {

	private static Scanner scan= new Scanner(System.in);
	private ArrayList<Account> accountList = new ArrayList<>();
	private QueryDB queryDB;
	private Connection conn;
	private Account acc;
	
 	public Account signUp() {
		
		conn = ConnectorDB.getServerConnection();
		queryDB = new QueryDB();
		
		//Create List containing accounts from Database
 		String sql = "Select UserName, Pass, RoleID From Account";
 		ResultSet rs = queryDB.selectData(sql, conn);
 		try {
			while (rs.next()) {
				Account acc = new Account();
				acc.setUserName(rs.getString("UserName"));
				acc.setPassword(rs.getString("Pass"));
				acc.setRoleId(rs.getInt("RoleID"));
				accountList.add(acc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
		Account tempAcc = null;
		String cusId = null;
		String pass = null;
		String cusName = null;
		String cusAdd = null;
		String cusPhone = null;
		String cusEmail = null;
		
		//Input information and Check valid UserName
		do {
			System.out.println("Input your information to regis");
			System.out.print("CustomerID: ");
			cusId = scan.nextLine();
			System.out.print("Password: ");
			pass = scan.nextLine();
			System.out.print("CustomerName: ");
			cusName = scan.nextLine();
			System.out.print("Address: ");
			cusAdd = scan.nextLine();
			System.out.print("Phone: ");
			cusPhone = scan.nextLine();
			System.out.print("Email: ");
			cusEmail = scan.nextLine();
			
			tempAcc = new Account(cusId, pass);						
						
			if (accountList.contains(tempAcc)) {
				System.out.println("CustomerID existed, please choose another ID");
			}	

		} while (accountList.contains(tempAcc));
		
		//Create an account object
		acc = new Account(cusId, pass, 1);
		
		//Add new account to Database
		String sql2 = "INSERT INTO Account (UserName, Pass, RoleID)"
				+ " VALUES ('"+cusId+"','"+pass+"',1)";
		queryDB.updateData(sql2, conn);	
		
		//Add new customer to Database
		String sql3 = "INSERT INTO Customer (CustomerID,CustomerUser,CusName,CusAddress,Phone,Email)"
				+ " VALUES ('"+cusId+"','"+cusId+"','"+cusName+"','"+cusAdd+"','"+cusPhone+"','"+cusEmail+"')";
		queryDB.updateData(sql3, conn);
		
		System.out.println("Register successfully");
		
		return acc;
	}
}
