package test;

import java.time.LocalDate;
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
	private LocalDate date;
	
	
	@BeforeEach
	void setUp() throws Exception {
		//sc = new ShipmentCtrl();
	}
	
	@Test
	void testPersistShipmentWithValidInfo() throws DataAccessException {
		//Arrange
		sc = new ShipmentCtrl();
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		
		String productBarcode = "4820226000099";
		int productQty = 500;
		LocalDate date = LocalDate.now();
				
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		
		sc.scanProduct(productQty, productBarcode, date);
		
//		Shipment shipment = sc.getCurrentShipment();
//		shipment.setShipmentNo("5555");
		
		sc.confirmShipment();
		//Assert
		
		
	}
		
}


