package controller;

import java.util.List;

import database.ShipmentDB;
import database.ShipmentDBIF;
import model.Freight;
import model.LotLine;
import model.Product;
import model.Shipment;
import model.Staff;

public class ShipmentCtrl {

	private Shipment shipment;
	private ProductCtrl productCtrl;
	private StorageCtrl storageCtrl;

	public boolean createShipment(List<String> staffIds, String freightNumber) {
		boolean res = false;

		StaffCtrl staffCtrl = new StaffCtrl();
		FreightCtrl freightCtrl = new FreightCtrl();

		List<Staff> staffs = staffCtrl.findStaffById(staffIds);
		Freight freight = freightCtrl.findFreightByFreightNumber(freightNumber);

		Shipment shipment = new Shipment(staffs, freight);

		if (shipment != null) {
			this.shipment = shipment;
			res = true;
		}

		return res;
	}

	public Product scanProduct(int quantity, String barcode) throws DataAccessException {
		if(productCtrl == null) {
			productCtrl = new ProductCtrl();
		}
		Product product = productCtrl.findProductByBarcode(barcode);
		
		if(product == null) {
			System.out.println("brok"); //TODO implement thowable or error
		}
		
		boolean accomplished = checkIfProductAlreadyScannedAndAddProductToOrderline(product, quantity);
		
		if(accomplished) {
			addFoundProductToAvaliableLot(product);
		}
		
		return product;
	}

	private boolean checkIfProductAlreadyScannedAndAddProductToOrderline(Product product, int quantity) {
		boolean res = shipment.addProductToAOrderline(product, quantity);
		return res;
	}

	private boolean addFoundProductToAvaliableLot(Product product) {
		boolean res = false;
		if(storageCtrl == null) {
			storageCtrl = new StorageCtrl();
		}
		
		LotLine lotLine = storageCtrl.findAvailableLotByPriorityForProduct(product);
		res = productCtrl.addLotLineToProduct(product, lotLine);

		return res;
	}

	public Shipment confirmShipment() {
		ShipmentDBIF shimpmentDBIF = new ShipmentDB();
		shimpmentDBIF.persistShipment(this.shipment);
		this.shipment = null;
		return shipment;
	}
}
