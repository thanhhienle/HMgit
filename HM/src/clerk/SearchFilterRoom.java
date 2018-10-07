package clerk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Room;
import function.ConnectorDB;
import unitfunc.QueryDB;

public class SearchFilterRoom {
	
	private QueryDB queryDB;
	private Connection conn;
	private static Scanner scan= new Scanner(System.in);
	private ArrayList<Room> roomList = new ArrayList<>();
	
	public void searchFilterRoom() {
		
		conn = ConnectorDB.getServerConnection();
		queryDB = new QueryDB();
		
		//Create roomList contain all rooms in Database
		String sql = "SELECT RoomID, RoomType, RoomPrice, RoomStatus FROM Room";
		ResultSet rs = queryDB.selectData(sql, conn);
		try {
			while (rs.next()) {
				Room room = new Room();
				room.setRoomId(rs.getInt("RoomID"));
				room.setRoomType(rs.getString("RoomType"));
				room.setPrice(rs.getDouble("RoomPrice"));
				room.setRoomStauts(rs.getString("RoomStatus"));
				roomList.add(room);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<Room> roomFound;
		boolean exit = false;
		String choose = null;
		searchRoomMenu();
		while(true) {
			choose = scan.nextLine();
			switch(choose) {
			case "1":
				roomFound = new ArrayList<>();
				System.out.println("Input RoomID to search");
				int roomId = inputInt();
				
				for(Room r : roomList) {
					if (r.getRoomId() == roomId) {
						roomFound.add(r);
					}
				}				
				showRooms(roomFound);
				break;
				
			case "2":
				roomFound = new ArrayList<>();
				System.out.println("Input RoomType to search");
				String roomType = scan.nextLine();
				for(Room r : roomList) {
					if(r.getRoomType().toLowerCase().contains(roomType.toLowerCase())) {
						roomFound.add(r);
					}
				}
				showRooms(roomFound);
				break;
				
			case "3":
				roomFound = new ArrayList<>();
				System.out.println("Input RoomStatus to search");
				String roomStatus = scan.nextLine();
				for(Room r : roomList) {
					if(r.getRoomStauts().toLowerCase().contains(roomStatus.toLowerCase())) {
						roomFound.add(r);
					}
				}
				showRooms(roomFound);				
				break;
				
			case "0":
				exit = true;
				break;
			default:
				System.out.println("Invalid! Try again");
				break;				
			}
			if(exit) break;
			searchRoomMenu();
		}	
		
	}
	
	private void searchRoomMenu() {
		System.out.println("Please type a number to choose search type");
		System.out.println("1. Search by RoomID");
		System.out.println("2. Search by RoomType");
		System.out.println("3. Search by RoomStatus");
		System.out.println("0. Return");
	}
	
	private void showRooms(ArrayList<Room> roomFound) {
		
		if(roomFound.isEmpty()) {
			System.out.println("Not Found!");
		} else {
			System.out.printf("%6s |%15s |%15s |%15s%n", "RoomID", "Room type", "Price", "Status");
			System.out.println("----------------------------------------------------------");
			for(Room r : roomFound) {
				System.out.printf("%6d |", r.getRoomId());
				System.out.printf("%15s |", r.getRoomType());
				System.out.printf("%15s |", r.getPrice());
				System.out.printf("%15s%n", r.getRoomStauts());
			}
		}
		
	}
	
	private int inputInt() {
    	while (true) {
    		try {
    			int num = Integer.parseInt(scan.nextLine());
    			return num;
    		} catch (NumberFormatException e) {
    			return -1;
    		}
    	}
    }
}
