package model;

public class Staff {

	private String name;
	private String phoneNo;
	private boolean admin;
	private String staffNo;
	
	
	public Staff(String name, String phoneNo, boolean admin, String staffNo){
		this.name = name;
		this.phoneNo = phoneNo;
		this.admin = admin;
		this.staffNo = staffNo;
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


	public String getStaffNo() {
		return staffNo;
	}


	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
}
