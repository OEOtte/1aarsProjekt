package controller;

import java.time.LocalDate;

import database.StorageDB;
import database.StorageDBIF;
import model.*;

public class StorageCtrl {

	private StorageDBIF storageDBIF;

	public LotLine findAvailableLotByPriorityForProductInArrivalWarehouse(Product product, int quantity, LocalDate date,
			Warehouse warehouse) throws DataAccessException {
		if (storageDBIF == null) {
			storageDBIF = new StorageDB();
		}

		Lot lot = storageDBIF.findAvailableLotByPriorityInArrivalWarehouse(product.getPriority(), warehouse);

		LotLine lotLine = new LotLine(product, quantity, date, lot);

		if (lot != null) {
			storageDBIF.persistProductOnLot(product, lot, quantity, date);

		}
		return lotLine;

	}

	public Warehouse findWarehouseByName(String name) throws DataAccessException {
		StorageDBIF storageDBIF = new StorageDB();
		Warehouse warehouse = storageDBIF.findWarehouseByName(name);
		return warehouse;
	}
}