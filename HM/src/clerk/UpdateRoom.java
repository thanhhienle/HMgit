package clerk;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Room;
import function.ConnectorDB;
import unitfunc.QueryDB;

public class UpdateRoom {
	
	private ViewAllRoom var;
	private static Scanner scan= new Scanner(System.in);
	private ArrayList<Room> roomList = new ArrayList<>();
	private QueryDB queryDB;
	private Connection conn;
	
	
	public void updateRoom() {
		
		conn = ConnectorDB.getServerConnection();
		queryDB = new QueryDB();
		var = new ViewAllRoom();
		var.viewAllRoom();
		
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
		
		//Choose room to update status
		System.out.println("Type number to choose next action");
		System.out.println("1. Update room status \t 2. Back to menu");
		boolean exit = false;
		String choose = null;
		while(true) {
			
			choose = scan.nextLine();
			switch(choose) {
			case "1":
				int roomNo;
				boolean isExist = false;
				Room tempRoom;
				//Update room status in list and Database
				do {
					System.out.println("Input RoomID to choose room");
					roomNo = scan.nextInt();
					scan.nextLine();
					tempRoom = new Room(roomNo);
					isExist = roomList.contains(tempRoom);
					if (isExist) {
						System.out.println("Input new status for the room");
						String status = null;
						status = scan.nextLine();
						int index = roomList.indexOf(tempRoom);
						roomList.get(index).setRoomStauts(status);
						System.out.println("Update successfully");		
						
						String sql1 = "UPDATE Room SET RoomStatus = '"+status+"' WHERE RoomID = "+roomNo;
						queryDB.updateData(sql1, conn);

					} else {
						System.out.println("Invalid! Please try again");
					}
				} while(!isExist);
				break;
			case "2":
				exit = true;
				break;
			default:
				System.out.println("Invalid! Try again");
				break;
			}
			if(exit) break;
			System.out.println("Type number to choose next action");
			System.out.println("1. Update room status \t 2. Back to menu");
			
 		}
		
		
		
	}
}
