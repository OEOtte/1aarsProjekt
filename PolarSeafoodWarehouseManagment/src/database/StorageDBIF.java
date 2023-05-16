package database;

import java.time.LocalDate;

import controller.DataAccessException;
import model.Lot;
import model.LotLine;
import model.Product;
import model.Warehouse;

public interface StorageDBIF {

	public Lot findAvailableLotByPriority(boolean priority, boolean fullAssociation) throws DataAccessException;

	public boolean persistProductOnLot(Product product, Lot lot, int quantity, LocalDate date) throws DataAccessException;

	public Warehouse findWarehouseByName(String name) throws DataAccessException;


}
