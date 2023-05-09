package controller;

import ctrl.DataAccessException;
import database.ProductDB;
import database.ProductDBIF;
import model.Product;

public class ProductCtrl {
	
	public ProductCtrl() throws DataAccessException {
		
	}
	
	public Product findProductByBarcode(String barcode) throws DataAccessException {
		ProductDBIF productDBIF = new ProductDB();
		return productDBIF.findProductByBarcode(barcode);
	}
}
