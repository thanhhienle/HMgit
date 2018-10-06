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
	
 	public void signUp() {
		
		conn = ConnectorDB.getServerConnection();
		queryDB = new QueryDB();
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
 		System.out.println(accountList.size());
		Account tempAcc = null;
		String cusId = null;
		String pass = null;
		String cusName = null;
		String cusAdd = null;
		String cusPhone = null;
		String cusEmail = null;
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
		
		String sql2 = "INSERT INTO Account (UserName, Pass, RoleID)"
				+ " VALUES ('"+cusId+"','"+pass+"',1)";
		queryDB.updateData(sql2, conn);	
		
		String sql3 = "INSERT INTO Customer (CustomerID,CustomerUser,CusName,CusAddress,Phone,Email)"
				+ " VALUES ('"+cusId+"','"+cusId+"','"+cusName+"','"+cusAdd+"','"+cusPhone+"','"+cusEmail+"')";
		queryDB.updateData(sql3, conn);
		
		
//		try {
//		
//		String sql = "Insert into Account (UserName, Pass, RoleID) Values (?, ?, ?)";
//		
//		PreparedStatement pstm = conn.prepareStatement(sql);
//		pstm.setString(1, cusId);
//		pstm.setString(2, pass);
//		pstm.setInt(3, 1);
//		
//		pstm.executeUpdate();
//		
//		sql = "Insert into Customer (CustomerID, CustomerUser, CusName, CusAddress, Phone, Email)"
//				+ "Values (?, ?, ?, ?, ?, ?)";
//		pstm = conn.prepareStatement(sql);
//		pstm.setString(1, cusId);
//		pstm.setString(2, cusId);
//		pstm.setString(3, cusName);
//		pstm.setString(4, cusAdd);
//		pstm.setString(5, cusPhone);
//		pstm.setString(6, cusEmail);
//		
//		pstm.executeUpdate();			
//		
//		System.out.println("Successfull Register");
//				
//		check = false;
//		
//	} catch (SQLException e) {
//		System.out.println("CustomerID existed, please choose another ID");
////		e.printStackTrace();			
//	}
	}
}
