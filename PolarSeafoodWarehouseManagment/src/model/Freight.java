package model;

public class Freight {
	private String name;
	private String nameOfCourier;
	private String email;
	private String phoneNo;
	private String address;
	private String freightNumber;
	
	
	public Freight(String name, String nameOfCourier, String email, String phoneNo, String address, String freightNumber) {
		this.name = name;
		this.nameOfCourier = nameOfCourier;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.freightNumber = freightNumber;
	}


	public String getFreightNumber() {
		return freightNumber;
	}


	public void setFreightNumber(String freightNumber) {
		this.freightNumber = freightNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNameOfCourier() {
		return nameOfCourier;
	}


	public void setNameOfCourier(String nameOfCourier) {
		this.nameOfCourier = nameOfCourier;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
}
