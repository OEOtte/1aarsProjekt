package model;

public class BoxedProduct extends Product {

	private int quantityInBox;
	private String parentBarcode;
	
	public BoxedProduct(int id, String productName, String itemNumber, String barcode, String countryOfOrigin,
			Double percentOfGlaze, String description, int weight, int minStock, boolean priority, int quantityInBox, String parentBarcode) {
		super(id,productName, itemNumber, barcode, countryOfOrigin, percentOfGlaze, description, weight, minStock, priority);
		this.quantityInBox = quantityInBox;
		this.parentBarcode = parentBarcode;
	}

}