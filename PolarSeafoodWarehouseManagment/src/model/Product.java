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
	
	public Product(String productName, String itemNumber, String barcode, String countryOfOrigin, Double percentOfGlaze,
			String description, int weight, int minStock) {
		super();
		this.productName = productName;
		this.itemNumber = itemNumber;
		this.barcode = barcode;
		this.countryOfOrigin = countryOfOrigin;
		this.percentOfGlaze = percentOfGlaze;
		this.description = description;
		this.weight = weight;
		this.minStock = minStock;
	}
}
