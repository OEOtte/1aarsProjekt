package model;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private int id;
	private String productName;
	private String itemNumber;
	private String barcode;
	private int countryOfOrigin;
	private int percentOfGlaze;
	private String description;
	private double weight;
	private int minStock;
	private boolean priority;
	private Supplier suppliers;
	private List<LotLine> lotLines;

	public Product(int id, String productName, String itemNumber, String barcode, int percentOfGlaze,
			String description, double weight, int minStock, boolean priority, int countryOfOrigin, Supplier supplier) {
		this.id = id;
		this.productName = productName;
		this.itemNumber = itemNumber;
		this.barcode = barcode;
		this.countryOfOrigin = countryOfOrigin;
		this.percentOfGlaze = percentOfGlaze;
		this.description = description;
		this.weight = weight;
		this.minStock = minStock;
		this.priority = priority;
		this.suppliers = supplier;
	}

	public Supplier getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Supplier suppliers) {
		this.suppliers = suppliers;
	}

	public List<LotLine> getLotLines() {
		return lotLines;
	}

	public void setLotLines(List<LotLine> lotLines) {
		this.lotLines = lotLines;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPriority(boolean priority) {
		this.priority = priority;
	}

	public boolean getPriority() {
		return priority;
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

	public int getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(int countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public int getPercentOfGlaze() {
		return percentOfGlaze;
	}

	public void setPercentOfGlaze(int percentOfGlaze) {
		this.percentOfGlaze = percentOfGlaze;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getMinStock() {
		return minStock;
	}

	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}

	public boolean addLotLine(LotLine lotLine) {
		if (this.lotLines == null) {
			this.lotLines = new ArrayList<>();
		}
		boolean res = lotLines.add(lotLine);
		return res;
	}

	public int getId() {
		return id;
	}

}
