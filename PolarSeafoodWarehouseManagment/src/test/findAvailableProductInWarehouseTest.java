package test;

	import static org.junit.jupiter.api.Assertions.assertEquals;

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
		void testfindAvailableProductInWarehouseWithValidInput() throws DataAccessException {
			// Arrange
			Product p;
			// act
			p = stoCtrl.findAvailableProductInWarehouseAndPrepareToRemove(pc.findProductByBarcode("4820226000082"), 1, "PSU1").get(0).getProduct();
			// assert
			assertEquals(p.getBarcode() , "4820226000082");
		}

	}

