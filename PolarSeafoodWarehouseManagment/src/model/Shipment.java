package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Shipment {

	
	private List<Staff> staffOnShipment;
	private Freight freight;
	private LocalDate arrivalDate;
	private Warehouse arrivalLocation;
	private LocalDate disbatchDate;
	private int totalWeight;
	private int amountOfDifferentProduct;
	private String shipmentNo;
	private List<ShipmentLine> shipmentLines;

	public Shipment(List<Staff> staffs, Freight freight, Warehouse warehouse) {
		this.freight = freight;
		this.staffOnShipment = staffs;
		this.arrivalLocation = warehouse;
		this.arrivalDate = LocalDate.now();
	}

	public ShipmentLine addProductToAShipmentline(Product product, int quantity) {
		ShipmentLine res = null;
		for (int i = 0; i < shipmentLines.size(); i++) {
			if (shipmentLines.get(i).getProduct().equals(product)) {
				shipmentLines.get(i).increaseQty(quantity);
				totalWeight += quantity * product.getWeight();
				res = shipmentLines.get(i);
			}
		}
		if (res == null) {
			ShipmentLine shipmentLine = new ShipmentLine(product, quantity);
			shipmentLines.add(shipmentLine);
			amountOfDifferentProduct++;
			totalWeight += quantity * product.getWeight();
		}

		return res;
	}

	public Warehouse getArrivalLocation() {
		return arrivalLocation;
	}

	public List<ShipmentLine> getShipmentLines() {
		return shipmentLines;
	}

	public List<Staff> getStaffOnShipment() {
		return staffOnShipment;
	}

	public void setStaffOnShipment(List<Staff> staffOnShipment) {
		this.staffOnShipment = staffOnShipment;
	}

	public Freight getFreight() {
		return freight;
	}

	public void setFreight(Freight freight) {
		this.freight = freight;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalDate getDisbatchDate() {
		return disbatchDate;
	}

	public void setDisbatchDate(LocalDate disbatchDate) {
		this.disbatchDate = disbatchDate;
	}

	public void setArrivalLocation(Warehouse arrivalLocation) {
		this.arrivalLocation = arrivalLocation;
	}

	public void setShipmentLines(List<ShipmentLine> shipmentLines) {
		this.shipmentLines = shipmentLines;
	}

	public int getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}

	public int getAmountOfDifferentProduct() {
		return amountOfDifferentProduct;
	}

	public void setAmountOfDifferentProduct(int amountOfDifferentProduct) {
		this.amountOfDifferentProduct = amountOfDifferentProduct;
	}

	public String getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(String shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

}
