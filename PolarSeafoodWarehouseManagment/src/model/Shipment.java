package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Shipment {

	private List<Staff> staffOnShipment;
	private Freight freight;
	private LocalDate arrivalDate;
	private Warehouse arrivalLocation; //TODO set warehouse on shipment
	private LocalDate disbatchDate;
	private List<ShipmentLine> shipmentLines;
	
	
	public Shipment(List<Staff> staffs, Freight freight) {
		this.freight = freight;
		this.staffOnShipment = staffs;
		this.arrivalDate = LocalDate.now();
	}
	
	public boolean addProductToAShipmentline(Product product, int quantity) {
		boolean res = false;
		for(int i = 0; i < shipmentLines.size(); i++) {
			if(shipmentLines.get(i).getProduct().equals(product)){
				shipmentLines.get(i).increaseQty(quantity);
				res = true;
			}
		}
		if(!res) {
			ShipmentLine shipmentLine = new ShipmentLine(product, quantity);
			shipmentLines.add(shipmentLine);
		}
		
		return res;
	}
}
