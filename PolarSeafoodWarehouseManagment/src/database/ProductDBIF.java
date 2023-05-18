package database;

import java.util.ArrayList;

import controller.DataAccessException;
import model.Product;


public interface ProductDBIF {
	
	public Product findProductByBarcode(String barcode) throws DataAccessException;
	
	public ArrayList<Product> findProduct(String prod) throws DataAccessException;
	

}
