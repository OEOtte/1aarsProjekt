package controller;

import java.time.LocalDate;
import java.util.List;

import controller.DataAccessException;
import database.ProductDB;
import database.ProductDBIF;
import model.LotLine;
import model.Product;

/**
 * @author Gruppe 3
 */

public class ProductCtrl {

	/**
	* This method finds a product by its barcode.
    *
    * @param (barcode) The barcode of the product.
    * @return Returns the product found by the barcode.
    * @throws DataAccessException if there is an error accessing the data.
    */

	public Product findProductByBarcode(String barcode) throws DataAccessException {
		ProductDBIF productDBIF = new ProductDB();
		return productDBIF.findProductByBarcode(barcode);
	}
	
	/**
     * This method adds a lotLine to a product.
     *
     * @param (product and lotLine) the product is added to a lotLine.
     * @return returns true if the lotLine was successfully added, false otherwise.
     */

	public boolean addLotLineToProduct(Product product, LotLine lotLine) {
		boolean res = false;
		res = product.addLotLine(lotLine);
		return res;
	}
	
	/**
     * This method finds products by a partial name.
     *
     * @param (product) is the partial name of the product.
     * @return Returns a list of products matching the partial name.
     * @throws DataAccessException if there is an error accessing the data.
     */
	
	public List<Product> findProductsByPartialName(String product) throws DataAccessException{
		ProductDBIF prodDBIF = new ProductDB();
		return prodDBIF.findProductByPartialName(product);
	}


	

}
