package model;

public class Product {
	private String productName;
	private String itemNumber;
	private String barcode;
	private String countryOfOrigin;
	private Double percentOfGlaze;
	private String description;
	private int weight;
	private int minStock;
	private boolean priority;
	
	public Product(String productName, String itemNumber, String barcode, String countryOfOrigin, Double percentOfGlaze,
			String description, int weight, int minStock, boolean priority) {
		this.productName = productName;
		this.itemNumber = itemNumber;
		this.barcode = barcode;
		this.countryOfOrigin = countryOfOrigin;
		this.percentOfGlaze = percentOfGlaze;
		this.description = description;
		this.weight = weight;
		this.minStock = minStock;
		this.priority = priority;
	}

	public boolean getPriority() {
		return priority;
	}

	public String getBarcode() {
		return barcode;
	}
}
