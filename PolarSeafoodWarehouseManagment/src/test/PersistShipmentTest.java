package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.ShipmentCtrl;
import database.ShipmentDB;
import database.ShipmentDBIF;
import model.Shipment;

class PersistShipmentTest {
	
	private ShipmentCtrl sc;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		ShipmentCtrl sc = new ShipmentCtrl();
	}
	
	@Test
	void testPersistShipmentWithValidInfo() throws DataAccessException {
		//Arrange
		
		String freightNo = "123";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("4321");
		String warehouseName = "PSU1";
		
		String productBarcode = "12345678";
		int productQty = 2;
		
		ShipmentDBIF shimpmentDBIF = new ShipmentDB();
		
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		
		sc.scanProduct(productQty, productBarcode, null);
		
		Shipment shipment = sc.getCurrentShipment();
		
		sc.confirmShipment();
		shimpmentDBIF.persistShipment(shipment);
		//Assert
	}
		
}


