package entity;

public class Account {
	
	private String userName;
	private String password;
	private int roleId;
	
	public Account(String user, String pass) {
		this.userName = user;
		this.password = pass;
	}
	
	public Account(String user, String pass, int roleId) {
		this.userName = user;
		this.password = pass;
		this.roleId = roleId;
	}
	
	public Account() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		return this.userName.equalsIgnoreCase(((Account)obj).userName);
	}
	
	
}
