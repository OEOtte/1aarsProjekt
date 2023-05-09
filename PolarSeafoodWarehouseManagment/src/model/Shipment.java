package model;

import java.util.List;

public class Shipment {

	private List<Staff> staffOnShipment;
	private Freight freight;
	
	
	public Shipment(List<Staff> staffs, Freight freight) {
		this.freight = freight;
		this.staffOnShipment = staffs;
	}
}
