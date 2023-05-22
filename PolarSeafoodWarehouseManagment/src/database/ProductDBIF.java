package database;

import java.util.List;

import controller.DataAccessException;
import model.Product;


public interface ProductDBIF {
	
	public Product findProductByBarcode(String barcode) throws DataAccessException;
	
	public List<Product> findProductByPartialName(String prod) throws DataAccessException;
	

}
