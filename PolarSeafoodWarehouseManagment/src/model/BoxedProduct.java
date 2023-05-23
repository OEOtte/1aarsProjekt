package model;

public class BoxedProduct extends Product {

	private int quantityInBox;
	private String parentBarcode;
	
	public BoxedProduct(int id, String productName, String itemNumber, String barcode, int percentOfGlaze,
			String description, double weight, int minStock, boolean priority, int countryOfOrigin, Supplier supplier,
			int quantityInBox, String parentBarcode) {
		super(id, productName, itemNumber, barcode, percentOfGlaze, description, weight, minStock, priority,
				countryOfOrigin, supplier);
		this.quantityInBox = quantityInBox;
		this.parentBarcode = parentBarcode;
	}

	public int getQuantityInBox() {
		return quantityInBox;
	}

	public void setQuantityInBox(int quantityInBox) {
		this.quantityInBox = quantityInBox;
	}

	public String getParentBarcode() {
		return parentBarcode;
	}

	public void setParentBarcode(String parentBarcode) {
		this.parentBarcode = parentBarcode;
	}



}
