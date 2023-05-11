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

	private Shipment currShipment;
	private ProductCtrl productCtrl;
	private StorageCtrl storageCtrl;

	public Shipment createShipment(List<String> staffNos, String freightNumber) throws DataAccessException {

		StaffCtrl staffCtrl = new StaffCtrl();
		FreightCtrl freightCtrl = new FreightCtrl();

		List<Staff> staffs = staffCtrl.findStaffById(staffNos);
		Freight freight = freightCtrl.findFreightByFreightNumber(freightNumber);

		if (staffs != null && freight != null) {
			Shipment shipment = new Shipment(staffs, freight);
			this.currShipment = shipment;
		}

		return currShipment;
	}

	public Product scanProduct(int quantity, String barcode) throws DataAccessException {
		if (productCtrl == null) {
			productCtrl = new ProductCtrl();
		}
		Product product = productCtrl.findProductByBarcode(barcode);

		checkIfProductAlreadyScannedAndAddProductToShipmentline(product, quantity);

		addFoundProductToAvaliableLot(product, quantity);

		return product;
		
	}

	private boolean checkIfProductAlreadyScannedAndAddProductToShipmentline(Product product, int quantity) {
		boolean res = this.currShipment.addProductToAShipmentline(product, quantity);
		return res;
	}

	private boolean addFoundProductToAvaliableLot(Product product, int quantity) throws DataAccessException {
		boolean res = false;
		if (storageCtrl == null) {
			storageCtrl = new StorageCtrl();
		}

		LotLine lotLine = storageCtrl.findAvailableLotByPriorityForProduct(product, quantity);
		res = productCtrl.addLotLineToProduct(product, lotLine);

		return res;
	}

	public Shipment confirmShipment() throws DataAccessException {
		ShipmentDBIF shimpmentDBIF = new ShipmentDB();
		shimpmentDBIF.persistShipment(this.currShipment);
		this.currShipment = null;
		return currShipment;
	}
}
