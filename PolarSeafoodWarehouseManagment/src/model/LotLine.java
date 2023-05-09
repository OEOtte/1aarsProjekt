package model;

import java.util.Date;

public class LotLine {

	private int quantity;
	private Product product;
	private Date expirationDate;
	private Lot lot;
	
	public LotLine(Product product, int quantity, Date expirationDate, Lot lot) {
		this.product = product;
		this.quantity = quantity;
		this.expirationDate = expirationDate;
		this.lot = lot;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
