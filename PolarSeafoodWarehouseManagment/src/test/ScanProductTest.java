package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.ProductCtrl;
import controller.ShipmentCtrl;
import controller.StorageCtrl;



class ScanProductTest {

	private ShipmentCtrl sc;
	private ProductCtrl pc;
	private StorageCtrl stoCtrl;
	
	@BeforeEach
	void SetUp() {
		sc = new ShipmentCtrl();
		pc = new ProductCtrl();
		stoCtrl = new StorageCtrl();
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
		sc.scanProduct(1, "4820226000082", expiryDate);
		
		// assert
		assertEquals(sc.getCurrentShipment().getShipmentLines().get(0).getProduct().getBarcode() , "4820226000082");
	}

	@Test
	void testScanProductWithBarcodeWithInvalidInfo() throws DataAccessException {
		// Arrange
		LocalDate expiryDate = LocalDate.of(2023, 07, 01);
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		// act
		sc.createShipment(staffNos, freightNo, warehouseName);
		
		
		// assert
		assertThrows(NullPointerException.class, () -> sc.scanProduct(1, "abcd", expiryDate));
	}
	
	@Test
	void testScanProductWhereBarcodeDoesNotEqualString() throws DataAccessException {
		// Arrange
		LocalDate expiryDate = LocalDate.of(2023, 07, 01);
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		// act
		sc.createShipment(staffNos, freightNo, warehouseName);
		
		
		// assert
		assertThrows(NullPointerException.class, () -> sc.scanProduct(1, null, expiryDate));
	}
	
	@Test
	void testScanProductWhereQuantityIsMinus1() throws DataAccessException {
		// Arrange
		LocalDate expiryDate = LocalDate.of(2023, 07, 01);
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		// act
		sc.createShipment(staffNos, freightNo, warehouseName);
		sc.scanProduct(-1, "4820226000082", expiryDate);
		
		// assert
		assertEquals(sc.getCurrentShipment().getAmountOfDifferentProduct() , 0);
	}
	
	
	@Test
	void testScanProductWhereQuantityIs0() throws DataAccessException {
		// Arrange
		LocalDate expiryDate = LocalDate.of(2023, 07, 01);
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		// act
		sc.createShipment(staffNos, freightNo, warehouseName);
		sc.scanProduct(0, "4820226000082", expiryDate);
		
		// assert
		assertEquals(sc.getCurrentShipment().getAmountOfDifferentProduct() , 0);
	}
	
	@Test
	void testScanProductWithInvalidDate() throws DataAccessException {
		// Arrange
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		// act
		sc.createShipment(staffNos, freightNo, warehouseName);
		sc.scanProduct(1, "4820226000082", null);
		
		// assert
		assertEquals(sc.getCurrentShipment().getAmountOfDifferentProduct() , 0);
	}
	
	
}
