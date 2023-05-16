package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.ProductCtrl;
import controller.ShipmentCtrl;
import controller.StorageCtrl;



class ScanProductTest {

	private ShipmentCtrl shipCtrl;
	private ProductCtrl pCtrl;
	private StorageCtrl stoCtrl;
	
	@BeforeEach
	void SetUp() {
	ShipmentCtrl shipCtrl = new ShipmentCtrl();
	ProductCtrl pCtrl = new ProductCtrl();
	StorageCtrl stoCtrl = new StorageCtrl();
	}
	
	@Test
	void testScanProductWithValidInputs() throws DataAccessException {
		// Arrange
		LocalDate expiryDate = LocalDate.of(2023, 07, 01);
		
		// act
		shipCtrl.scanProduct(1, "1234", expiryDate);

		// assert
		assertEquals(, );
	}

}
