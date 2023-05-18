package controller;

import java.time.LocalDate;
import java.util.List;

import database.ShipmentDB;
import database.ShipmentDBIF;
import model.Freight;
import model.LotLine;
import model.Product;
import model.Shipment;
import model.Staff;
import model.Warehouse;

public class ShipmentCtrl {

	private Shipment currShipment;
	private ProductCtrl productCtrl;
	private StorageCtrl storageCtrl;

	public Shipment createShipment(List<String> staffNos, String freightNo, String warehouseName)
			throws DataAccessException {

		StaffCtrl staffCtrl = new StaffCtrl();
		FreightCtrl freightCtrl = new FreightCtrl();
		StorageCtrl storageCtrl = new StorageCtrl();

		List<Staff> staffs = staffCtrl.findStaffById(staffNos);
		Freight freight = freightCtrl.findFreightByFreightNumber(freightNo);
		Warehouse warehouse = storageCtrl.findWarehouseByName(warehouseName);

		if (staffs != null && freight != null && warehouse != null) {
			Shipment shipment = new Shipment(staffs, freight, warehouse);
			this.currShipment = shipment;
		}

		return currShipment;
	}

	public Product scanProduct(int quantity, String barcode, LocalDate date) throws DataAccessException {
		if (productCtrl == null) {
			productCtrl = new ProductCtrl();
		}
		Product product = productCtrl.findProductByBarcode(barcode);

		if (currShipment != null) {
			currShipment.addProductToAShipmentline(product, quantity);
			addFoundProductToAvaliableLot(product, quantity, date);
		}

		return product;

	}

	private boolean addFoundProductToAvaliableLot(Product product, int quantity, LocalDate date)
			throws DataAccessException {
		boolean res = false;
		if (storageCtrl == null) {
			storageCtrl = new StorageCtrl();
		}

		LotLine lotLine = storageCtrl.findAvailableLotByPriorityForProductInArrivalWarehouse(product, quantity, date, currShipment.getArrivalLocation());
		res = productCtrl.addLotLineToProduct(product, lotLine);

		return res;
	}

	public Shipment confirmShipment() throws DataAccessException {
		ShipmentDBIF shimpmentDBIF = new ShipmentDB();
		shimpmentDBIF.persistShipment(this.currShipment);
		return currShipment;
	}

	public Shipment getCurrentShipment() {
		return currShipment;
	}
}
