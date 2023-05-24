package test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.ProductCtrl;
import controller.ShipmentCtrl;
import database.StorageDB;
import model.LotLine;

public class RemovalOfProductInWarehouseTest {

	private ShipmentCtrl sc;
	private ProductCtrl pc;
	private StorageDB stoDB;

	@BeforeEach
	void SetUp() throws DataAccessException {
		sc = new ShipmentCtrl();
		pc = new ProductCtrl();
		stoDB = new StorageDB();
	}

	/**
	 * This method tests if the removalOfProductInWarehouse method works correctly.
	 * It does this by first adding the product that is to be removed, then removing
	 * it and asserting that the return value of the method is true
	 * 
	 * @throws DataAccessException
	 */
	
	@Test
	void testRemovalOfProductInWarehouseWithValidInput() throws DataAccessException {
		// Arrange
		LocalDate expiryDate = LocalDate.of(2023, 07, 01);
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		List<LotLine> ll = new ArrayList<>();
		// act
		sc.createShipment(staffNos, freightNo, warehouseName);
		sc.scanProduct(1, "4820226000082", expiryDate);
		sc.confirmShipment();
		ll = stoDB.findAvailableProductsInWarehouseAndPrepareToRemove(pc.findProductsByPartialName("tuna").get(0), 1,
				"PSU1");
		boolean works = stoDB.removalOfProductInWarehouse(ll);
		// assert
		assertTrue(works);
	}

	/**
	 * This method tests if the removalOfProductInWarehouse method works with
	 * lotLines being null. It does this by first creating a list of LotLines ll, and setting it to null.
	 * This should make the method called with the method with ll as input parameter throw a NullPointerException.
	 * 
	 * @throws DataAccessException
	 */
	
	@Test
	void testRemovalOfProductInWarehouseWithLotLinesBeingNull() throws DataAccessException {
		// Arrange

		List<LotLine> ll = null;
		// act

		// assert
		assertThrows(NullPointerException.class, () -> stoDB.removalOfProductInWarehouse(ll));
	}
}