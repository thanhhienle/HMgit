package customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Account;
import entity.Reservation;
import entity.Room;
import function.ConnectorDB;
import unitfunc.MinusDate;
import unitfunc.QueryDB;

public class Booking {
	private static Scanner scan= new Scanner(System.in);
	private ArrayList<Room> roomList = new ArrayList<>();
	private ArrayList<Reservation> reserList = new ArrayList<>();
	private QueryDB queryDB;
	private Connection conn;
	
	public void booking(Account cus) {
		
		String checkin = null;
		String checkout = null;
		int numOfDays = 0;
		boolean dateFormat = true;
		
		//Input check in and check out date
		do {
			System.out.println("Please input your Check in and Check out Date");
			
			checkin = scan.nextLine();
			checkout = scan.nextLine();
			String pattern = "(20)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";
			Pattern pat = Pattern.compile(pattern);
			Matcher mat = pat.matcher(checkin);
			Matcher mat2 = pat.matcher(checkout);
			
			dateFormat = mat.matches() && mat2.matches();
			if (!dateFormat) {
				System.out.println("Invalid input date values");
			} else {
				numOfDays = MinusDate.diffDay(checkin, checkout);
				if (numOfDays < 0) {
					System.out.println("Invalid input date values");
					dateFormat = false;
				}
			}
			
		} while (!dateFormat);
		
		//Showing rooms are available
		conn = ConnectorDB.getServerConnection();
		queryDB = new QueryDB();
		String sql = "SELECT RoomID, RoomType, RoomPrice, RoomStatus FROM Room"
				+ " WHERE RoomStatus = 'Available'";
		ResultSet rs = queryDB.selectData(sql, conn);
		System.out.println("Rooms are ready for you");
		System.out.println("RoomID \t RoomType \t Price \t Status");
		try {
			while (rs.next()) {
				Room room = new Room();
				room.setRoomId(rs.getInt("RoomID"));
				room.setRoomType(rs.getString("RoomType"));
				room.setPrice(rs.getDouble("RoomPrice"));
				room.setRoomStauts(rs.getString("RoomStatus"));
				roomList.add(room);
				
				System.out.println(rs.getInt("RoomID") +"\t"+ rs.getString("RoomType")+"\t"+
				rs.getString("RoomPrice")+"\t"+rs.getString("RoomStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Input number of room
		System.out.println("How many rooms do you want to book?");
		int quanRoom = scan.nextInt();
		for (int i = 1; i <= quanRoom; i++) {
			boolean isAvail = false;
			Room tempRoom;
			int roomNo;
			do {		
				System.out.println("Pleas input RoomID to select room");
				roomNo = scan.nextInt();
				tempRoom = new Room(roomNo);
				isAvail = roomList.contains(tempRoom);
				if (isAvail) {
					int index = roomList.indexOf(tempRoom);
					tempRoom = roomList.get(index);
				}
				if (!isAvail) {
					System.out.println("The room is not available");
				}
			
			} while (!isAvail);
			
			double payment = numOfDays * tempRoom.getPrice();
//			System.out.println(payment);
			Reservation reser = new Reservation(cus.getUserName(), checkin, checkout, tempRoom.getRoomId(),
					numOfDays, payment);			
			
			reserList.add(reser);
		}
		
		//Add data to Reservation Table in Database
		String sql1 = "INSERT INTO Reservation (CustomerID,CheckinDate,CheckoutDate)"
				+ " VALUES ('"+cus.getUserName()+"','"+checkin+"','"+checkout+"')";
		queryDB.updateData(sql1, conn);
		
		//Get ReservationID from Database
		String sql2 = "SELECT MAX(ReservationID) FROM Reservation";
		ResultSet rs1 = queryDB.selectData(sql2, conn);
		int id = 0;
		try {
			while (rs1.next()) {
				id = rs1.getInt(1);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Add data to ReservationDetail in Database
		int n = reserList.size();
		for (int i = 0; i < n; i++) {
			Reservation reser = reserList.get(i);
			String sql3 = "INSERT INTO ReservationDetail (ReservationID,RoomID,Payment)"
					+ " VALUES ('"+id+"','"+reser.getRoomId()+"','"+reser.getCost()+"')";
			queryDB.updateData(sql3, conn);
		}
		
		//Show bill after booking
		double totalCost = 0;
		System.out.println("Check your bill");
		System.out.format("%6s |%20s |%20s |%10s%n", "RoomID", "Check In", "Check Out", "Cost");
		for (int i = 0; i < n; i++) {
			Reservation reser = reserList.get(i);
			totalCost += reser.getCost();
			System.out.format("%6d |", reser.getRoomId());
			System.out.format("%20s |", reser.getCheckinDate());
			System.out.format("%20s |", reser.getChecoutDate());
			System.out.format("%10s%n", reser.getCost());
		}
		System.out.println("Total Cost: " + totalCost);
		
		
		                                            
	}
}
