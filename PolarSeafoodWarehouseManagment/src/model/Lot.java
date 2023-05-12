package model;

public class Lot {

	private Warehouse warehouse;
	private String lotNumber;
	private int id;
	private boolean available;

	public Lot(int id, String lotNumber, boolean available, Warehouse warehouse) {
		this.id = id;
		this.lotNumber = lotNumber;
		this.available = available;
		this.warehouse = warehouse;
	}

	public int getId() {
		return id;
	}
	
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
}
