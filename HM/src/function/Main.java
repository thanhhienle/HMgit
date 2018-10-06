package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import customer.Booking;
import entity.Account;
import unitfunc.MinusDate;
import unitfunc.QueryDB;

public class Main {

	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ConnectorDB.getServerConnection();
//		Login login = new Login();		
//		Account acc = login.getLogin();
		
//		System.out.println(acc.getUserName());
		
//		Register register = new Register();
//		register.signUp();
//		Connection conn = ConnectorDB.getServerConnection();
//		String user = "cus01";
//		String pass = "1234";
//		QueryDB query = new QueryDB();
	
//		String line = "20181221";
//		String pattern = "(20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";
//		Pattern pat = Pattern.compile(pattern);
//		Matcher mat = pat.matcher(line);		
//		System.out.println(mat.matches() ? "ok" : "no"); 
		
		Account acc = new Account();
		Booking book = new Booking();
		book.booking(acc);
	
	}

}
