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
	
	/**
	 * This method finds an available lot for product by its priority for the specific warehouse it arrives at.
	 * @param product	the product that needs an available lot
	 * @param quantity	the quantity of the product
	 * @param date		the expiry date for the product
	 * @param warehouse	the warehouse that the product arrives at
	 * @return	returns a lot line for the product at the specified warehouse and null if lot is not found.
	 * @throws DataAccessException if there is an error accessing the data
	 */

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
	
	/**
	 * This method finds warehouse by name
	 * @param name	name of the warehouse that we want to find
	 * @return	returns the specified warehouse and null if warehouse not found
	 * @throws DataAccessException if there is an error accessing the data
	 */

	public Warehouse findWarehouseByName(String name) throws DataAccessException {
		if (storageDBIF == null) {
			storageDBIF = new StorageDB();
		}
		Warehouse warehouse = storageDBIF.findWarehouseByName(name);
		return warehouse;
	}

	
	/**
	 * This method finds product by its partial name
	 * @param productName	is the partial name of the product we want to find
	 * @return	returns a list of products with the matching partial name
	 * @throws DDataAccessException if there is an error accessing the data
	 */
	
	public List<Product> findProductsByPartialName(String productName) throws DataAccessException {
		if (productCtrl == null) {
			productCtrl = new ProductCtrl();
		}
		return productCtrl.findProductsByPartialName(productName);
	}
	
	/**
	 * This method finds available lot lines for the product in a warehouse and prepare to remove them
	 * @param product	the product that we need to find available lot lines for
	 * @param quantity	the quantity of the product
	 * @param warehouseName	the warehouse that we want to find an available lot lines for the product
	 * @return	returns a list of lot lines in the inputed warehouse that the product can be stored.
	 * @throws DataAccessException if there is an error accessing the data
	 */
	
	public List<LotLine> findAvailableProductInWarehouseAndPrepareToRemove(Product product, int quantity, String warehouseName)
			throws DataAccessException {
		if (storageDBIF == null) {
			storageDBIF = new StorageDB();
		}
		return storageDBIF.findAvailableProductsInWarehouseAndPrepareToRemove(product, quantity, warehouseName);
	}
	
	/**
	 * This method confirms the removal of a product in a lot line from a warehouse
	 * @param lotLines of the product that we want to remove from the warehouse
	 * @return	returns true if the removal was successful and false if it was unsuccessful
	 * @throws DataAccessException if there is an error accessing the data
	 */
	
	public boolean confirmRemovalOfProductInWarehouse(List<LotLine> lotLines) throws DataAccessException {
		if (storageDBIF == null) {
			storageDBIF = new StorageDB();
		}
		boolean res = storageDBIF.removalOfProductInWarehouse(lotLines);
		return res;
	}
}