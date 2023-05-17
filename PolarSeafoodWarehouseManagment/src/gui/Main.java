package gui;

import java.util.ArrayList;
import java.util.List;

import controller.DataAccessException;
import controller.ProductCtrl;
import controller.ShipmentCtrl;
import model.Product;
import model.Shipment;

public class Main {
	public static void main(String[] args) throws DataAccessException {
		
		
		ProductCtrl pc = new ProductCtrl();
		
		Product p = pc.findProductByBarcode("4820226000099");
		
		System.out.println(p.getProductName());
		
		
		
		
	}
}
