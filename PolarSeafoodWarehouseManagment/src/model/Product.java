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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public Double getPercentOfGlaze() {
		return percentOfGlaze;
	}

	public void setPercentOfGlaze(Double percentOfGlaze) {
		this.percentOfGlaze = percentOfGlaze;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	
	
}
