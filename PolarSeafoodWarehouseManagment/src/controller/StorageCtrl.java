package controller;

import database.StorageDB;
import database.StorageDBIF;
import model.*;

public class StorageCtrl {

	public LotLine findAvailableLotByPriorityForProduct(Product product, int quantity) throws DataAccessException {
		StorageDBIF storageDBIF = new StorageDB();
		boolean priority = product.getPriority();
		boolean fullAssociation = true; //TODO implement 
		Lot lot = storageDBIF.findAvailableLotByPriority(priority, fullAssociation);
		LotLine lotLine = new LotLine(product, quantity, null, lot); //TODO date relevant here?
		boolean res = storageDBIF.persistProductOnLot(product, lotLine);
		return lotLine;
	}
}