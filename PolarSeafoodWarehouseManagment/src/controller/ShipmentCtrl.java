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

	public boolean createShipment(List<String> staffNos, String freightNumber) throws DataAccessException {
		boolean res = false;

		StaffCtrl staffCtrl = new StaffCtrl();
		FreightCtrl freightCtrl = new FreightCtrl();

		List<Staff> staffs = staffCtrl.findStaffById(staffNos);
		Freight freight = freightCtrl.findFreightByFreightNumber(freightNumber);

		if (staffs != null && freight != null) {
			Shipment shipment = new Shipment(staffs, freight);
			this.shipment = shipment;
			res = true;
		}

		return res;
	}

	public Product scanProduct(int quantity, String barcode) throws DataAccessException {
		if (productCtrl == null) {
			productCtrl = new ProductCtrl();
		}
		Product product = productCtrl.findProductByBarcode(barcode);

		checkIfProductAlreadyScannedAndAddProductToOrderline(product, quantity);

		addFoundProductToAvaliableLot(product, quantity);

		return product;
	}

	private boolean checkIfProductAlreadyScannedAndAddProductToOrderline(Product product, int quantity) {
		boolean res = this.shipment.addProductToAShipmentline(product, quantity);
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

	public Shipment confirmShipment() {
		ShipmentDBIF shimpmentDBIF = new ShipmentDB();
		shimpmentDBIF.persistShipment(this.shipment);
		this.shipment = null;
		return shipment;
	}
}
