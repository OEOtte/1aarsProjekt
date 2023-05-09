package model;

public class ShipmentLine {

	private int quantity;
	private Product product;
	private Shipment shipment;

	public ShipmentLine(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void increaseQty(int quantity) {
		this.quantity += quantity;
	}
}
