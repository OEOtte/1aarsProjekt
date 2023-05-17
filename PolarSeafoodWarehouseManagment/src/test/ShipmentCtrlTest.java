package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.FreightCtrl;
import controller.ShipmentCtrl;
import controller.StaffCtrl;
import model.Shipment;


class ShipmentCtrlTest {
	
	private ShipmentCtrl sc;
	private FreightCtrl fc;
	private StaffCtrl staffc;

	@BeforeEach
	void setUp() throws Exception {
		sc = new ShipmentCtrl();
		fc = new FreightCtrl();
		staffc = new StaffCtrl();
	}
	
	@Test
	void testValidFreightNumber9999ValidStaffIdValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertTrue(shipment!=null);
	}
	
	@Test
	void testValidFreightNumberValidStaffIdsTwoStaffIdsValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		staffNos.add("4444");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertTrue(shipment!=null);
		assertEquals(fc.findFreightByFreightNumber(freightNo).getName(), "Test");
	}
	
	@Test
	void testInvalidFreightNumber000ValidStaffIdsValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNo = "000";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		staffNos.add("4444");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertNull(shipment);
		assertEquals(null, shipment);
	}
	
	@Test
	void testInvalidFreightNumberIsNullValidStaffidsValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNo = null;
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		staffNos.add("4444");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertNull(shipment);
	}
	
	@Test
	void testValidFreightNumberInvalidStaffidsIsNullValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add(null);
		staffNos.add(null);
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertNull(shipment);
	}
	
	@Test
	void testValidFreightNumberInvalidStaffid0000ValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("0000");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertNull(shipment);
	}
	
	@Test
	void testValidFreightNumberInvalidStaffids0000and0001ValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("0000");
		staffNos.add("0001");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertNull(shipment);
	}
	
	@Test
	void testValidFreightNumberValidStaffidsInvalidWarehouseNameIsNull() throws DataAccessException {
		//Arrange
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		staffNos.add("4444");
		String warehouseName = null;
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertNull(shipment);
	}
	
	@Test
	void testValidFreightNumberValidStaffidsInvalidWarehouseNameIsUkraine() throws DataAccessException {
		//Arrange
		String freightNo = "9999";
		List<String> staffNos = new ArrayList<>();
		staffNos.add("5555");
		staffNos.add("4444");
		String warehouseName = "Ukraine";
		//Act
		sc.createShipment(staffNos, freightNo, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertNull(shipment);
	}
}
