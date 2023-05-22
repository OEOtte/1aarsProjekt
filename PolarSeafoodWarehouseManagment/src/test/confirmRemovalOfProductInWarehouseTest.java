package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.ProductCtrl;
import controller.ShipmentCtrl;
import controller.StorageCtrl;

public class confirmRemovalOfProductInWarehouseTest {

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
	void testRemovalOfProductInWarehouseWithValidInput() throws DataAccessException {
		// Arrange
		LocalDate expiryDate = LocalDate.of(2023, 07, 01);
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		// act
		
		stoCtrl.confirmRemovalOfProductInWarehouse(null);
		// assert
		assertEquals(sc.getCurrShipment().getShipmentLines().get(0).getProduct().getBarcode() , "4820226000082");
	}

}
