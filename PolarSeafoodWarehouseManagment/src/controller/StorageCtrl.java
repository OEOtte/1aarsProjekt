package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.ProductDBIF;
import database.StorageDB;
import database.StorageDBIF;
import model.*;

public class StorageCtrl {

	private StorageDBIF storageDBIF;
	private ProductCtrl productCtrl;

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
		if (storageDBIF == null) {
			storageDBIF = new StorageDB();
		}
		Warehouse warehouse = storageDBIF.findWarehouseByName(name);
		return warehouse;
	}

	public List<Product> findProductsByPartialName(String productName) throws DataAccessException {
		if (productCtrl == null) {
			productCtrl = new ProductCtrl();
		}
		return productCtrl.findProductsByPartialName(productName);
	}

	public List<LotLine> findAvailableProductInWarehouse(Product product, int quantity, String warehouseName)
			throws DataAccessException {
		if (storageDBIF == null) {
			storageDBIF = new StorageDB();
		}
		return storageDBIF.findAvailableProductsInWarehouseAndPrepareToRemove(product, quantity, warehouseName);
	}

	public boolean confirmRemovalOfProductInWarehouse(List<LotLine> lotLines) throws DataAccessException {
		if (storageDBIF == null) {
			storageDBIF = new StorageDB();
		}
		boolean res = storageDBIF.removalOfProductInWarehouse(lotLines);
		return res;
	}
}