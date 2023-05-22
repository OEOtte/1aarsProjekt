package model;

import java.time.LocalDate;
import java.util.Date;

public class LotLine {

	private int quantity;
	private Product product;
	private LocalDate expirationDate;
	private Lot lot;
	private int removedQty;
	
	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}

	public int getRemovedQty() {
		return removedQty;
	}

	public void setRemovedQty(int removedQty) {
		this.removedQty = removedQty;
	}

	public LotLine(Product product, int quantity, LocalDate expirationDate, Lot lot) {
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
