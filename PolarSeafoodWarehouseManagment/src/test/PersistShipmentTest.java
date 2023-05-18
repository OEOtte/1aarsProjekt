package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.ShipmentCtrl;
import database.ShipmentDB;
import database.ShipmentDBIF;

class PersistShipmentTest {

	private ShipmentCtrl sc;
	private LocalDate date;
	private ShipmentDBIF SDB;
	private int before;

	@BeforeEach
	void setUp() throws Exception {
		SDB = new ShipmentDB();
		before = SDB.getLatestShipmentNo();
	}

	@Test
	void testPersistShipmentWithValidInfo() throws DataAccessException {
		// Arrange
		sc = new ShipmentCtrl();
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		String productBarcode = "4820226000099";
		int productQty = 500;
		LocalDate date = LocalDate.now();
		
		
		// Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		sc.scanProduct(productQty, productBarcode, date);
		sc.confirmShipment();
		int after = SDB.getLatestShipmentNo();
		
		// Assert
		assertTrue((before + 1) == after);

	}

}
