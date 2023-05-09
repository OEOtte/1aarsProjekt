package model;

public class ShipmentLine {

	private int quantity;
	private Product product;
	private Shipment shipment;

	public Product getProduct() {
		return product;
	}

	public void increaseQty(int quantity) {
		this.quantity += quantity;
	}
}
