package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Shipment {

	private List<Staff> staffOnShipment;
	private Freight freight;
	private LocalDate arrivalDate;
	private Warehouse arrivalLocation;
	private LocalDate dispatchDate;
	private int totalWeight;
	private int amountOfDifferentProduct;
	private int shipmentNo;
	private List<ShipmentLine> shipmentLines;

	public Shipment(List<Staff> staffs, Freight freight, Warehouse warehouse) {
		this.freight = freight;
		this.staffOnShipment = staffs;
		this.arrivalLocation = warehouse;
		this.arrivalDate = LocalDate.now();
		this.dispatchDate = LocalDate.now(); // TODO: register dispatch date. Another use-case
	}

	/**
	 * Adds a product to a shipment line or updates an existing shipment line with
	 * the given product and quantity. it checks if there is an existing shipment
	 * line with the same barcode. If found, it increases the quantity and updates
	 * the weight accordingly. If no matching shipment line is found, a new shipment
	 * line is created. The total weight and amount of different products are
	 * updated accordingly.
	 *
	 * @param product  the product to be added to the shipment line
	 * @param quantity the quantity of the product to be added
	 * @return the shipment line where the product was added or updated
	 */
	public ShipmentLine addProductToAShipmentline(Product product, int quantity) {
		ShipmentLine res = null;
		if (shipmentLines == null) {
			shipmentLines = new ArrayList<>();
		}

		boolean boxed = false;
		if (product instanceof BoxedProduct) {
			boxed = true;
		}

		for (int i = 0; i < shipmentLines.size(); i++) {
			if (boxed && ((BoxedProduct) shipmentLines.get(i).getProduct()).getParentBarcode()
					.equals(((BoxedProduct) product).getParentBarcode())) {
				shipmentLines.get(i).increaseQty(quantity);
				double weightForBox = quantity * (((BoxedProduct) product).getQuantityInBox() * product.getWeight());
				shipmentLines.get(i).setWeight(weightForBox);
				totalWeight += weightForBox;
				res = shipmentLines.get(i);
			} else if (shipmentLines.get(i).getProduct().getBarcode().equals(product.getBarcode())) {
				shipmentLines.get(i).increaseQty(quantity);
				double shipmentLineWeight = quantity * product.getWeight();
				shipmentLines.get(i).setWeight(shipmentLineWeight);
				totalWeight += shipmentLineWeight;
				res = shipmentLines.get(i);
			}
		}
		if (res == null && !boxed) {
			res = new ShipmentLine(product, quantity);
			res.setWeight(quantity * product.getWeight());
			shipmentLines.add(res);
			amountOfDifferentProduct++;
			totalWeight += (quantity * product.getWeight());
		} else if (res == null && boxed) {
			res = new ShipmentLine(product, quantity);
			double weightForBox = quantity * (((BoxedProduct) product).getQuantityInBox() * product.getWeight());
			res.setWeight(weightForBox);
			shipmentLines.add(res);
			totalWeight += weightForBox;
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

	public LocalDate getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
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

	public int getShipmentNo() {
		return shipmentNo;
	}

	public void setShipmentNo(int shipmentNo) {
		this.shipmentNo = shipmentNo;
	}

}
