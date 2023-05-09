package controller;

import java.util.List;

import model.Freight;
import model.Shipment;
import model.Staff;

public class ShipmentController {

	private Shipment shipment;
	private ProductCtrl productCtrl;

	public boolean createShipment(List<String> staffIds, String freightNumber) {
		boolean res = true;

		StaffCtrl staffCtrl = new StaffCtrl();
		FreightCtrl freightCtrl = new FreightCtrl();

		List<Staff> staffs = staffCtrl.findStaffByID(staffIds);
		Freight freight = freightCtrl.findFreightByFreightNumber(freightNumber);

		Shipment shipment = new Shipment(staffs, freight);

		if (shipment == null) {
			res = false;
		}

		return res;
	}

	public Product scanProduct(int quantity, String barcode) {
		if(productCtrl == null) {
			productCtrl = new ProductCtrl;
		}
		Product product = productCtrl.findProductByBarcode(barcode);
		
		if(product == null) {
			System.out.println("brok"); //TODO implement thowable or error
		}
		
		checkIfProductAlreadyScannedAndAddProductToOrderline(product, quantity);
		
		
		return product;
	}

	private boolean checkIfProductAlreadyScannedAndAddProductToOrderline(Product product, int quantity) {
		boolean res = shipment.addProductToAOrderline(product, quantity);
		return res;
	}

}
