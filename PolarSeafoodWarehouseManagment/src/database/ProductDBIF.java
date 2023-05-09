package database;

import controller.DataAccessException;
import model.Product;


public interface ProductDBIF {
	
	public Product findProductByBarcode(String barcode) throws DataAccessException;
}
