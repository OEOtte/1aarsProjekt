package gui;

import controller.DataAccessException;
import controller.FreightCtrl;
import controller.ProductCtrl;
import model.Freight;
import model.Product;

public class Main {
	public static void main(String[] args) throws DataAccessException {
		
		
		ProductCtrl pc = new ProductCtrl();
		
		Product p = pc.findProductByBarcode("4820226000099");
		
		System.out.println(p.getProductName());
	}
}
