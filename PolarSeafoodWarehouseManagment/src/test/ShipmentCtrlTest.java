package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.FreightCtrl;
import controller.ShipmentCtrl;
import controller.StaffCtrl;
import model.Freight;
import model.Shipment;


class ShipmentCtrlTest {
	
	private ShipmentCtrl sc;
	private FreightCtrl fc;
	private StaffCtrl staffc;

	@BeforeEach
	void setUp() throws Exception {
		ShipmentCtrl sc = new ShipmentCtrl();
		FreightCtrl fc = new FreightCtrl();
		StaffCtrl staffc = new StaffCtrl();
	}
	
	@Test
	void testValidFreightNumber123ValidStaffIdValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNumber = "123";
		List<String> staffNo = new ArrayList<>();
		staffNo.add("4321");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertTrue(shipment!=null);
	}
	
	void testValidFreightNumberValidStaffIdsTwoStaffIdsValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNumber = "123";
		List<String> staffNo = new ArrayList<>();
		staffNo.add("4321");
		staffNo.add("5432");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertTrue(shipment!=null);
	}
	
	void testInvalidFreightNumber000ValidStaffIdsValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNumber = "000";
		List<String> staffNo = new ArrayList<>();
		staffNo.add("4321");
		staffNo.add("5432");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertFalse(shipment!=null); //Kig lige på den igen
	}
	
	void testInvalidFreightNumberIsNullValidStaffidsValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNumber = null;
		List<String> staffNo = new ArrayList<>();
		staffNo.add("4321");
		staffNo.add("5432");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertFalse(shipment!=null);
	}
	
	void testValidFreightNumberInvalidStaffidsIsNullValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNumber = "123";
		List<String> staffNo = new ArrayList<>();
		staffNo.add(null);
		staffNo.add(null);
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertFalse(shipment!=null);
	}
	
	void testValidFreightNumberInvalidStaffid0000ValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNumber = "123";
		List<String> staffNo = new ArrayList<>();
		staffNo.add("0000");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertFalse(shipment!=null);
	}
	
	void testValidFreightNumberInvalidStaffids0000and0001ValidWarehouseName() throws DataAccessException {
		//Arrange
		String freightNumber = "123";
		List<String> staffNo = new ArrayList<>();
		staffNo.add("0000");
		staffNo.add("0001");
		String warehouseName = "PSU1";
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertFalse(shipment!=null);
	}
	
	void testValidFreightNumberValidStaffidsInvalidWarehouseNameIsNull() throws DataAccessException {
		//Arrange
		String freightNumber = "123";
		List<String> staffNo = new ArrayList<>();
		staffNo.add("4321");
		staffNo.add("5432");
		String warehouseName = null;
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertFalse(shipment!=null);
	}
	
	void testValidFreightNumberValidStaffidsInvalidWarehouseNameIsUkraine() throws DataAccessException {
		//Arrange
		String freightNumber = "123";
		List<String> staffNo = new ArrayList<>();
		staffNo.add("4321");
		staffNo.add("5432");
		String warehouseName = "Ukraine";
		//Act
		sc.createShipment(staffNo, freightNumber, warehouseName);
		Shipment shipment = sc.getCurrentShipment();
		//Assert
		assertFalse(shipment!=null);
	}
}
