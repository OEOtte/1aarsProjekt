package controller;

import java.time.LocalDate;

import database.StorageDB;
import database.StorageDBIF;
import model.*;

public class StorageCtrl {

	public LotLine findAvailableLotByPriorityForProduct(Product product, int quantity, LocalDate date) throws DataAccessException {
		StorageDBIF storageDBIF = new StorageDB();
		boolean priority = product.getPriority();
		boolean fullAssociation = true; //TODO implement fullAssociation 
		Lot lot = storageDBIF.findAvailableLotByPriority(priority, fullAssociation);
		LotLine lotLine = new LotLine(product, quantity, date, lot); 
		boolean res = storageDBIF.persistProductOnLot(product, lot, quantity, date);
		
		return lotLine;
	}

	public Warehouse findWarehouseByAddress(String name) throws DataAccessException {
		StorageDBIF storageDBIF = new StorageDB();
		Warehouse warehouse = storageDBIF.findWarehouseByName(name);
		return warehouse;
	}
}