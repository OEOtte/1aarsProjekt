package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.ProductCtrl;
import controller.ShipmentCtrl;
import controller.StorageCtrl;
import model.LotLine;



class ScanProductTest {

	private ShipmentCtrl sc;
	private ProductCtrl pc;
	private StorageCtrl stoCtrl;
	
	@BeforeEach
	void SetUp() {
	ShipmentCtrl sc = new ShipmentCtrl();
	ProductCtrl pCtrl = new ProductCtrl();
	StorageCtrl stoCtrl = new StorageCtrl();
	}
	
	@Test
	void testScanProductWithValidInputs() throws DataAccessException {
		// Arrange
		LocalDate expiryDate = LocalDate.of(2023, 07, 01);
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		// act
		sc.createShipment(staffNos, freightNo, warehouseName);
		sc.scanProduct(1, "1234", expiryDate);
		
		// assert
		assertEquals(sc.getCurrentShipment().getAmountOfDifferentProduct() , 1);
	}

}
