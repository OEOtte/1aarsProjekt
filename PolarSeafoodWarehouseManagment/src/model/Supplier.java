package model;

public class Supplier {
	private int id;
	private String name;
	private String phoneNo;
	private String email;
	private String country;
	
	
	public Supplier(int id) {
		this.id = id;
	}
	
	public Supplier(String name, String phoneNo, String email, String country) {
		this.name = name;
		this.phoneNo = phoneNo;
		this.email = email;
		this.country = country;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
}
