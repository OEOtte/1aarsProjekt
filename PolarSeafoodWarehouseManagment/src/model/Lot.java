package model;

public class Lot {

	private Warehouse warehouse;
	private String lotNumber;

	public Lot(String lotNumber, Warehouse warehouse) {
		this.lotNumber = lotNumber;
		this.warehouse = warehouse;
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
