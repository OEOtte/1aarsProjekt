package test;

	import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.ProductCtrl;
import controller.StorageCtrl;
import model.Product;



	public class findAvailableProductInWarehouseTest {

		private ProductCtrl pc;
		private StorageCtrl stoCtrl;
		
		@BeforeEach
		void SetUp() throws DataAccessException {
			pc = new ProductCtrl();
			stoCtrl = new StorageCtrl();
		}
		
		/**
		 *This method tests if the findAvailableProductInWarehouse method works correctly.
		 *It does this by creating a local variable that is product p, and setting it to be the return value of the tested method, and
		 *afterwards asserting that the barcode of p is the same as the barcode used in the scanProduct method. 
		 * @throws DataAccessException
		 */
		
		@Test
		void testFindAvailableProductInWarehouseWithValidInput() throws DataAccessException {
			// Arrange
			Product p;
			// act
			p = stoCtrl.findAvailableProductInWarehouseAndPrepareToRemove(pc.findProductByBarcode("4820226000082"), 1, "PSU1").get(0).getProduct();
			// assert
			assertEquals(p.getBarcode() , "4820226000082");
		}

		@Test
		void testFindAvailableProductInWarehouseWithInvalidProduct() throws DataAccessException {
			// Arrange
			Product p;
			// act
			p = null;
			// assert
			org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> 
		        stoCtrl.findAvailableProductInWarehouse(p, 1, "PSU1"));
		    
		}
		
		@Test
		void testFindAvailableProductInWarehouseWhereWarehouseNameIsNotString() throws DataAccessException {
			// Arrange
			Product p;
			// act
			p = pc.findProductByBarcode("4820226000082");
			// assert
			org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> 
		        stoCtrl.findAvailableProductInWarehouse(p, 1, null));
		    
		}
		
		@Test
		void testFindAvailableProductInWarehouseWhereWarehouseNameHasInvalidInfo() throws DataAccessException {
			// Arrange
			Product p;
			// act
			p = pc.findProductByBarcode("4820226000082");
			// assert
			org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> 
		        stoCtrl.findAvailableProductInWarehouse(p, 1, "Ukraine"));
		}
		
		@Test
		void testFindAvailableProductInWarehouseWithInvalidQuantity0() throws DataAccessException {
			// Arrange
			Product p;
			// act
			p = pc.findProductByBarcode("4820226000082");
			// assert
			org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> 
		        stoCtrl.findAvailableProductInWarehouse(p, 0, "PSU1"));
		}
		
		@Test
		void testFindAvailableProductInWarehouseWithInvalidQuantityMinus1() throws DataAccessException {
			// Arrange
			Product p;
			// act
			p = pc.findProductByBarcode("4820226000082");
			// assert
			org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> 
		        stoCtrl.findAvailableProductInWarehouse(p, -1, "PSU1"));
		}
	}

