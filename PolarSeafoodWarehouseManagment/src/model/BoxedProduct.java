package model;

public class BoxedProduct extends Product {

	private int quantityInBox;
	private String parentBarcode;
	
	public BoxedProduct(String productName, String itemNumber, String barcode, String countryOfOrigin,
			Double percentOfGlaze, String description, int weight, int minStock, boolean priority, int quantityInBox, String parentBarcode) {
		super(productName, itemNumber, barcode, countryOfOrigin, percentOfGlaze, description, weight, minStock, priority);
		this.quantityInBox = quantityInBox;
		this.parentBarcode = parentBarcode;
	}

}
