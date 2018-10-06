package function;

public class Menu {
	public void homeMenu() {
		System.out.println("Please type a number to choose your action");
		System.out.println("---------------------------");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
		System.out.println("---------------------------");
	}
	
	public void customerMenu() {
		System.out.println("---------------------------");
		System.out.println("1. Booking rooms");
		System.out.println("2. Logout");
		System.out.println("---------------------------");
	}
	
	public void clerkMenu() {
		System.out.println("---------------------------");
		System.out.println("1. View all rooms");
		System.out.println("2. Search / filter rooms");
		System.out.println("3. Update status of rooms");
		System.out.println("---------------------------");
	}
	
	public void adminMenu() {
		System.out.println("---------------------------");
		System.out.println("1. Create new user");
		System.out.println("2. Set role");
		System.out.println("3. View all users");
		System.out.println("4. Search / filter user");
		System.out.println("5. Update user information");
		System.out.println("---------------------------");
	}
}
