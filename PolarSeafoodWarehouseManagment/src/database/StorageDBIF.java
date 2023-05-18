package database;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.DataAccessException;
import model.Lot;
import model.LotLine;
import model.Product;
import model.Warehouse;

public interface StorageDBIF {

	public Lot findAvailableLotByPriorityInArrivalWarehouse(boolean priority, Warehouse warehouse) throws DataAccessException;

	public boolean persistProductOnLot(Product product, Lot lot, int quantity, LocalDate date) throws DataAccessException;

	public Warehouse findWarehouseByName(String warehouseName) throws DataAccessException;
	
	public String findAddress(int addressId) throws DataAccessException;
	
	public ArrayList<Product> findProducts(String prod) throws DataAccessException;
	
	public void removeProduct(Product prod) throws DataAccessException;


}
