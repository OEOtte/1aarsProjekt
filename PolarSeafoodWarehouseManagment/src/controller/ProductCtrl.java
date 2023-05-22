package controller;

import java.util.List;

import controller.DataAccessException;
import database.ProductDB;
import database.ProductDBIF;
import model.LotLine;
import model.Product;

public class ProductCtrl {

	public ProductCtrl() {

	}

	public Product findProductByBarcode(String barcode) throws DataAccessException {
		ProductDBIF productDBIF = new ProductDB();
		return productDBIF.findProductByBarcode(barcode);
	}

	public boolean addLotLineToProduct(Product product, LotLine lotLine) {
		boolean res = false;
		res = product.addLotLine(lotLine);
		return res;
	}
	
	public List<Product> findProductsByPartialName(String prod) throws DataAccessException{
		ProductDBIF prodDBIF = new ProductDB();
		return prodDBIF.findProductByPartialName(prod);
	}
	

}
