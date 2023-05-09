package model;

public class Staff {

	private String name;
	private String phoneNo;
	private boolean admin;
	private String staffID;
	
	
	public Staff(String name, String phoneNo, boolean admin, String staffID){
		this.name = name;
		this.phoneNo = phoneNo;
		this.admin = admin;
		this.staffID = staffID;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public String getStaffID() {
		return staffID;
	}


	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
}
