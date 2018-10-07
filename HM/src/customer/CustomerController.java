package customer;

import java.util.Scanner;

import entity.Account;
import function.Menu;

public class CustomerController {
	
	private Menu menu;
	private Scanner scan = new Scanner(System.in);
	private Booking bookingRoom;
	
	public void cusControl(Account account) {
		
		menu = new Menu();
		bookingRoom = new Booking();		
		
		System.out.println("Hello Customer");
		menu.customerMenu();
		String choose = null;
		boolean exit1 = false;
		while(true) {
			choose = scan.nextLine();
			switch(choose) {
			//Customer make a reservation
			case "1":
				bookingRoom.booking(account);
				break;
			//Customer Logout
			case "0":
				System.out.println("Signed out");
				exit1 = true;
				break;
			default:
				System.out.println("Invalid! Try again");
				break;
			}
			if(exit1) break;
			menu.customerMenu();
		}
	}
}
