package entity;

public class Room {
	private int roomId;
	private String roomType;
	private double price;
	private String roomStauts;
	
	public Room() {
		
	}
	
	public Room(int roomId) {
		this.roomId = roomId;
	}
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRoomStauts() {
		return roomStauts;
	}
	public void setRoomStauts(String roomStauts) {
		this.roomStauts = roomStauts;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.roomId == ((Room) obj).getRoomId();
	}
	
	
}
