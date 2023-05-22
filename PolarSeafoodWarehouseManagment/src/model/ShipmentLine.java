package model;

public class ShipmentLine {

	private int quantity;
	private Product product;
	private Shipment shipment;
	private double weight;

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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setWeight(double shipmentLineWeight) {
		this.weight += shipmentLineWeight;
	}
	
	public double getWeight() {
		return weight;
	}

}
