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
		
		System.out.println("Welcome to Hotel Management!");
		
		boolean exit = false;
		Account account = null;
		Login login = new Login();
		Register register = new Register();
//		Booking bookingRoom = new Booking();
		Menu menu = new Menu();
		CustomerController cusCont = new CustomerController();
		
		String choose = null;
		
		menu.homeMenu();
		while(true) {
			choose = scan.nextLine();
			switch(choose) {
			
			//Login account
			case "1":
				account = login.getLogin();
			
				switch(account.getRoleId()) {
				//Account is a customer
				case 1:
					cusCont.cusControl(account);					
					break;
				//Account is a hotel clerk
				case 2:
					System.out.println("Hello clerk");
					break;
				//Account is a admin
				case 3:
					System.out.println("Hello admin");
					break;
				}
				
				break;
				
			//Register account
			case "2":
				account = register.signUp();
				cusCont.cusControl(account);
				break;
			//Exit system
			case "3":
				System.out.println("Exited");
				exit = true;
				break;
			default:
				System.out.println("Invalid! Try again");
				break;
			}
			if(exit)
				break;
			menu.homeMenu();
		}
		
	}

}
