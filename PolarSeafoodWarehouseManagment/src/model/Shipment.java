package model;

import java.util.List;

public class Shipment {

	private List<Staff> staffOnShipment;
	private Freight freight;
	private List<ShipmentLine> shipmentLines;
	
	
	public Shipment(List<Staff> staffs, Freight freight) {
		this.freight = freight;
		this.staffOnShipment = staffs;
	}
	
	public boolean addProductToAOrderline(Product product, int quantity) {
		boolean res = false;
		for(int i = 0; i < shipmentLines.size(); i++) {
			if(shipmentLines.get(i).getProduct().equals(product)){
				shipmentLines.get(i).increaseQty(quantity);
			}
		}
	}
}
