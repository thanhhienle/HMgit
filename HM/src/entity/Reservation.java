package entity;

public class Reservation {
	private int reservationId;
	private String customerId;
	private String checkinDate;
	private String checoutDate;
	private int roomId;
	private int numOfDays;
	private double cost;
	
	public Reservation(String customerId, String checkinDate, String checoutDate, int roomId,
			int numOfDays, double cost) {
		super();
		this.customerId = customerId;
		this.checkinDate = checkinDate;
		this.checoutDate = checoutDate;
		this.roomId = roomId;
		this.numOfDays = numOfDays;
		this.cost = cost;
	}
	
	public int getReservationId() {
		return reservationId;
	}	
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(String checkinDate) {
		this.checkinDate = checkinDate;
	}
	public String getChecoutDate() {
		return checoutDate;
	}
	public void setChecoutDate(String checoutDate) {
		this.checoutDate = checoutDate;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getNumOfDays() {
		return numOfDays;
	}
	public void setNumOfDays(int numOfDays) {
		this.numOfDays = numOfDays;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
