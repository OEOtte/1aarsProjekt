package controller;

import java.time.LocalDate;
import java.util.List;

import database.ShipmentDB;
import database.ShipmentDBIF;
import model.BoxedProduct;
import model.Freight;
import model.LotLine;
import model.Product;
import model.Shipment;
import model.Staff;
import model.Warehouse;

/**
 * @author Gruppe 3
 */

public class ShipmentCtrl {

	private Shipment currShipment;
	private ProductCtrl productCtrl;
	private StorageCtrl storageCtrl;

	/**
	 * This method creates a shipment by specified list of staffNos (staffNumbers),
	 * freight number and warehouse name.
	 *
	 * @param staffNos      the list of staff numbers that is associated for the
	 *                      creation of the shipment.
	 * @param freightNo     the freight number associated with the shipment.
	 * @param warehouseName the name of the warehouse for the shipment creation.
	 * @return returns the current shipment with the objects that are found in the
	 *         database and otherwise if they are null.
	 * @throws DataAccessException if there is an error accessing the data.
	 */

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

	/**
	 * This method scans product with specified quantity, barcode and date.
	 * 
	 * @param quantity the quantity of the scanned product
	 * @param barcode  the barcode of the scanned product
	 * @param date     the expiry date of the product.
	 * @return returns the product if current shipment and date is not null and
	 *         quantity is bigger than 0 and the found product is succesfully added
	 *         the product to a shipmentLine and finds available lot.
	 * @throws DataAccessException if there is an error accessing the data.
	 */

	public Product scanProduct(int quantity, String barcode, LocalDate date) throws DataAccessException {
		if (productCtrl == null) {
			productCtrl = new ProductCtrl();
		}
		Product product = null;
		if (currShipment != null && date != null && quantity > 0) {
			product = productCtrl.findProductByBarcode(barcode);
			currShipment.addProductToAShipmentline(product, quantity);
			addFoundProductToAvaliableLot(product, quantity, date);
		}

		return product;

	}

	/**
	 * This method adds the scanned product in the method above to an available lot.
	 * 
	 * @param product  the scanned product from the method above to the available
	 *                 lot.
	 * @param quantity the quantity of the product.
	 * @param date     the date of the of the expiry date of the product.
	 * @return returns true if the product is added to the available lot and false
	 *         if not.
	 * @throws DataAccessException if there is an error accessing the data.
	 */

	private boolean addFoundProductToAvaliableLot(Product product, int quantity, LocalDate date)
			throws DataAccessException {
		boolean res = false;
		if (storageCtrl == null) {
			storageCtrl = new StorageCtrl();
		}

		LotLine lotLine = storageCtrl.findAvailableLotByPriorityForProductInArrivalWarehouse(product, quantity, date,
				currShipment.getArrivalLocation());
		res = productCtrl.addLotLineToProduct(product, lotLine);

		return res;
	}

	/**
	 * The method confirms the shipment if the shipment is successfully persisted in
	 * the database.
	 * 
	 * @return returns the confirmed shipment
	 * @throws DataAccessException if there is an error accessing the data.
	 */

	public Shipment confirmShipment() throws DataAccessException {
		ShipmentDBIF shimpmentDBIF = new ShipmentDB();
		shimpmentDBIF.persistShipment(this.currShipment);
		return currShipment;
	}

	public Shipment getCurrShipment() {
		return currShipment;
	}

}
