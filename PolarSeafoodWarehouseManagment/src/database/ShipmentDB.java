package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import controller.DataAccessException;
import database.*;
import model.Shipment;
import model.ShipmentLine;

public class ShipmentDB implements ShipmentDBIF {

	private static final String INSERT_SHIPMENT_TO_DATABASE_Q = "insert into Shipment(arrivalDate, arrivalLocation,dispatchDate, totalWeight, amountOfDifferentProduct, shipmentNo, freight_id) values(?,?,?,?,?,?,?);";
	private PreparedStatement insertShipmentToDatabasePS;

	private static final String INSERT_SHIPMENTLINE_TO_DATABASE_Q = "insert into ShipmentLine(quantity, shipment_id, product_id) values (?, ?, ?);";
	private PreparedStatement insertShipmentLineToDatabasePS;

	private static final String INSERT_SHIPMENT_AND_WAREHOUSE_TO_JOIN_TABLE = "Insert into ShipmentWarehouseTable(shipment_id,warehouse_id) values(?,?);";
	private PreparedStatement insertShipmentAndWarehouseToJoinTablePS;
	
	public ShipmentDB() throws DataAccessException {
		init();
	}

	private void init() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();
		try {
			insertShipmentToDatabasePS = connection.prepareStatement(INSERT_SHIPMENT_TO_DATABASE_Q, PreparedStatement.RETURN_GENERATED_KEYS);
			insertShipmentLineToDatabasePS = connection.prepareStatement(INSERT_SHIPMENTLINE_TO_DATABASE_Q);
			insertShipmentAndWarehouseToJoinTablePS = connection.prepareStatement(INSERT_SHIPMENT_AND_WAREHOUSE_TO_JOIN_TABLE);
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_PREPARE_STATEMENT, e);
		}
	}

	@Override
	public void persistShipment(Shipment shipment) throws DataAccessException {
		try {
			DBConnection.getInstance().startTransaction();

			insertShipmentToDatabasePS.setDate(1, Date.valueOf(shipment.getArrivalDate()));
			insertShipmentToDatabasePS.setString(2, shipment.getArrivalLocation().getName());
			insertShipmentToDatabasePS.setDate(3, Date.valueOf(shipment.getDisbatchDate()));
			insertShipmentToDatabasePS.setInt(4, shipment.getTotalWeight());
			insertShipmentToDatabasePS.setInt(5, shipment.getAmountOfDifferentProduct());
			insertShipmentToDatabasePS.setString(6, shipment.getShipmentNo());
			insertShipmentToDatabasePS.setInt(7, shipment.getFreight().getId());
			int id = DBConnection.getInstance().executeInsertWithIdentity(insertShipmentToDatabasePS);

			persistShipmentLine(shipment, id);
			persistShipmentInWarehouse(shipment, id);

			DBConnection.getInstance().commitTransaction();
		} catch (SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
	}

	private void persistShipmentInWarehouse(Shipment shipment,int id) throws DataAccessException {
		try {
			insertShipmentAndWarehouseToJoinTablePS.setInt(1, id);
			insertShipmentAndWarehouseToJoinTablePS.setInt(2, shipment.getArrivalLocation().getId());
			insertShipmentAndWarehouseToJoinTablePS.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
	}

	private void persistShipmentLine(Shipment shipment, int id) throws DataAccessException {
		List<ShipmentLine> shipmentLines = shipment.getShipmentLines();

		try {
			for (ShipmentLine sl : shipmentLines) {
				insertShipmentLineToDatabasePS.setInt(1, sl.getQuantity());
				insertShipmentLineToDatabasePS.setInt(2, id);
				insertShipmentLineToDatabasePS.setInt(3, sl.getProduct().getId());
				insertShipmentLineToDatabasePS.executeUpdate();
			}
		} catch (SQLException e) {
			throw new DataAccessException(DBMessages.COULD_NOT_BIND_OR_EXECUTE_QUERY, e);
		}
	}

}
