package clerk;

import java.util.Scanner;

import entity.Account;
import function.Menu;

public class ClerkController {

	private Menu menu;
	private Scanner scan = new Scanner(System.in);
	private ViewAllRoom var;
	private UpdateRoom ur;
	private SearchFilterRoom sfr;
	
	public void clerkControl(Account account) {
		
		menu = new Menu();
		var = new ViewAllRoom();
		ur = new UpdateRoom();
		sfr = new SearchFilterRoom();
		
		System.out.println("Hello Clerk");
		menu.clerkMenu();
		String choose = null;
		boolean exit = false;
		while(true) {
			choose = scan.nextLine();
			switch(choose) {
			//View all rooms
			case "1":
				var.viewAllRoom();
				break;
			
			//Search and Filter room
			case "2":
				sfr.searchFilterRoom();
				break;
			
			//Update rooms
			case "3":
				ur.updateRoom();
				break;
				
			//Clerk Logout
			case "0":
				System.out.println("Signed out");
				exit = true;
				break;
			default:
				System.out.println("Invalid! Try again");
				break;
			}
			if(exit) break;
			menu.clerkMenu();
		}
		
	}
}
